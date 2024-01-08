


let click = {
	mood: [],
	instrument: [],
	genre: []
};
// 현재 아이팟 페이지 분기만들기 위한 변수
let ipodClickCheck = '';


// 아이팟 click event
$(".screen-inner").on("click", ".ipod-item", function(e) {
	e.stopPropagation();
	// 한번 초기화
	$(".ipod-item").removeClass("active")
	//아이팟페이지 분기를 위해 변수 초기화
	
	// 클래스를 부여함으로써 스타일 활성화

	// 클릭 한 요소에 따라 분기 나누기

	if ($(this).hasClass('hot')) {
		getMusicList('hot')
		ipodClickCheck = '';


	} else if ($(this).hasClass('genre')) {

		updateSearchTag(genre, 'genre');
		ipodClickCheck = '';


	} else if ($(this).hasClass('newMusic')) {
		getMusicList('newMusic')
		ipodClickCheck = '';


	} else if ($(this).hasClass('mood')) {

		updateSearchTag(mood, 'mood');
		ipodClickCheck = '';



	}

})

// 아이팟 뮤직 출력

function ipodMusic(data) {

	$(".screen-inner-ul").empty();


	for (const song of data) {
		// 각 노래를 표시할 HTML 엘리먼트 생성

		// 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
		$(".screen-inner-ul").append(`
			
			<div class="ipod-musicList">
					<div class="ipod-musicList-inner">
						<div class="ipod-musicList-menu">
							<div class="ipod-musicList-title">
            					<div class="ipod-musicList-menu-title-left">
               						 <img src=img/${song.song_img} />
            					<div    style=" margin-left: 10px">
               	 				<div onclick="location.href='musicDetail?song_id=${song.song_id}'">${song.song_name}</div>
              
               						 <div onclick="goToArtistdetail2('${song.userFullPhoneNumber}')">${song.composer_name}</div>
            					</div>
            					</div>
            					</div>
            					<div>
            						 <button onclick="ipodMusicPlayer(${song.song_id})">▶</button>
            					</div>
            	</div>
            	</div>
            	</div>
            	</div>
  
        `);


	};
}

function ipodMusicPlayer(song_id) {
	$.ajax({
		type: 'GET',
		url: 'getMusicDetail', // 실제 서버 엔드포인트 주소로 변경
		data: {
			song_id: song_id
		},
		success: function(response) {
			console.log('태그가 성공적으로 전송되었습니다.');
			console.log(response);

			getMusic(response);


		},
		error: function(error) {
			console.error('태그 전송 중 오류가 발생했습니다.');
		}
	});



}

