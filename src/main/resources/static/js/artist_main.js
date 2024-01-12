
document.addEventListener("DOMContentLoaded", function() {


	var pageSize = 9;
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
				console.log(artistMap);
				allArtists = artistMap.artistList;
				//allArtists = artistMap.artistList;
				console.log(artistMap.totalArtists);
				artists = artistMap.totalArtists;
				currentPage = 1;
				loadArtists(currentPage, artists); // 현재 페이지에 해당하는 아티스트를 가져오기
			},
			error: function(error) {
				console.error("아티스트를 가져오는 중 오류 발생", error);
			}
		});
	}

	function loadArtists(page, artists) {
		console.log("현재 페이지 :" + page);
		console.log(artists);
		//const totalPages = Math.ceil(allArtists.length / pageSize);
		const totalPages = Math.ceil(allArtists.length / pageSize);
		const startIndex = (page - 1) * pageSize;
		const endIndex = Math.min(startIndex + pageSize, allArtists.length);
		const artistList = allArtists.slice(startIndex, endIndex);
		
		console.log(artistList);
		displayArtists(artistList);
		displayPagination(currentPage, totalPages);
	}
	
	function formatGenre(genres) {
    if (!genres || genres.length === 0) {
        return "Unknown";
    }
    
    const individualGenres = genres.split(',');

    const formattedGenres = individualGenres.map(genre => {
        switch (genre.trim()) {
            case "pop":
                return "ポップ";
            case "dance":
                return "ダンス";
            case "electronic":
                return "エレクトロニック";
            case "hiphop":
            	return "ヒップホップ";
            case "rnb":
            	return "R&B";
            case "classic":
            	return "クラシック";
            case "newage":
            	return "ニューエイジ";
            case "rock":
            	return "ロック";
            case "ballad":
            	return "バラード";
            case "indie":
            	return "インディ";
            case "jazz_swing":
            	return "ジャズ／スウィング";
            case "latin":
            	return "ラテン";
            case "korean":
            	return "伝統音楽";
            case "world":
            	return "ワールドミュージック";
            case "ambient":
            	return "アンビエント";
            case "trot":
            	return "演歌";
            case "etc":
            	return "その他";
            // ... 다른 장르들에 대한 처리 ...
            default:
                return genre.trim(); // 기본적으로는 변환하지 않고 원래 값 사용
        }
    });

    return formattedGenres.join(", ");
}

	function displayArtists(artists) {
		const artistsContainer = $("#artists-container");
		artistsContainer.empty();

		artists.forEach(artist => {
			const formattedGenre = formatGenre(artist.composer_genre);
			const artistHtml = `
                <div class="artist-container">
                    <div class="artist-details" data-composer-id="${artist.composer_id}">
                        <img src="/images/profile/${artist.composer_img}"
                        onerror="this.src='/images/profile/default_profile.png'">
                        <div class="artist-details-word">
                            <h3>${artist.composer_name}</h3>
                            <p>${formattedGenre}</p>
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
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (currentPage - 1) + '">前へ</a>');
		} else {
			paginationContainer.append('<span class="pagination-link disabled">前へ</span>');
		}

		for (var i = startPage; i <= endPage; i++) {
			var activeClass = (i === currentPage) ? 'active' : '';
			paginationContainer.append('<a href="#" class="pagination-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
		}

		if (currentPage < totalPages) {
			paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (currentPage + 1) + '">次へ</a>');
		} else {
			paginationContainer.append('<span class="pagination-link disabled">次へ</span>');
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

