// 페이지 로드 시 실행
$(document).ready(function() {
	// 페이지 로드 시, URL에서 음악 ID를 추출
	const urlParams = new URLSearchParams(window.location.search);
	const songId = urlParams.get('song_id');

	// 음악 ID를 사용하여 음악 상세 페이지를 로드
	loadMusicDetailPage(songId);




	//댓글


	// 초기 코멘트 로드
	loadComments(1);

	// 페이지 링크 클릭 이벤트
	$(document).on("click", ".pagination-link", function() {
		event.preventDefault();
		var page = $(this).data("page");
		loadComments(page);
	});

	function loadComments(page) {
		var size = 10;
		$.ajax({
			url: '/MusicCommentPaging?song_id=' + songId + '&page=' + page + '&size=' + size,
			type: 'GET',
			success: function(responseDTO) {
				console.log('loadComments 성공');
				console.log('composerId : ' + songId);
				var comments = responseDTO.comments;
				var totalComments = responseDTO.totalComments;
				console.log('asdasd : ' + comments[0].comment_id)
				console.log(comments[0].user_nickName)

				displayComments(comments);
				displayPagination(page, Math.ceil(totalComments / size));
			},
			error: function(data) {
				alert('코멘트 로딩 중 오류가 발생했습니다.');
				console.log(data);
			}
		});
	}



	function displayComments(comments) {
		var commentsContainer = $("#comments-container");
		commentsContainer.empty();
		console.log(comments[0].userNickName);

		$.each(comments, function(index, comment) {

			commentsContainer.append(
				'<div class="comment-item">' +
				'<div class="user-nickname">' + comment.user_nickName + '</div>' +
				'<div class="comment-content">' + comment.comment_text + '</div>' +
				'<div class="created-at">' + convertToKoreanTime(comment.comment_date) + '</div>' +
				'<div class="delete-btn" data-comment-id="' + comment.comment_id + '">삭제</div>' +
				'</div>'

			);
		});


	}

	// 한국 시간으로 변경
	function convertToKoreanTime(dateTimeString) {
		// 서버에서 제공하는 시간 형식에 따라 적절히 수정해야 할 수 있습니다.
		var serverDateTime = new Date(dateTimeString.replace(' ', 'T') + 'Z'); // ISO 8601 형식으로 변환
		var koreanDateTime = serverDateTime.toLocaleString("en-US", { timeZone: "Asia/Seoul" });
		return koreanDateTime;
	}

	function displayPagination(currentPage, totalPages) {
		var paginationContainer = $("#pagination");
		paginationContainer.empty();

		var startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
		var endPage = Math.min(totalPages, startPage + 9);

		if (currentPage > 10) {
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (startPage - 1) + '">이전</a>');
		}

		for (var i = startPage; i <= endPage; i++) {
			var activeClass = (i === currentPage) ? 'active' : '';
			paginationContainer.append('<a href="#" class="pagination-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
		}

		if (endPage < totalPages) {
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (endPage + 1) + '">다음</a>');
		}
	}

	// 스크롤 이벤트를 사용하여 특정 영역에 도달했을 때 페이지 번호를 고정
	var paginationContainer = $("#pagination");
	var threshold = $("#commentTitle").offset().top;

	window.addEventListener("scroll", function() {
		var scrollPosition = window.scrollY;

		if (scrollPosition >= threshold) {
			paginationContainer.addClass("fixed-pagination");
		} else {
			paginationContainer.removeClass("fixed-pagination");
		}
	});


	// 코멘트 작성 버튼 클릭 시 이벤트
	$("#commentBtn").on("click", function() {
		

		if (isLogin == 1) {
			const comment_text = $("#commentInput").val();
			
		console.log(songId);
		console.log(isLogin);

			$.ajax({
				type: "GET",
				url: "RegMusicComment",
				data: {
					comment_text: comment_text,
					song_id: songId
				},
				success: function(result) {
					console.log("코멘트 추가 성공");

					$("#commentInput").val("");
					// 코멘트를 업데이트하고 새로운 코멘트를 확인하기 위해 다시 로드합니다.
					loadComments(1);
				},
				error: function(error) {
					console.error("코멘트 추가 실패", error);
				}
			});


		} else {
			alert('로그인 후 이용 가능합니다')
		}




	});

	//	 코멘트 삭제 버튼 클릭 시 이벤트
	$("#comments-container").on("click", ".delete-btn", function() {
		const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");

		if (isConfirmed) {
			// 클릭된 버튼의 부모 요소에서 comment_id를 가져옴
			const commentId = $(this).data('comment-id');
			$.ajax({
				type: "GET",
				url: "DeleteMusicComment",
				data: {
					comment_id: commentId
				},
				success: function() {
					console.log("여기까지 딜리트 : " + commentId);
					console.log("코멘트 삭제 성공");
					loadComments(1);
				},
				error: function(error) {
					console.log(commentId);
					console.error("코멘트 삭제 실패", error);
				}
			})
		}
	});
});