async function getMusic(song) {

	$('.musicDetail-content-music').empty();

	$('.musicDetail-content-music').append(`
		<div class= "musicDetail-items1">
					<div class="musicDetail-title">
						<div class="musicDetail-title-img"></div>
						<div>
							<div class="musicDetail-title-title"></div>
							<div class="musicDetail-title-composer"></div>
					<!-- 좋아요 하트 -->
					<div class="musicDetail-option">
						<div class="musicDetail-option-inner"></div>
					</div>
						</div>
					</div>
					
					<div class="musicDetail-playSpace">
						<!-- 여기에 오디오 플레이어가 들어갈 것입니다. -->
					</div>
				</div >
		<div class="musicDetail-content-items2">
			<div>
				<div>
					<div>ジャンル</div>
					<div class="genre-area"></div>
				</div>
				<div>
					<div>ムード</div>
					<div class="mood-area"></div>
				</div>
				<div>
					<div>楽器</div>
					<div class="instrument-area"></div>
				</div>
			</div>
		</div>
			</div >


		`)

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
	} else {
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

function updateSearchTag(tags, option) {
	// 기존 태그들을 비우고 선택한 태그들을 추가
	$(".screen-inner-ul").empty();
	console.log('제발1' + tags);
	console.log('제발2' + option)

	tags.forEach(tag => {
		console.log(tag);
		$(".screen-inner-ul").append(`<li class="ipod-item" name='searchTag' id='${tag}' onclick="sendTagsToServer('${tag}','${option}')"><span>＋</span>${tag}</li>`);
	})
}

$(document).ready(function() {
	$(document).on('click', 'div[name="searchTag"]', function() {
		sendTagsToServer();
	});
	$('.musicMenu-searchInput button').on('click', function() {

		// 입력된 검색어
		let searchKeyword = $('#searchInput').val();
		// 검색어를 서버로 전송
		sendTextToServer(searchKeyword);
	});


	// musicMenu 페이지에서 모델에 추가된 tags를 가져와서 click 객체에 할당

});

function sendTagsToServer(tag, option) {
	// 선택된 태그들을 배열에 저장
	let genre = '';
	let mood = ''; // 선택된 분위기 값을 저장할 배열
	let instrument = ''; // 선택된 악기 값을 저장할 배열


	1
	if (option == 'genre') {
		genre = tag;
	} else if (option == 'mood') {
		mood = tag;
	}

	ipodClickCheck = option;



	//센드투서버 각각 펑션에
	//
	//페이징 그거 삽입해주면될듯?
	//
	//ㄹㅇ 깨달아버림 
	//
	//통신 부분에 페이징 그거 삽입 ㄱㄱㄱㄱㄱ
	// AJAX 요청을 통해 서버에 데이터 전송
	$.ajax({
		type: 'POST',
		url: 'MusicSearchTag', // 실제 서버 엔드포인트 주소로 변경
		data: {
			genre: genre, // 'tags' 파라미터에 선택된 태그들을 쉼표로 구분된 문자열로 전송
			mood: mood, // 선택된 분위기들을 쉼표로 구분된 문자열로 전송
			instrument: instrument // 선택된 악기들을 쉼표로 구분된 문자열로 전송
		},
		traditional: true,
		success: function(response) {
			console.log('태그가 성공적으로 전송되었습니다.');
			console.log(response);
			//			let jsonArray = JSON.stringify(response);
			ipodMusic(response);

		},
		error: function(error) {
			console.error('태그 전송 중 오류가 발생했습니다.');
		}
	});

}

function sendTextToServer(text) {
	$.ajax({
		type: 'GET',
		url: 'MusicSearchText', // 실제 서버 엔드포인트로 업데이트
		data: {
			text: text
		},
		traditional: true,
		success: function(response) {
			console.log('텍스트가 성공적으로 전송되었습니다.');
			console.log(response);
			//			let jsonArray = JSON.stringify(response);

		},

		error: function(error) {
			console.error('텍스트 전송 중 오류가 발생했습니다.');
		}
	});
}

function getMusicList(keyWord) {

	$.ajax({
		type: 'GET',
		url: 'GetIpodMusic', // 실제 서버 엔드포인트로 업데이트
		data: {
			keyWord: keyWord
		},
		traditional: true,
		success: function(response) {
			console.log('텍스트가 성공적으로 전송되었습니다.');
			console.log(response);
			ipodMusic(response);

		},

		error: function(error) {
			console.error('텍스트 전송 중 오류가 발생했습니다.');
		}
	});





}
// 뒤로가기 버튼
$("#backBtn").on("click", function() {
	console.log('bbaa' + ipodClickCheck);

	if (ipodClickCheck == 'mood') {

		updateSearchTag(mood,'mood');
	} else if (ipodClickCheck == 'genre') {
		updateSearchTag(genre,'genre');

	} else {
		$(".screen-inner-ul").empty();
		$(".screen-inner-ul").append(`
									<li class="ipod-item hot">지금 핫한곡!</li>
									<li class="ipod-item newMusic">최신곡!</li>
									<li class="ipod-item genre">장르</li>
									<li class="ipod-item mood">분위기</li>
		`)
	}
	ipodClickCheck = '';
	



})











// slider

$(document).ready(function() {
	$('.bxslider').bxSlider({
		minSlides: 12,
		maxSlides: 12,
		slideWidth: 200,
		slideMargin: 1,
		ticker: true,
		speed: 20000
	});



});


function goToArtistdetail2(phonenum) {
	// 폼 동적으로 생성
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', '/artist_detail');

	// 전화번호 입력 필드 추가
	var phoneNumberInput = document.createElement('input');
	phoneNumberInput.setAttribute('type', 'hidden');
	phoneNumberInput.setAttribute('name', 'userFullPhoneNumber');
	phoneNumberInput.setAttribute('value', phonenum);

	// 폼에 입력 필드 추가
	form.appendChild(phoneNumberInput);

	// 폼을 body에 추가하고 서브밋
	document.body.appendChild(form);
	form.submit();
}



