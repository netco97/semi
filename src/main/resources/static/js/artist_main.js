document.addEventListener("DOMContentLoaded", function() {

	var pageSize = 5;
	var currentPage = 1;
	var allArtists = []; // 모든 아티스트를 저장하는 변수

	// 초기에 모든 아티스트를 가져오기
	loadAllArtists();

	function loadAllArtists(query = "") {
		const url = '/artist_list' + (query ? `?query=${query}` : '');
		$.ajax({
			type: "GET",
			url: url,

			success: function(artistMap) {
				allArtists = artistMap.artistList;
				currentPage = 1;
				loadArtists(currentPage); // 현재 페이지에 해당하는 아티스트를 가져오기
			},
			error: function(error) {
				console.error("아티스트를 가져오는 중 오류 발생", error);
			}
		});
	}

	function loadArtists(page) {
		const totalPages = Math.ceil(allArtists.length / pageSize);

		const startIndex = (page - 1) * pageSize;
		const endIndex = Math.min(startIndex + pageSize, allArtists.length);
		const artistList = allArtists.slice(startIndex, endIndex);

		displayArtists(artistList);
		displayPagination(currentPage, totalPages);
	}

	function displayArtists(artists) {
		const artistsContainer = $("#artists-container");
		artistsContainer.empty();

		artists.forEach(artist => {
			const artistHtml = `
                <div class="artist-container">
                    <div class="artist-details" data-composer-id="${artist.composer_id}">
                        <img src="/images/profile/${artist.composer_img}">
                        <div class="artist-details-word">
                            <h3>${artist.composer_name}</h3>
                            <p>${artist.composer_genre}</p>
                            <p>${artist.composer_text}</p>
                        </div>
                    </div>
                </div>
            `;
			artistsContainer.append(artistHtml);
		});


		//폰넘버 감추기
		artistsContainer.on("click", ".artist-details", function() {
			const composerId = $(this).data("composer-id");
			const detailPageUrl = "/artist_detail";

			// 폼 동적 생성
			const form = $('<form>', {
				'action': detailPageUrl,
				'method': 'post',
				'style': 'display:none;'
			});

			// input 추가
			$('<input>').attr({
				'type': 'hidden',
				'name': 'userFullPhoneNumber',
				'value': composerId
			}).appendTo(form);

			// 폼을 body에 추가하고 submit
			form.appendTo('body').submit();
		});




	}

	function displayPagination(currentPage, totalPages) {
		const paginationContainer = $("#pagination");
		paginationContainer.empty();

		var startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
		var endPage = Math.min(totalPages, startPage + 9);

		if (currentPage > 1) {
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (currentPage - 1) + '">이전</a>');
		} else {
			paginationContainer.append('<span class="pagination-link disabled">이전</span>');
		}

		for (var i = startPage; i <= endPage; i++) {
			var activeClass = (i === currentPage) ? 'active' : '';
			paginationContainer.append('<a href="#" class="pagination-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
		}

		if (currentPage < totalPages) {
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (currentPage + 1) + '">다음</a>');
		} else {
			paginationContainer.append('<span class="pagination-link disabled">다음</span>');
		}
	}

	$("#pagination").on("click", ".pagination-link:not(.disabled):not(.active)", function() {
		const clickedPage = $(this).data("page");
		currentPage = clickedPage;
		loadArtists(currentPage);
	});

	// 검색 기능 추가
	$("#searchInput").on("input", function() {
		const query = $(this).val();
		loadAllArtists(query);
	});
});