// 이동한 페이지에서 음악 정보를 받아오는 함수
function loadMusicDetailPage(songId) {
	console.log('로드뮤직디테일페이지');

	// AJAX 요청을 통해 서버에서 음악 정보를 가져옴
	$.ajax({
		type: 'GET',
		url: 'getMusicDetail', // 실제 서버 엔드포인트 주소로 변경
		data: {
			song_id: songId
		},
		success: function(response) {
			console.log('음악 정보를 성공적으로 가져왔습니다.');
			console.log(response);
			// 가져온 음악 정보를 사용하여 페이지를 업데이트
			updateMusicDetailPage(response);

		},
		error: function(error) {
			console.error('음악 정보를 가져오는 중 오류가 발생했습니다.');
		}
	});
}

// 페이지 업데이트 함수
async function updateMusicDetailPage(song) {

	// 음악 타이틀 및 작곡가 정보 업데이트
	$('.musicDetail-title-img').append(`<img src=img/${song[0].song_img}>`)
	$('.musicDetail-title-title').append(`<div class="musicDetail-title-title">${song[0].song_name}</div>`);
	// 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
	$('.musicDetail-title-composer').append(`<div onclick="location.href=''" class="musicDetail-title-composer">${song[0].composer_name}</div>`);
	// 여기에서 좋아요 여부를 확인하고 하트 표시를 결정
	const likeCheck = await songLikeCheck(song[0].song_id);

	if (likeCheck == 1) {
		// 이미 좋아요한 경우
		$('.musicDetail-option').append(`
                        <div class="heart-filled" onclick="songLike(${song[0].song_id})">
                        		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="red" class="w-6 h-6">
  									<path d="m11.645 20.91-.007-.003-.022-.012a15.247 15.247 0 0 1-.383-.218 25.18 25.18 0 0 1-4.244-3.17C4.688 15.36 2.25 12.174 2.25 8.25 2.25 5.322 4.714 3 7.688 3A5.5 5.5 0 0 1 12 5.052 5.5 5.5 0 0 1 16.313 3c2.973 0 5.437 2.322 5.437 5.25 0 3.925-2.438 7.111-4.739 9.256a25.175 25.175 0 0 1-4.244 3.17 15.247 15.247 0 0 1-.383.219l-.022.012-.007.004-.003.001a.752.752 0 0 1-.704 0l-.003-.001Z" />
									</svg>
                        
                        </div>
                        `);
	} else {
		// 좋아요하지 않은 경우
		$('.musicDetail-option').append(`
                        <div class="heart-filled" onclick="songLike(${song[0].song_id})">
                        
                        	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="red" class="w-6 h-6">
  								<path stroke-linecap="round" stroke-linejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
								</svg>
                       	 
        
                        
                        </div>
                        `);
	}

	// 장르, 분위기, 악기 정보 업데이트
	$('.genre-area').text(song[0].genre_id);
	$('.mood-area').text(song[0].mood_id);
	$('.instrument-area').text(song[0].instrument_id);

	// 오디오 플레이어 업데이트
	let $musicPlaySpace = $('.musicDetail-playSpace');
	$musicPlaySpace.empty(); // 플레이어를 비우고 다시 생성
	let $audioPlayer = createAudioPlayer(`audio/${song[0].song_audio}`);
	$musicPlaySpace.append($audioPlayer);
}

