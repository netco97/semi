console.log("genre" + genre);
console.log("mood" + mood);
console.log("instrument" + instrument);

let click = {
	mood: [],
	instrument: [],
	genre: []
};


function clickOption(option) {
	let $searchTag = $('.musicMenu-searchTag');

	if ($searchTag.hasClass('active')) {
		if (option == 'genre' && !($searchTag.hasClass('genre'))) {
			click.genre = genre;
			updateSearchTag(click.genre);
			$searchTag.removeClass('mood');
			$searchTag.removeClass('instrument');
			$searchTag.addClass('genre');
		} else if (option == 'mood' && !($searchTag.hasClass('mood'))) {
			click.mood = mood;

			updateSearchTag(click.mood);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('instrument');
			$searchTag.addClass('mood');
		} else if (option == 'instrument' && !($searchTag.hasClass('instrument'))) {
			click.instrument = instrument;
			updateSearchTag(click.instrument);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('mood');
			$searchTag.addClass('instrument');
		} else {
			$searchTag.removeClass('active');
		}
	} else {
		if (option == 'genre') {
			click.genre = genre;
			updateSearchTag(click.genre);
			$searchTag.addClass('genre');
		} else if (option == 'mood') {
			click.mood = mood;
			updateSearchTag(click.mood);
			$searchTag.addClass('mood');
		} else if (option == 'instrument') {
			click.instrument = instrument;
			updateSearchTag(click.instrument);
			$searchTag.addClass('instrument');
		}
		$searchTag.addClass('active');
	}
}

function updateSearchTag(tags) {
	let $searchTag = $('.musicMenu-searchTag');
	// 기존 태그들을 비우고 선택한 태그들을 추가
	$searchTag.empty();
	console.log(tags);
	tags.forEach(tag => {
		console.log(tag);
		$searchTag.append(`<div name='searchTag' id='${tag}' onclick="clickTag('${tag}')"><span>＋</span>${tag}</div>`);
	});
}

let selectedTags = [];

function clickTag(tag) {
	let $searchTagInput = $('.musicMenu-searchTagInput');
	// 선택한 태그를 추가 또는 제거
	if ($searchTagInput.find(`#${tag}`).length) {
		// 이미 존재하는 태그인 경우 제거
		$searchTagInput.find(`#${tag}`).remove();
		// 배열에서도 제거
		selectedTags = selectedTags.filter(selectedTag => selectedTag !== tag);
	} else {
		// 존재하지 않는 태그인 경우 추가
		$searchTagInput.append(`<div onclick="deleteTag('${tag}')" name='searchTagInput' id='${tag}'><span></span>${tag}</div>`);
		// 배열에 추가
		selectedTags.push(tag);
	}
}
function deleteTag(tag) {
	$('.musicMenu-searchTagInput').find(`#${tag}`).remove();
	sendTagsToServer();
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
	let selectedGenre = [];
	let selectedMood = []; // 선택된 분위기 값을 저장할 배열
	let selectedInstrument = []; // 선택된 악기 값을 저장할 배열


	$('.musicMenu-searchTagInput div').each(function() {
		const tag = $(this).text();


		// 여기서 분위기와 악기를 구분하여 배열에 추가
		if (click.mood.includes(tag)) {
			selectedMood.push(tag);
		} else if (click.instrument.includes(tag)) {
			selectedInstrument.push(tag);
		} else if (click.genre.includes(tag)) {
			selectedGenre.push(tag);

		}
	});

	// AJAX 요청을 통해 서버에 데이터 전송
	$.ajax({
		type: 'GET',
		url: 'MusicSearchTag', // 실제 서버 엔드포인트 주소로 변경
		data: {
			genre: selectedGenre.join('#'), // 'tags' 파라미터에 선택된 태그들을 쉼표로 구분된 문자열로 전송
			mood: selectedMood.join('#'), // 선택된 분위기들을 쉼표로 구분된 문자열로 전송
			instrument: selectedInstrument.join('#') // 선택된 악기들을 쉼표로 구분된 문자열로 전송
		},
		traditional: true,
		success: function(response) {
			console.log('태그가 성공적으로 전송되었습니다.');
			console.log(response);
			outPutSearch(response);
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
		success: function(response) {
			console.log('텍스트가 성공적으로 전송되었습니다.');
			console.log(response);
			outPutSearch(response);
		},

		error: function(error) {
			console.error('텍스트 전송 중 오류가 발생했습니다.');
		}
	});
}

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
//
//function songLikeCheck(song_id){
//	
//	$.ajax({
//			type: 'GET',
//			url: 'MusicLikeCheckC',
//			data: {
//				song_id: song_id
//			},
//			success: function(response) {
//				console.log('좋아요 정보 불러오기 성공.');
//				console.log(response);
//				
//
//				// 가져온 음악 정보를 사용하여 페이지를 업데이트
//				if (response == 1) {
//					console.log("리스폰 확인 : "+ response)
//					// 이미 좋아요한 경우
//					return 1;
//				} else {
//					// 좋아요하지 않은 경우
//					return 0;
//				}
//			},
//			error: function(error) {
//				console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
//			}
//		});
//	
//	
//	
//}

//function songLike(song_id) {
//	console.log('송라이크');
//
//	let user_id = 1;
//	//session 에 userid 가져와서 "" 이랑 비교해서 있으면 넘어가게 해야댐
//	if (!(user_id == "")) {
//
//
//		$.ajax({
//			type: 'GET',
//			url: 'MusicLikeC', // 실제 서버 엔드포인트 주소로 변경
//			data: {
//				song_id: song_id
//			},
//			success: function(response) {
//				console.log('좋아요 정보 갱신 성공.');
//				console.log(response);
//
//				// 기존에 추가된 하트 모두 제거
//				$('.heart-filled').filter(`[id="${song_id}"]`).empty();
//
//				// 가져온 음악 정보를 사용하여 페이지를 업데이트
//				if (response == 1) {
//					// 이미 좋아요한 경우
//					$('.heart-filled').filter(`[id="${song_id}"]`).append(`
//                        <div class="heart-filled" id="${song_id}" onclick="songLike(${song_id})">♥</div>
//                       `);
//				} else {
//					// 좋아요하지 않은 경우
//					$('.heart-filled').append(`
//                        <div class="heart-filled"  id="${song_id}" onclick="songLike(${song_id})">♡</div>
//                       `);
//				}
//			},
//			error: function(error) {
//				console.error('좋아요 정보를 가져오는 중 오류가 발생했습니다.');
//			}
//		});
//
//	}
//}




