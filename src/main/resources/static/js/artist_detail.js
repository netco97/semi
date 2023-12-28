document.addEventListener("DOMContentLoaded", function() {
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
//////////////////////////////////////////////////////
	// 별점 기능
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
			
			// 서버로 별점 전송
            submitRatingToServer(value);
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
	
	function submitRatingToServer(rating) {
    //const composerId = composerId/* HTML 또는 Thymeleaf에서 composerId 가져오기 */;
    const userFullNumber = '01044982324'/* 세션 또는 저장된 위치에서 userFullNumber 가져오기 */;
    const ratingData = {
		
        userFullNumber: userFullNumber,
        composerId: composerId,
        rating: rating
    };

    // AJAX를 사용하여 서버에 평가 전송
    $.ajax({
        type: "POST",
        url: "/ratings/rate",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(ratingData),
        success: function(result) {
			const createdRatingID = result.ratingID;
			console.log(userFullNumber);
			console.log(composerId);
			console.log(rating);
			
            console.log("평가가 성공적으로 제출되었습니다. RatingID: " + createdRatingID);
        },
        error: function(error) {
            console.error("평가 제출 중 오류", error);
        }
    });
}
//////////////////////////////////////////////////////////	
	// 팔로우
//	followBtn.addEventListener('click', () => {
//		alert('팔로우 되었습니다!');
//	});

	// 쪽지
//	messageBtn.addEventListener('click', () => {
//		alert('쪽지를 보냈습니다!');
//	});

//////////////////////////////////////////////////////
	// 코멘트 기능
	$(document).ready(function() {
		// 초기 코멘트 로드
		loadComments(1);
		

		// 페이지 링크 클릭 이벤트
		$(document).on("click", ".pagination-link", function() {
			event.preventDefault();
			var page = $(this).data("page");
			loadComments(page);
		});

		function loadComments(page) {
			var composerId = $("#commentBtn").data('composer-id');
			var size = 10;
			$.ajax({
				url: '/comments/' + composerId + '?page=' + page + '&size=' + size,
				type: 'GET',
				success: function(responseDTO) {
					console.log('loadComments 성공');
					console.log('composerId : ' + composerId);
					var comments = responseDTO.comments;
					var totalComments = responseDTO.totalComments;

					displayComments(comments);
					displayPagination(page, Math.ceil(totalComments / size));
				},
				error: function(data) {
					alert('코멘트 로딩 중 오류가 발생했습니다.');
					console.log(data);
				}
			});
		}

		function displayComments(comments) {
			var commentsContainer = $("#comments-container");
			commentsContainer.empty();

			$.each(comments, function(index, comment) {
				
				commentsContainer.append(
					'<div class="comment-item">' +
					'<div class="user-nickname">' + comment.userNickname + '</div>' +
					'<div class="comment-content">' + comment.comment_content + '</div>' +
					'<div class="created-at">' + convertToKoreanTime(comment.created_at) + '</div>' +
					'<div class="delete-btn" data-comment-id="' + comment.comment_id + '">삭제</div>' +
					'</div>'

				);
			});
/*			    $.each(comments, function(index, comment) {
        // 각 코멘트를 표시하는 HTML을 생성
        var commentHtml = '<div class="comment-item">' +
            '<div class="user-nickname">' + comment.userNickname + '</div>' +
            '<div class="comment-content">' + comment.comment_content + '</div>' +
            '<div class="created-at">' + convertToKoreanTime(comment.created_at) + '</div>';

        // 현재 로그인한 사용자와 코멘트를 작성한 사용자의 전화번호 비교
        if (comment.userFullNumber === userFullNumber) {
            // 같다면 삭제 버튼을 추가
            commentHtml += '<div class="delete-btn" data-comment-id="' + comment.comment_id + '">삭제</div>';
        }

        // HTML을 컨테이너에 추가
        commentHtml += '</div>';
        commentsContainer.append(commentHtml);
    });
	*/		
			
		}
		
		// 한국 시간으로 변경
		function convertToKoreanTime(dateTimeString) {
		    // 서버에서 제공하는 시간 형식에 따라 적절히 수정해야 할 수 있습니다.
		    var serverDateTime = new Date(dateTimeString.replace(' ', 'T') + 'Z'); // ISO 8601 형식으로 변환
		    var koreanDateTime = serverDateTime.toLocaleString("en-US", { timeZone: "Asia/Seoul" });
		    return koreanDateTime;
		}		

		function displayPagination(currentPage, totalPages) {
			var paginationContainer = $("#pagination");
			paginationContainer.empty();

			var startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
			var endPage = Math.min(totalPages, startPage + 9);

			if (currentPage > 10) {
				paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (startPage - 1) + '">이전</a>');
			}

			for (var i = startPage; i <= endPage; i++) {
				var activeClass = (i === currentPage) ? 'active' : '';
				paginationContainer.append('<a href="#" class="pagination-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
			}

			if (endPage < totalPages) {
				paginationContainer.append('<a href="#" class="pagination-link" data-page="' + (endPage + 1) + '">다음</a>');
			}
		}

		// 스크롤 이벤트를 사용하여 특정 영역에 도달했을 때 페이지 번호를 고정
		var paginationContainer = $("#pagination");
		var threshold = $("#commentTitle").offset().top;

		window.addEventListener("scroll", function() {
			var scrollPosition = window.scrollY;

			if (scrollPosition >= threshold) {
				paginationContainer.addClass("fixed-pagination");
			} else {
				paginationContainer.removeClass("fixed-pagination");
			}
		});


		// 코멘트 작성 버튼 클릭 시 이벤트
		$("#commentBtn").on("click", function() {
			const composerId = $("#commentBtn").data('composer-id');
			const userName = userNickname;
			const commentContent = $("#commentInput").val();

			const comment = {
				composer_id: composerId,
				userNickname: userName,
				comment_content: commentContent
			};
				console.log(composerId);
				console.log(userName);
				console.log(commentContent);

			$.ajax({
				type: "POST",
				url: "/comments",
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(comment),
				success: function(result) {
					console.log("코멘트 추가 성공");

					$("#commentInput").val("");
					// 코멘트를 업데이트하고 새로운 코멘트를 확인하기 위해 다시 로드합니다.
					loadComments(1);
				},
				error: function(error) {
					console.error("코멘트 추가 실패", error);
				}
			});
		});

		// 코멘트 삭제 버튼 클릭 시 이벤트
		$("#comments-container").on("click", ".delete-btn", function() {
			const isConfirmed = confirm("코멘트를 삭제하시겠습니까?");

			if (isConfirmed) {
				// 클릭된 버튼의 부모 요소에서 comment_id를 가져옴
				const commentId = $(this).data('comment-id');
				$.ajax({
					type: "DELETE",
					url: "/comments/" + commentId,
					success: function() {
						console.log(commentId);
						console.log("코멘트 삭제 성공");
						loadComments(1);
					},
					error: function(error) {
						console.log(commentId);
						console.error("코멘트 삭제 실패", error);
					}
				})
			}
		});
	});
});