// 오디오 플레이어 생성 함수
function createAudioPlayer(audioSrc) {
	let $audioPlayer = $('<div class="audio-player"></div>');

	// 오디오 태그 생성
	let $audio = $('<audio controls></audio>');
	let $source = $('<source src="' + audioSrc + '" type="audio/mp3">');

	// 플레이어 UI 생성
	let $progressBar = $('<div class="progress-bar"></div>');

	// 오디오 태그와 플레이어 UI를 조합하여 플레이어 생성
	$audio.append($source);
	$audioPlayer.append($audio, $progressBar);

	$audio.on('timeupdate', function() {
		// 재생 시간이 업데이트될 때마다 호출되는 이벤트
		let percentage = ($audio[0].currentTime / $audio[0].duration) * 100;
		$progressBar.css('width', percentage + '%');
	});

	return $audioPlayer;

}

async function songLike(song_id) {
	console.log(song_id);

	
	// session 에 userid 가져와서 "" 이랑 비교해서 있으면 넘어가게 해야됨
	if (isLogin == 1) {
		try {
			const response = await $.ajax({
				type: 'GET',
				url: 'MusicLikeC',
				data: {
					song_id: song_id
				}
			});

			console.log('좋아요 정보 갱신 성공.');
			console.log(response);

			// 기존에 추가된 하트 모두 제거
			$('.musicDetail-option').empty();

			// 가져온 음악 정보를 사용하여 페이지를 업데이트
			if (response == 1) {
				// 이미 좋아요한 경우
				$('.musicDetail-option').append(`<div class="heart-filled" onclick="songLike(${song_id})">
				<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="red" class="w-6 h-6">
  <path d="m11.645 20.91-.007-.003-.022-.012a15.247 15.247 0 0 1-.383-.218 25.18 25.18 0 0 1-4.244-3.17C4.688 15.36 2.25 12.174 2.25 8.25 2.25 5.322 4.714 3 7.688 3A5.5 5.5 0 0 1 12 5.052 5.5 5.5 0 0 1 16.313 3c2.973 0 5.437 2.322 5.437 5.25 0 3.925-2.438 7.111-4.739 9.256a25.175 25.175 0 0 1-4.244 3.17 15.247 15.247 0 0 1-.383.219l-.022.012-.007.004-.003.001a.752.752 0 0 1-.704 0l-.003-.001Z" />
</svg>

					

				</div>`);
			} else {
				// 좋아요하지 않은 경우
				$('.musicDetail-option').append(`<div class="heart-filled" onclick="songLike(${song_id})">
				
					<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="red" class="w-6 h-6">
  <path stroke-linecap="round" stroke-linejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
</svg>

				</div>`);
			}
		} catch (error) {
			console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
		}
	}else{
		alert('로그인 후 이용가능합니다')
	}
}

async function songLikeCheck(song_id) {
	console.log(song_id);

	try {
		const response = await $.ajax({
			type: 'GET',
			url: 'MusicLikeCheckC',
			data: {
				song_id: song_id
			}
		});

		console.log('좋아요 정보 불러오기 성공.');
		console.log("라이크 체크 :" + response);

		if (response == 1) {
			console.log("리스폰 확인 : " + response);
			// 이미 좋아요한 경우
			return 1;
		} else {
			// 좋아요하지 않은 경우
			return 0;
		}
	} catch (error) {
		console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
		return 0; // 에러 발생 시 기본적으로 좋아요하지 않은 것으로 처리
	}
}
