document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('rating-value');
    const followBtn = document.getElementById('followBtn');
    const messageBtn = document.getElementById('messageBtn');
    
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
        const commentContent = $("#commentInput").val();
        
        // CommentDTO 객체 생성
        const comment = {
            composerId: 2/* 여기에 composerId 값을 가져오는 코드 */,
            userName: 'wk' /* 여기에 userName 값을 가져오는 코드 */,
            commentContent: commentContent
        };

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
                location.reload();
            },
            error: function(error) {
                console.error("코멘트 추가 실패", error);
            }
        });
    });
    
    $("#delete-btn").on("click", function() {
		const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");
		
		// 사용자가 확인을 선택한 경우에만 삭제 요청 보내기
		if(isConfirmed) {
			
		
		const commentId = $(this).data("comment-id");
		
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
});