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
		$('#followBtn').text('フォロー');
	} else {
		$('#followBtn').text('アン·フォロー');
	}
}
