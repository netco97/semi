document.addEventListener("DOMContentLoaded", function() {
	// 페이지네이션을 위한 현재 페이지와 페이지 크기 변수
	var currentPage = 1;
	var pageSize = 1; // 페이지 크기를 필요에 맞게 조절
	// 전체 페이지 수
	//var totalPages = 12;/* Thymeleaf로부터 받은 값 또는 직접 설정한 값 */;

	console.log('Current Page:', currentPage);
	console.log('Total Pages:', totalPages);
	console.log('Artist List:', artistList);

	// ... (기존 코드)
	loadArtists(currentPage);

	// 페이지에 기반하여 아티스트를 가져오고 표시하는 함수
	function loadArtists(page) {
		// 페이지네이션을 지원하는 아티스트를 가져오기 위한 API 엔드포인트가 있다고 가정
		const url = '/artist_list?page=' + page + '&pageSize=' + pageSize;

		// 아티스트를 가져오기 위한 AJAX 요청
		$.ajax({
			type: "GET",
			url: url,
			success: function(artistMap) {
				console.log('loadArtists 성공');
				console.log(artistMap);

				// 맵을 배열로 변환
				const artistArray = Array.from(artistMap.artistList.values());

				// currentPage, totalPages 변수 추출
				const totalArtists = artistMap.totalArtists;
				const totalPages = artistMap.totalPages;

				console.log(artistArray);
				console.log(totalArtists);
				console.log(totalPages);

				// 아티스트를 artists-container에 표시
				displayArtists(artistArray);

				// 페이지네이션 링크 표시
				displayPagination(currentPage, totalPages);
			},
			error: function(error) {
				console.error("아티스트를 가져오는 중 오류 발생", error);
			}
		});
	}

	// artists-container에 아티스트를 표시하는 함수
	function displayArtists(artists) {
		console.log('displayArtists 호출 성공');
		console.log(artists);
		const artistsContainer = $("#artists-container");
		artistsContainer.empty();

		// 아티스트를 반복하며 컨테이너에 추가
		artists.forEach(artists => {
			// 각 아티스트에 대한 HTML을 생성하고 컨테이너에 추가
			const artistHtml = `
            <div class="artist-container">
                <div class="artist-details">
                <img src="/images/profile/${artists.composer_img}" data-composer-id="${artists.composer_id}">
                    <h3>${artists.composer_name}</h3>
                    <p>장르: ${artists.composer_genre}</p>
                    <p>자기소개: ${artists.composer_text}</p>
                </div>
            </div>
        `;
			artistsContainer.append(artistHtml);
		});

		// 아티스트 이미지 클릭 이벤트 핸들러
		artistsContainer.on("click", "img", function(artistArray) {
			console.log(artistArray);
			// 클릭된 아티스트의 아이디를 가져옴

			const composerId = $(this).data("composer-id");
			console.log(composerId);

			// 클릭된 아티스트의 JSON 데이터 찾기
			const clickedArtist = artists.find(artist => artist.composer_id === composerId);

			// clickedArtist를 활용하여 원하는 작업 수행
			console.log(clickedArtist);

			// 상세 페이지 URL을 생성
			const detailPageUrl = `/artist_detail?userFullPhoneNumber=${composerId}`;

			// 상세 페이지로 이동
			window.location.href = detailPageUrl;
		});
	}

	//////////////////////// 페이지네이션 //////////////////////////

	// 페이지네이션 링크를 표시하는 함수
	function displayPagination(currentPage, totalPages) {
		console.log('displayPagination 호출 성공');
		console.log(currentPage);
		console.log(totalPages);
		const paginationContainer = $("#pagination");
		paginationContainer.empty();

		// ... (기존 페이지네이션 코드)

		var startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
		var endPage = Math.min(totalPages, startPage + 9);

		//if (currentPage > 10) {
		paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (startPage - 1) + '">이전</a>');
		//}

		for (var i = startPage; i <= endPage; i++) {
			var activeClass = (i === currentPage) ? 'active' : '';
			paginationContainer.append('<a href="#" class="pagination-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
		}

		//if (endPage < totalPages) {
		paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (endPage + 1) + '">다음</a>');
		//}

		// 이전 클릭 이벤트를 언바인딩
		paginationContainer.off("click", ".pagination-link");

		// 클릭된 페이지에 대한 아티스트를 로드하도록 이벤트 업데이트
		paginationContainer.on("click", ".pagination-link", function() {
			const clickedPage = $(this).data("page");
			console.log('clickedPage: ' + clickedPage);
			loadArtists(clickedPage);
		});
	}
});