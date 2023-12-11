
function clickOption(option) {
	let click = [];
	let $searchTag = $('.musicMenu-searchTag');
	let converter = [];


	if ($searchTag.hasClass('active')) {

		if (option == 'genre' && !($searchTag.hasClass('genre'))) {
			click = ['jazz', 'hiphop'];
			updateSearchTag(click);
			$searchTag.removeClass('mood');
			$searchTag.removeClass('instrument');

			$searchTag.addClass('genre');
		} else if (option == 'mood' && !($searchTag.hasClass('mood'))) {
			click = ['Lively', 'Confident'];
			updateSearchTag(click);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('instrument');

			$searchTag.addClass('mood');
		} else if (option == 'instrument' && !($searchTag.hasClass('instrument'))) {
			click = ['Piano', 'Violin'];
			updateSearchTag(click);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('mood');

			$searchTag.addClass('instrument');
		} else {
			$searchTag.removeClass('active');
		}

	} else {
		if (option == 'genre') {
			click = ['jazz', 'hiphop'];
			updateSearchTag(click);
			$searchTag.addClass('genre');
		} else if (option == 'mood') {
			click = ['Lively', 'Confident'];
			updateSearchTag(click);
			$searchTag.addClass('mood');

		} else if (option == 'instrument') {
			click = ['Piano', 'Violin'];
			updateSearchTag(click);
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
		converter = tag;

		$searchTag.append(`<div name='searchTag' id='${tag}' onclick="clickTag('${tag}')">${tag}</div>`);
	});
}

//

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
		$searchTagInput.append(`<div onclick="deleteTag(${tag})" name='searchTagInput' id='${tag}'>${tag}</div>`);
		// 배열에 추가
		selectedTags.push(tag);
	}
}
$(document).ready(function() {

	$(document).on('click', 'div[name="searchTag"]', function() {
		sendTagsToServer();
	});
});

function sendTagsToServer() {
	// 선택된 태그들을 배열에 저장
	let selectedTags = [];
	$('.musicMenu-searchTagInput div').each(function() {
		selectedTags.push($(this).text());
	});

	// AJAX 요청을 통해 서버에 데이터 전송
	$.ajax({
		type: 'GET',
		url: 'MusicSearchTag', // 실제 서버 엔드포인트 주소로 변경
		data: { tags: selectedTags },
		traditional: true,
		success: function(response) {
			console.log('태그가 성공적으로 전송되었습니다.');
			console.log(response);
		},
		error: function(error) {
			console.error('태그 전송 중 오류가 발생했습니다.');
		}
	});
}

function deleteTag(tag){
	$('.musicMenu-searchTagInput').find(tag).remove();
	sendTagsToServer();
	
}











