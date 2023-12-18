let click = {
    genre: [],
    mood: [],
    instrument: []
};

function clickOption(option) {
    let $searchTag = $('.musicMenu-searchTag');
    
    if ($searchTag.hasClass('active')) {
        if (option == 'genre' && !($searchTag.hasClass('genre'))) {
            click.genre = ['팝', '댄스', '일레트로닉', '힙합', 'RB', '클래식', '뉴에이지', '락', '발라드', '인디', '재즈/스윙', '라틴', '국악', '월드뮤직', '앰비언트', '트로트', '기타', '바보'];
            updateSearchTag(click.genre);
            $searchTag.removeClass('mood');
            $searchTag.removeClass('instrument');
            $searchTag.addClass('genre');
        } else if (option == 'mood' && !($searchTag.hasClass('mood'))) {
            click.mood = ['밝은', '신나는', '경쾌한', '행복한', '청량한', '귀여운', '코믹한', '희망찬', '로맨틱', '사랑스러운', '슬픈', '어두운', '우울한', '쓸쓸한', '아련한', '잔잔한', '웅장한', '비장한', '긴장감', '화난', '박력', '공포', '몽환적인', '신비로운', '나른한', '미래적인', '그루브', '섹시한', '단순한', '중독성', '난해한', '이국적인', '한국적인'];
            updateSearchTag(click.mood);
            $searchTag.removeClass('genre');
            $searchTag.removeClass('instrument');
            $searchTag.addClass('mood');
        } else if (option == 'instrument' && !($searchTag.hasClass('instrument'))) {
            click.instrument = ['기타', '피아노', '베이스', '바이올린', '드럼', '첼로', '그외'];
            updateSearchTag(click.instrument);
            $searchTag.removeClass('genre');
            $searchTag.removeClass('mood');
            $searchTag.addClass('instrument');
        } else {
            $searchTag.removeClass('active');
        }
    } else {
        if (option == 'genre') {
            click.genre = ['팝', '댄스', '일레트로닉', '힙합', 'RB', '클래식', '뉴에이지', '락', '발라드', '인디', '재즈/스윙', '라틴', '국악', '월드뮤직', '앰비언트', '트로트', '기타', '바보'];
            updateSearchTag(click.genre);
            $searchTag.addClass('genre');
        } else if (option == 'mood') {
            click.mood = ['밝은', '신나는', '경쾌한', '행복한', '청량한', '귀여운', '코믹한', '희망찬', '로맨틱', '사랑스러운', '슬픈', '어두운', '우울한', '쓸쓸한', '아련한', '잔잔한', '웅장한', '비장한', '긴장감', '화난', '박력', '공포', '몽환적인', '신비로운', '나른한', '미래적인', '그루브', '섹시한', '단순한', '중독성', '난해한', '이국적인', '한국적인'];
            updateSearchTag(click.mood);
            $searchTag.addClass('mood');
        } else if (option == 'instrument') {
            click.instrument = ['기타', '피아노', '베이스', '바이올린', '드럼', '첼로', '그외'];
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
        $searchTag.append(`<div name='searchTag' id='${tag}' onclick="clickTag('${tag}')">${tag}</div>`);
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
        $searchTagInput.append(`<div onclick="deleteTag('${tag}')" name='searchTagInput' id='${tag}'>${tag}</div>`);
        // 배열에 추가
        selectedTags.push(tag);
    }
}
function deleteTag(tag) {
    $('.musicMenu-searchTagInput').find(`#${tag}`).remove();
    sendTagsToServer();
}



$(document).ready(function () {
    $(document).on('click', 'div[name="searchTag"]', function () {
        sendTagsToServer();
    });
    $('.musicMenu-searchInput button').on('click', function () {
		
        // 입력된 검색어
        let searchKeyword = $('#searchInput').val();
        // 검색어를 서버로 전송
        sendTextToServer(searchKeyword);
    });
    
});

function sendTagsToServer() {
    // 선택된 태그들을 배열에 저장
    let selectedGenre = [];
    let selectedMood = []; // 선택된 분위기 값을 저장할 배열
    let selectedInstrument = []; // 선택된 악기 값을 저장할 배열
    

    $('.musicMenu-searchTagInput div').each(function () {
        const tag = $(this).text();
        

        // 여기서 분위기와 악기를 구분하여 배열에 추가
        if (click.mood.includes(tag)) {
            selectedMood.push(tag);
        } else if (click.instrument.includes(tag)) {
            selectedInstrument.push(tag);
        } else if (click.genre.includes(tag)){
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
        success: function (response) {
            console.log('태그가 성공적으로 전송되었습니다.');
            console.log(response);
            outPutSearch(response);
        },
        error: function (error) {
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
        success: function (response) {
            console.log('텍스트가 성공적으로 전송되었습니다.');
            console.log(response);
            outPutSearch(response);
        },
        
        error: function (error) {
            console.error('텍스트 전송 중 오류가 발생했습니다.');
        }
    });
}
function outPutSearch(data) {
    let $musicList = $('.musicList-inner');

    // musicList 내용 초기화
    $musicList.empty();

    // 데이터의 각 노래에 대해 반복
    data.forEach(song => {
        // 각 노래를 표시할 HTML 엘리먼트 생성
        let $songContainer = $('<div class="musicList-menu"></div>');

        let $musicListTitle = $('<div class="musicList-title"></div>');
        // 추후 컴포져 네임 div 에 컴포져 상세페이지 가는 링크 걸어야함
        $musicListTitle.append(`
            <div>
                <img src=img/${song.song_img} />
            </div>
            <div>
                <div onclick="location.href='musicDetail?songs_id=${song.song_id}'">${song.song_name}</div>
              
                <div onclick="location.href=''">${song.composer_name}</div>
            </div>
        `);

        let $musicListPlaySpace = createAudioPlayer(`audio/${song.song_audio}`);
        // 위에서 생성한 오디오 플레이어를 $musicListPlaySpace에 추가

        let $musicListInfo = $('<div class="musicList-info"></div>');
        $musicListInfo.append(`
            <div>${song.mood_id}</div>
            <div>${song.genre_id}</div>
        `);

        let $musicListOption = $('<div class="musicList-option"></div>');
        $musicListOption.append(`
            <div>♥</div>
            <div>☆</div>
            <div style="font-size: 10px">좋댓구</div>
        `);

        // 각 섹션을 노래 컨테이너에 추가
        $songContainer.append($musicListTitle, $musicListPlaySpace, $musicListInfo, $musicListOption);

        // 노래 컨테이너를 musicList에 추가
        $musicList.append($songContainer);
    });
}

// createAudioPlayer 함수를 사용하여 오디오 플레이어를 생성하는 부분
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

    

    $audio.on('timeupdate', function () {
        // 재생 시간이 업데이트될 때마다 호출되는 이벤트
        let percentage = ($audio[0].currentTime / $audio[0].duration) * 200;
        $progressBar.css('width', percentage + '%');
    });

    return $audioPlayer;
}




