document.addEventListener("DOMContentLoaded", function() {
	// 페이지를 변경하기 전에 현재 스크롤 위치 저장
	function storeScrollPosition() {
		sessionStorage.setItem("scrollPosition", window.scrollY);
	}

	// 새 콘텐츠가 로드된 후에 스크롤 위치 복원
	function restoreScrollPosition() {
		const scrollPosition = sessionStorage.getItem("scrollPosition");
		if (scrollPosition) {
			window.scrollTo(0, parseInt(scrollPosition));
			sessionStorage.removeItem("scrollPosition");
		}
	}

	// 페이지 번호를 클릭하는 이벤트에 스크롤 위치 저장 함수 추가
	document.querySelectorAll('.pagination-link').forEach(link => {
		link.addEventListener('click', () => {
			storeScrollPosition();
		});
	});

	// 콘텐츠가 로드된 후에 스크롤 위치를 복원하는 이벤트 리스너 추가
	window.addEventListener('load', () => {
		restoreScrollPosition();
	});
//////////////////////////////////////////////////////
	// 별점 기능
	const stars = document.querySelectorAll('.star');
	const ratingValue = document.getElementById('rating-value');
	const followBtn = document.getElementById('followBtn');
	const messageBtn = document.getElementById('messageBtn');

	stars.forEach(star => {
		star.addEventListener('click', () => {
			const value = parseInt(star.getAttribute('data-value'));
			ratingValue.innerText = `평가: ${value}점`;
			removeActiveStars();
			setActiveStars(value);
			
			// 서버로 별점 전송
            submitRatingToServer(value);
		});

		star.addEventListener('mouseover', () => {
			const value = parseInt(star.getAttribute('data-value'));
			removeActiveStars();
			setActiveStars(value);
		});

		star.addEventListener('mouseout', () => {
			removeActiveStars();
		});
	});

	function removeActiveStars() {
		stars.forEach(star => {
			star.classList.remove('active');
		});
	}

	function setActiveStars(value) {
		for (let i = 0; i < value; i++) {
			stars[i].classList.add('active');
		}
	}
	
	function submitRatingToServer(rating) {
    //const composerId = composerId/* HTML 또는 Thymeleaf에서 composerId 가져오기 */;
    const userFullNumber = '01044982324'/* 세션 또는 저장된 위치에서 userFullNumber 가져오기 */;
    const ratingData = {
		
        userFullNumber: userFullNumber,
        composerId: composerId,
        rating: rating
    };

    // AJAX를 사용하여 서버에 평가 전송
    $.ajax({
        type: "POST",
        url: "/ratings/rate",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(ratingData),
        success: function(result) {
			const createdRatingID = result.ratingID;
			console.log(userFullNumber);
			console.log(composerId);
			console.log(rating);
			
            console.log("평가가 성공적으로 제출되었습니다. RatingID: " + createdRatingID);
        },
        error: function(error) {
            console.error("평가 제출 중 오류", error);
        }
    });
}
//////////////////////////////////////////////////////////	
	// 팔로우
//	followBtn.addEventListener('click', () => {
//		alert('팔로우 되었습니다!');
//	});

	// 쪽지
//	messageBtn.addEventListener('click', () => {
//		alert('쪽지를 보냈습니다!');
//	});

//////////////////////////////////////////////////////
	// 코멘트 기능
	$(document).ready(function() {
		var composerId = $("#commentBtn").data('composer-id');
		// 초기 코멘트 로드
		loadComments(1);
		loadSongs(composerId);

		// 페이지 링크 클릭 이벤트
		$(document).on("click", ".pagination-link", function() {
			event.preventDefault();
			var page = $(this).data("page");
			loadComments(page);
		});

		function loadComments(page) {
			var size = 10;
			$.ajax({
				url: '/comments/' + composerId + '?page=' + page + '&size=' + size,
				type: 'GET',
				success: function(responseDTO) {
					console.log('loadComments 성공');
					console.log('composerId : ' + composerId);
					var comments = responseDTO.comments;
					var totalComments = responseDTO.totalComments;

					displayComments(comments);
					displayPagination(page, Math.ceil(totalComments / size));
				},
				error: function(data) {
					alert('코멘트 로딩 중 오류가 발생했습니다.');
					console.log(data);
				}
			});
		}
		
		
		function loadSongs(composerId){
			console.log("loadSongs");
			
			$.ajax({
			type: 'GET',
			url: '/getComposerMusic',
			data: {
				composer_id: composerId
			},
			success: function(response) {
				outPutSearch(response)
			songLikeCheck(response.song_id)
				console.log(response);
				console.log("음악정보 불러오기 성공");
				
				

				// 가져온 음악 정보를 사용하여 페이지를 업데이트
			},
			error: function(error) {
				console.error('음악정보 불러오기 중 오류가 발생했습니다.');
			}
		});
		}
	

		function displayComments(comments) {
			var commentsContainer = $("#comments-container");
			commentsContainer.empty();

			$.each(comments, function(index, comment) {
				
				commentsContainer.append(
					'<div class="comment-item">' +
					'<div class="user-nickname">' + comment.userNickname + '</div>' +
					'<div class="comment-content">' + comment.comment_content + '</div>' +
					'<div class="created-at">' + convertToKoreanTime(comment.created_at) + '</div>' +
					'<div class="delete-btn" data-comment-id="' + comment.comment_id + '">삭제</div>' +
					'</div>'

				);
			});
/*			    $.each(comments, function(index, comment) {
        // 각 코멘트를 표시하는 HTML을 생성
        var commentHtml = '<div class="comment-item">' +
            '<div class="user-nickname">' + comment.userNickname + '</div>' +
            '<div class="comment-content">' + comment.comment_content + '</div>' +
            '<div class="created-at">' + convertToKoreanTime(comment.created_at) + '</div>';

        // 현재 로그인한 사용자와 코멘트를 작성한 사용자의 전화번호 비교
        if (comment.userFullNumber === userFullNumber) {
            // 같다면 삭제 버튼을 추가
            commentHtml += '<div class="delete-btn" data-comment-id="' + comment.comment_id + '">삭제</div>';
        }

        // HTML을 컨테이너에 추가
        commentHtml += '</div>';
        commentsContainer.append(commentHtml);
    });
	*/		
			
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
			const composerId = $("#commentBtn").data('composer-id');
			const userName = userNickname;
			const commentContent = $("#commentInput").val();

			const comment = {
				composer_id: composerId,
				userNickname: userName,
				comment_content: commentContent
			};
				console.log(composerId);
				console.log(userName);
				console.log(commentContent);

			$.ajax({
				type: "POST",
				url: "/comments",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(comment),
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
		});

		// 코멘트 삭제 버튼 클릭 시 이벤트
		$("#comments-container").on("click", ".delete-btn", function() {
			const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");

			if (isConfirmed) {
				// 클릭된 버튼의 부모 요소에서 comment_id를 가져옴
				const commentId = $(this).data('comment-id');
				$.ajax({
					type: "DELETE",
					url: "/comments/" + commentId,
					success: function() {
						console.log(commentId);
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
});


/* 아티스트 뮤직 리스트 */

async function songLikeCheck(song_id) {
    try {
        const response = await $.ajax({
            type: 'GET',
            url: 'MusicLikeCheckC',
            data: {
                song_id: song_id
            }
        });

        console.log('좋아요 정보 불러오기 성공.');
        console.log(response);

        // 가져온 음악 정보를 사용하여 페이지를 업데이트
        if (response == 1) {
            console.log("리스폰 확인 : " + response)
            // 이미 좋아요한 경우
            return 1;
        } else {
            // 좋아요하지 않은 경우
            return 0;
        }
    } catch (error) {
        console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
        throw error; // 에러를 호출자에게 전파
    }
}

async function outPutSearch(data) {
	let $musicList = $('.musicList-inner');

	// musicList 내용 초기화
	$musicList.empty();

	// 데이터의 각 노래에 대해 반복
	 for (const song of data) {
		// 각 노래를 표시할 HTML 엘리먼트 생성
		let $songContainer = $('<div class="musicList-menu"></div>');

		let $musicListTitle = $('<div class="musicList-title"></div>');
		// 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
		$musicListTitle.append(`
            <div>
                <img src=img/${song.song_img} />
            </div>
            <div>
                <div onclick="location.href='musicDetail?song_id=${song.song_id}'">${song.song_name}</div>
              
                <div onclick="location.href=''">${song.composer_name}</div>
            </div>
        `);

		let $musicListPlaySpace = createAudioPlayer(`audio/${song.song_audio}`);
		// 위에서 생성한 오디오 플레이어를 $musicListPlaySpace에 추가

		let $musicListInfo = $('<div class="musicList-info"></div>');
		$musicListInfo.append(`
            <div>${song.mood_id}</div>
            <div>${song.genre_id}</div>
            <div>${song.instrument_id}</div>
        `);

		let $musicListOption = $('<div class="musicList-option"></div>');
		// 여기서 좋아요 여부에 따라 하트 초기 상태를 설정
		console.log("체크체크! : " +song.song_id);
		const likeCheck = await songLikeCheck(song.song_id);

        console.log("이프문 위 첵라잌" + likeCheck);

        if (likeCheck === 1) {
            console.log("이프문 트루 안 : " + likeCheck);

            $musicListOption.append(`
                <div class="heart-filled" id="heart-${song.song_id}" onclick="songLike(${song.song_id}, this)">♥</div>
            `);
        } else {
            console.log("이프문 엘즈안 : " + likeCheck);
            $musicListOption.append(`
                <div class="heart-filled" id="heart-${song.song_id}" onclick="songLike(${song.song_id}, this)">♡</div>
            `);
        }


		// 각 섹션을 노래 컨테이너에 추가
		$songContainer.append($musicListTitle, $musicListPlaySpace, $musicListInfo, $musicListOption);

		// 노래 컨테이너를 musicList에 추가
		$musicList.append($songContainer);
	};
}

// createAudioPlayer 함수를 사용하여 오디오 플레이어를 생성하는 부분
function createAudioPlayer(audioSrc) {
	let $audioPlayer = $('<div class="audio-player"></div>');

	// 오디오 태그 생성
	let $audio = $('<audio controls></audio>');
	let $source = $('<source src="' + audioSrc + '" type="audio/mp3">');


	// 오디오 태그와 플레이어 UI를 조합하여 플레이어 생성
	$audio.append($source);
	$audioPlayer.append($audio);




	return $audioPlayer;
}

function songLike(song_id, heartElement) {
	console.log(heartElement);

	let user_id = 1;
	// session 에 userid 가져와서 "" 이랑 비교해서 있으면 넘어가게 해야댐 
	if (!(user_id == "")) {
		$.ajax({
			type: 'GET',
			url: 'MusicLikeC',
			data: {
				song_id: song_id
			},
			success: function(response) {
				console.log('좋아요 정보 갱신 성공.');
				console.log(response);
				

				// 가져온 음악 정보를 사용하여 페이지를 업데이트
				if (response == 1) {
					// 이미 좋아요한 경우
					$(heartElement).html('♥');
				} else {
					// 좋아요하지 않은 경우
					$(heartElement).html('♡');
				}
			},
			error: function(error) {
				console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
			}
		});
	}
}


