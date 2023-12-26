// 페이지 로드 시 실행
$(document).ready(function() {
	// 페이지 로드 시, URL에서 음악 ID를 추출
	const urlParams = new URLSearchParams(window.location.search);
	const songId = urlParams.get('song_id');

	// 음악 ID를 사용하여 음악 상세 페이지를 로드
	loadMusicDetailPage(songId);
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
function updateMusicDetailPage(song) {
	console.log('업데이트뮤직디테일페이지')

	// 음악 타이틀 및 작곡가 정보 업데이트
	$('.musicDetail-title-img').append(`<img src=img/${song[0].song_img}>`)
	$('.musicDetail-title-title').append(`<div class="musicDetail-title-title">${song[0].song_name}</div>`);
	// 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
	$('.musicDetail-title-composer').append(`<div onclick="location.href=''" class="musicDetail-title-composer">${song[0].composer_name}</div>`);
	// 여기에서 좋아요 여부를 확인하고 하트 표시를 결정
	songLike(song[0].song_id);

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

function songLike(song_id) {
	console.log('송라이크');

	let user_id = 1;
	//session 에 userid 가져와서 "" 이랑 비교해서 있으면 넘어가게 해야댐 
	if (!(user_id == "")) {


		$.ajax({
			type: 'GET',
			url: 'MusicLikeC', // 실제 서버 엔드포인트 주소로 변경
			data: {
				song_id: song_id
			},
			success: function(response) {
				console.log('좋아요 정보 갱신 성공.');
				console.log(response);

				// 기존에 추가된 하트 모두 제거
				$('.musicDetail-option').empty();

				// 가져온 음악 정보를 사용하여 페이지를 업데이트
				if (response == 1) {
					// 이미 좋아요한 경우
					$('.musicDetail-option').append(`
                        <div class="heart-filled" onclick="songLike(${song_id})">♥</div>
                        <div>평점</div>
                        <div>즐겨찾기</div>
                        <div>공유하기</div>`);
				} else {
					// 좋아요하지 않은 경우
					$('.musicDetail-option').append(`
                        <div class="heart-filled" onclick="songLike(${song_id})">♡</div>
                        <div>평점</div>
                        <div>즐겨찾기</div>
                        <div>공유하기</div>`);
				}
			},
			error: function(error) {
				console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
			}
		});

	}
}



