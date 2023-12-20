function followUser() {
    var userFullPhoneNumber = "사용자의 전화번호"; // 예시로 고유한 사용자 식별 정보를 전달해야 합니다.
    var followerUsername = "팔로워의 이름"; // 예시로 팔로워의 정보를 전달해야 합니다.
    var followerEmail = "팔로워의 이메일"; // 예시로 팔로워의 정보를 전달해야 합니다.

    var data = {
        userFullPhoneNumber: userFullPhoneNumber,
        followerUsername: followerUsername,
        followerEmail: followerEmail
    };

    fetch('/api/followers/follow', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('result').innerText = data;
        document.getElementById('error').innerText = '';
    })
    .catch(error => {
        document.getElementById('error').innerText = 'Error following user';
        document.getElementById('result').innerText = '';
    });
}