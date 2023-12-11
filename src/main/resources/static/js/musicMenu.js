
function clickTag(tag) {
	let $searchTagInput = $('.musicMenu-searchTagInput');

	// 선택한 태그를 추가 또는 제거
	if ($searchTagInput.find(`#${tag}`).length) {
		// 이미 존재하는 태그인 경우 제거
		$searchTagInput.find(`#${tag}`).remove();
	} else {
		// 존재하지 않는 태그인 경우 추가
		$searchTagInput.append(`<div id='${tag}'>${tag}</div>`);
	}
}



function clickOption(option) {
	let click = [];
	let $searchTag = $('.musicMenu-searchTag');


	if ($searchTag.hasClass('active')) {

		if (option == 'genre' && !($searchTag.hasClass('genre'))) {
			click = ['재즈', '힙합'];
			updateSearchTag(click);
			$searchTag.removeClass('mood');
			$searchTag.removeClass('instrument');

			$searchTag.addClass('genre');
		} else if (option == 'mood' && !($searchTag.hasClass('mood'))) {
			click = ['활기찬', '당당한'];
			updateSearchTag(click);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('instrument');

			$searchTag.addClass('mood');
		} else if (option == 'instrument' && !($searchTag.hasClass('instrument'))) {
			click = ['피아노', '바이올린'];
			updateSearchTag(click);
			$searchTag.removeClass('genre');
			$searchTag.removeClass('mood');

			$searchTag.addClass('instrument');
		} else {
			$searchTag.removeClass('active');
		}

	} else {
		if (option == 'genre') {
			click = ['재즈', '힙합'];
			updateSearchTag(click);
			$searchTag.addClass('genre');
		} else if (option == 'mood') {
			click = ['활기찬', '당당한'];
			updateSearchTag(click);
			$searchTag.addClass('mood');

		} else if (option == 'instrument') {
			click = ['피아노', '바이올린'];
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
		$searchTag.append(`<div id='${tag}' onclick="clickTag('${tag}')">${tag}</div>`);
	});
}












