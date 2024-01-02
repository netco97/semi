function toggleFollow() {
	let followerUserId = followerUserId1;
	let targetUserId = targetUserId1;

	$.ajax({
		type: 'POST',
		url: '/follow/toggle',
		data: {
			followerUserId: followerUserId,
			targetUserId: targetUserId
		},
		success: function(response) {
			// 응답에서 팔로우 상태를 가져와서 업데이트
			updateFollowButton(response.isFollowing);
		},
		error: function(error) {
			console.error('팔로우 토글 중 오류 발생:', error);
		}
	});
}

function updateFollowButton(isFollowing) {
	if (isFollowing) {
		$('#followBtn').text('팔로우');
	} else {
		$('#followBtn').text('언팔로우');
	}
}

// 내 팔로워 보기 버튼 클릭 시 모달창 띄우기
function openFollowerListModal() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 모달창에 반환된 HTML을 삽입하고 모달창 열기
                document.getElementById('followerListModalContent').innerHTML = xhr.responseText;
                document.getElementById('followerListModal').style.display = 'block';
            } else {
                console.error('팔로워 리스트 가져오기 오류:', xhr.status, xhr.statusText);
            }
        }
    };

    xhr.open('GET', '/follow/follower_list?userId=' + followerUserId1, true);
    xhr.send();
}