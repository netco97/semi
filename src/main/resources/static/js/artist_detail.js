document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('rating-value');
    const followBtn = document.getElementById('followBtn');
    const messageBtn = document.getElementById('messageBtn');
    const commentInput = document.getElementById('commentInput');
    const commentBtn = document.getElementById('commentBtn');
    const commentList = document.getElementById('commentList');
    
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

    commentBtn.addEventListener('click', () => {
        const commentText = commentInput.value;
        if (commentText.trim() !== '') {
            const commentDiv = document.createElement('div');
            commentDiv.className = 'comment';
            commentDiv.innerText = `사용자: ${commentText}`;
            commentList.appendChild(commentDiv);
            commentInput.value = '';
        }
    });
});