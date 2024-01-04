

let click = {
	mood: [],
	instrument: [],
	genre: []
};


// 아이팟 click event
$(".ipod-item").on("click", function(e) {
	// 한번 초기화
	$(".ipod-item").removeClass("active")
	// 클래스를 부여함으로써 스타일 활성화

	// 클릭 한 요소에 따라 분기 나누기

	if ($(this).hasClass('hot')) {


	} else if ($(this).hasClass('genre')) {
		updateSearchTag(genre);


	} else if ($(this).hasClass('newMusic')) {

	} else if ($(this).hasClass('mood')) {


	}


})

// 아이팟 뮤직 출력

function ipodMusic(data) {

	$(".screen-inner-ul").empty();


	for (const song of data) {
		// 각 노래를 표시할 HTML 엘리먼트 생성

		// 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
		$(".screen-inner-ul").append(`
			
			<div class="musicList">
					<div class="musicList-inner">
						<div class="musicList-menu">
							<div class="musicList-title">
            	<div>
               	 <img src=img/${song.song_img} />
            	</div>
            	<div>
               	 <div onclick="location.href='musicDetail?song_id=${song.song_id}'">${song.song_name}</div>
              
               	 <div onclick="location.href=''">${song.composer_name}</div>
            	</div>
            	<div>
            	<button>▶</button>
            	</div>
            	</div>
            	</div>
            	</div>
            	</div>
  
        `);

	};
}










function updateSearchTag(tags) {
	// 기존 태그들을 비우고 선택한 태그들을 추가
	$(".screen-inner-ul").empty();
	console.log(tags);
	tags.forEach(tag => {
		console.log(tag);
		$(".screen-inner-ul").append(`<li class="ipod-item" name='searchTag' id='${tag}' onclick="sendTagsToServer('${tag}')"><span>＋</span>${tag}</li>`);
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

function sendTagsToServer() {
	// 선택된 태그들을 배열에 저장
	let genre = '';
	let mood = ''; // 선택된 분위기 값을 저장할 배열
	let instrument = ''; // 선택된 악기 값을 저장할 배열


	$('.musicMenu-searchTagInput div').each(function() {
		const tag = $(this).text();


		// 여기서 분위기와 악기를 구분하여 배열에 추가
		if (click.mood.includes(tag)) {
			mood = tag;
		} else if (click.instrument.includes(tag)) {
			instrument = tag;
		} else if (click.genre.includes(tag)) {
			genre = tag;

		}
	});


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
			pagination(response);
		},

		error: function(error) {
			console.error('텍스트 전송 중 오류가 발생했습니다.');
		}
	});
}


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


	// session 에 userid 가져와서 "" 이랑 비교해서 있으면 넘어가게 해야댐 
	if (isLogin == 1) {
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
	} else {
		alert('로그인 후 이용 가능합니다')
	}
}









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


