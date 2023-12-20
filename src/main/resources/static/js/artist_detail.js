document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('rating-value');
    const followBtn = document.getElementById('followBtn');
    const messageBtn = document.getElementById('messageBtn');
    
    
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
    
    
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const value = parseInt(star.getAttribute('data-value'));
            ratingValue.innerText = `평가: ${value}점`;
            removeActiveStars();
            setActiveStars(value);
        });

        star.addEventListener('mouseover', () => {
            const value = parseInt(star.getAttribute('data-value'));
            removeActiveStars();
            setActiveStars(value);
        });

        star.addEventListener('mouseout', () => {
            removeActiveStars();
        });
    });

    function removeActiveStars() {
        stars.forEach(star => {
            star.classList.remove('active');
        });
    }

    function setActiveStars(value) {
        for (let i = 0; i < value; i++) {
            stars[i].classList.add('active');
        }
    }

    followBtn.addEventListener('click', () => {
        // 팔로우 기능 구현
        alert('팔로우 되었습니다!');
    });

    messageBtn.addEventListener('click', () => {
        // 쪽지 보내기 기능 구현
        alert('쪽지를 보냈습니다!');
    });

$(document).ready(function() {
    // 코멘트 작성 버튼 클릭 시 이벤트
    $("#commentBtn").on("click", function() {
        // 작성한 코멘트 내용 가져오기
        const composerId = $("#commentBtn").data('composer-id');
        const userName = $("#commentBtn").data('user-name');
        const commentContent = $("#commentInput").val();
        
        // CommentDTO 객체 생성
        const comment = {
            composerId: composerId /* 여기에 composerId 값을 가져오는 코드 */,
            userName:  userName /* 여기에 userName 값을 가져오는 코드 */,
            commentContent: commentContent
        };
        console.log(composerId);
        console.log(userName);

        // Ajax를 통해 서버에 코멘트 추가 요청
        $.ajax({
            type: "POST",
            url: "/comments",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(comment),
            success: function() {
                // 코멘트 추가 성공 시, 화면 갱신 등 필요한 작업 수행
                console.log("코멘트 추가 성공");
                $("#commentInput").val("");
                //location.reload();
                
                // 현재 URL을 가져오고 쿼리 매개변수를 제거합니다.
        		const currentUrl = window.location.href.split('?')[0];

		        // 업데이트된 댓글이 표시된 아티스트 페이지로 리다이렉트합니다.
		        window.location.href = currentUrl;
                
            },
            error: function(error) {
                console.error("코멘트 추가 실패", error);
            }
        });
    });
    
    $(".comment-container-inner").on("click", "#delete-btn", function() {
		const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");
		
		// 사용자가 확인을 선택한 경우에만 삭제 요청 보내기
		if(isConfirmed) {
			const commentId = $(this).data("commentId");
			console.log(commentId);
		
    	// Ajax를 통해 서버에 코멘트 삭제 요청
    	
    	$.ajax({
			type: "DELETE",
			url: "/comments/" + commentId,
			success: function() {
				// 코멘트 삭제 성공 시, 화면 갱신 등 필요한 작업 수행
				console.log("코멘트 삭제 성공");
				location.reload();
			},
			error: function(error) {
				console.error("코멘트 삭제 실패", error);
			}
		})
    }
    
    
    
    });
    
    
    
	});
	
	
	
	function loadCommentsByPage(pageNumber) {
    // AJAX를 사용하여 해당 페이지의 댓글을 서버에서 가져오는 로직
    // 페이지 번호를 서버에 전달하고, 서버는 해당 페이지의 댓글을 응답으로 전달
    
    $.ajax({
        type: "GET",
        url: "/comments/" + composerId + "?page=" + pageNumber,
        success: function (data) {
            // 서버에서 받은 데이터로 댓글 목록 업데이트
            console.log("서버 응답:", data);
            $("#commentList").html(data);
        },
        error: function () {
            console.error("Error loading comments by page");
        }
    });
}
	
});