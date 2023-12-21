document.addEventListener("DOMContentLoaded", function () {
    
    // 페이지를 변경하기 전에 현재 스크롤 위치 저장
    function storeScrollPosition() {
        sessionStorage.setItem("scrollPosition", window.scrollY);
    }

    // 새 콘텐츠가 로드된 후에 스크롤 위치 복원
    function restoreScrollPosition() {
        const scrollPosition = sessionStorage.getItem("scrollPosition");
        if (scrollPosition) {
            window.scrollTo(0, parseInt(scrollPosition));
            sessionStorage.removeItem("scrollPosition");
        }
    }

    // 페이지 번호를 클릭하는 이벤트에 스크롤 위치 저장 함수 추가
    document.querySelectorAll('.pagination-link').forEach(link => {
        link.addEventListener('click', () => {
            storeScrollPosition();
        });
    });

    // 콘텐츠가 로드된 후에 스크롤 위치를 복원하는 이벤트 리스너 추가
    window.addEventListener('load', () => {
        restoreScrollPosition();
    });

    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('rating-value');
    const followBtn = document.getElementById('followBtn');
    const messageBtn = document.getElementById('messageBtn');

    stars.forEach(star => {
        // 별 클릭 시 이벤트
        star.addEventListener('click', () => {
            const value = parseInt(star.getAttribute('data-value'));
            ratingValue.innerText = `평가: ${value}점`;
            removeActiveStars();
            setActiveStars(value);
        });

        // 별에 마우스 오버 시 이벤트
        star.addEventListener('mouseover', () => {
            const value = parseInt(star.getAttribute('data-value'));
            removeActiveStars();
            setActiveStars(value);
        });

        // 별에 마우스 아웃 시 이벤트
        star.addEventListener('mouseout', () => {
            removeActiveStars();
        });
    });

    // 활성 별 제거
    function removeActiveStars() {
        stars.forEach(star => {
            star.classList.remove('active');
        });
    }

    // 지정된 수의 별을 활성화
    function setActiveStars(value) {
        for (let i = 0; i < value; i++) {
            stars[i].classList.add('active');
        }
    }

    // 팔로우 버튼 클릭 시 이벤트
    followBtn.addEventListener('click', () => {
        alert('팔로우 되었습니다!');
    });

    // 쪽지 보내기 버튼 클릭 시 이벤트
    messageBtn.addEventListener('click', () => {
        alert('쪽지를 보냈습니다!');
    });

    $(document).ready(function () {
        // 코멘트 작성 버튼 클릭 시 이벤트
        $("#commentBtn").on("click", function () {
            // 작성한 코멘트 내용 가져오기
            const composerId = $("#commentBtn").data('composer-id');
            const userName = $("#commentBtn").data('user-name');
            const commentContent = $("#commentInput").val();

            // CommentDTO 객체 생성
            const comment = {
                composerId: composerId,
                userName: userName,
                commentContent: commentContent
            };

            // Ajax를 통해 서버에 코멘트 추가 요청
            $.ajax({
                type: "POST",
                url: "/comments",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(comment),
                success: function () {
                    // 코멘트 추가 성공 시, 화면 갱신 등 필요한 작업 수행
                    console.log("코멘트 추가 성공");
                    $("#commentInput").val("");
                    //location.reload();

                    // 현재 URL을 가져오고 쿼리 매개변수를 제거합니다.
                    const currentUrl = window.location.href.split('?')[0];

                    // 업데이트된 댓글이 표시된 아티스트 페이지로 리다이렉트합니다.
                    window.location.href = currentUrl;
                },
                error: function (error) {
                    console.error("코멘트 추가 실패", error);
                }
            });
        });

        // 코멘트 삭제 버튼 클릭 시 이벤트
        $(".comment-container-inner").on("click", "#delete-btn", function () {
            const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");

            // 사용자가 확인을 선택한 경우에만 삭제 요청 보내기
            if (isConfirmed) {
                const commentId = $(this).data("commentId");

                // Ajax를 통해 서버에 코멘트 삭제 요청
                $.ajax({
                    type: "DELETE",
                    url: "/comments/" + commentId,
                    success: function () {
                        // 코멘트 삭제 성공 시, 화면 갱신 등 필요한 작업 수행
                        console.log("코멘트 삭제 성공");
                        location.reload();
                    },
                    error: function (error) {
                        console.error("코멘트 삭제 실패", error);
                    }
                })
            }
        });
    });

	// 페이지 번호를 클릭하는 이벤트에 스크롤 위치 저장 함수 추가
	document.querySelectorAll('.pagination-link').forEach(link => {
	    link.addEventListener('click', () => {
	        const pageNumber = link.getAttribute('data-pageNumber'); // 페이지 번호 가져오기
	        updateCommentList(pageNumber); // updateCommentList 호출
	        
	        // 스크롤 위치를 부드럽게 이동
        	document.getElementById('commentTitle').scrollIntoView({ behavior: 'smooth', block: 'start' });
	        
	        // 모든 링크에서 'active' 클래스 제거
	        document.querySelectorAll('.pagination-link').forEach(otherLink => {
	            otherLink.classList.remove('active');
	        });
	
	        // 클릭된 링크에 'active' 클래스 추가
	        link.classList.add('active');
		});
	});
    
});

	    function previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updateCommentList(currentPage);
	        }
	    }

	    function nextPage() {
	        if (currentPage < totalPages) {
	            currentPage++;
	            updateCommentList(currentPage);
	        }
	    }

    function updateCommentList(pageNumber) {
		const composerId = $("#commentBtn").data('composer-id');
        $.ajax({
            type: "GET",
            url: "/artist_detail/" + composerId + "?page=" + pageNumber,
            success: function (data) {
				console.log('연결 성공');
				console.log("Current Page: " + currentPage);
				console.log("Total Pages: " + totalPages);
				
                const newComments = $(data).find("#commentList").html(); // 새로운 댓글 목록 가져오기
	            $("#commentList").html(newComments); // 댓글 목록 업데이트
	            
	            currentPage = pageNumber; // 현재 페이지 업데이트
	            console.log("Current Page2: " + currentPage);
				console.log("Total Pages2: " + totalPages);
				
				
				// 응답에서 startPage 및 endPage를 가져오기
	            const newStartPage = parseInt($(data).find("#startPage").text());
	            const newEndPage = parseInt($(data).find("#endPage").text());
				
	            // Thymeleaf 모델에 추가
		        startPage = newStartPage;
		        endPage = newEndPage;
		        console.log("StartPage: " + startPage);
		        console.log("EndPage: " + endPage);
		        console.log("newStartPage: " + newStartPage);
		        console.log("newEndPage: " + newEndPage);
	            
	            // 모델에서 전달받은 startPage와 endPage를 사용하여 페이지 번호 업데이트
	            document.getElementById('startPage').innerText = newStartPage;
	            document.getElementById('endPage').innerText = newEndPage;

            },
            error: function () {
                console.error("Error updating comment list");
            }
        });
    }
    

