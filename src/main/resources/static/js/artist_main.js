$(document).ready(function () {
    // 페이지 로드 후 실행할 내용

    // 초기 페이지 로딩 시 아티스트 목록을 가져와서 표시
    loadArtists();

    // 검색 버튼 클릭 시 아티스트 목록을 다시 가져와서 표시
    $("#searchInput").on("input", function () {
        loadArtists();
    });
});

function loadArtists() {
    // 현재 페이지와 페이지 사이즈를 가져옴
    var currentPage = 1; // 현재 페이지 번호, 필요에 따라 수정
    var pageSize = 10; // 페이지당 표시할 아티스트 수, 필요에 따라 수정

    // 검색어를 가져옴
    var searchKeyword = $("#searchInput").val();

    // 비동기적으로 아티스트 목록을 가져오는 Ajax 요청
    $.ajax({
        type: "GET",
        url: "/api/artists", // 아티스트 목록을 반환하는 API 엔드포인트, 필요에 따라 수정
        data: {
            page: currentPage,
            pageSize: pageSize,
            searchKeyword: searchKeyword
        },
        success: function (data) {
            // 성공적으로 데이터를 가져왔을 때 아티스트 목록을 업데이트
            updateArtistList(data);
        },
        error: function (error) {
            console.error("Error fetching artists: ", error);
        }
    });
}

function updateArtistList(artistList) {
    // 아티스트 목록을 받아와서 동적으로 HTML을 생성하여 페이지에 추가
    var artistListContainer = $("#artistListContainer");
    artistListContainer.empty(); // 현재 아티스트 목록을 지우고 새로운 목록으로 갱신

    // artistList를 순회하면서 각 아티스트에 대한 HTML을 생성하고 페이지에 추가
    $.each(artistList, function (index, artist) {
        var artistHtml = '<a href="/artist_detail/' + artist.composer_id + '">';
        artistHtml += '<img src="/images/profile/' + artist.getImgOrDefault() + '" alt="Artist Profile">';
        artistHtml += '<span>' + artist.composer_name + '</span>';
        artistHtml += '</a>';

        // 생성된 HTML을 아티스트 목록 컨테이너에 추가
        artistListContainer.append(artistHtml);
    });
}


//    // 페이지네이션을 위한 현재 페이지와 페이지 크기 변수
//    var currentPage = 1;
//    var pageSize = 10; // 페이지 크기를 필요에 맞게 조절
//
//    // 페이지가 로드될 때 실행되는 함수
//    $(document).ready(function() {
//        loadArtists(currentPage);
//
//        // 이전 페이지로 이동하는 버튼
//        $("#prevBtn").click(function() {
//            if (currentPage > 1) {
//                currentPage--;
//                loadArtists(currentPage);
//            }
//        });
//
//        // 다음 페이지로 이동하는 버튼
//        $("#nextBtn").click(function() {
//            // 여기에 전체 페이지 수를 구하는 로직이 필요하면 추가할 수 있습니다.
//            // 예: var totalPages = ...;
//
//            if (currentPage < totalPages) {
//                currentPage++;
//                loadArtists(currentPage);
//            }
//        });
//    });
//
//    // 아티스트 목록을 가져오고 화면에 표시하는 함수
//    function loadArtists(page) {
//        $.ajax({
//            url: "/artist_main", // Spring Controller에 맞게 경로 설정
//            type: "GET",
//            data: { page: page, pageSize: pageSize },
//            success: function(data) {
//                // 성공 시 아티스트 목록을 표시하는 부분
//                // data를 이용하여 목록을 동적으로 생성하는 코드 작성
//            },
//            error: function(xhr, status, error) {
//                console.error("Failed to load artists. Error: " + error);
//            }
//        });
//    }