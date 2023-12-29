function toggleFollow() {
     // 세션에서 사용자 정보 가져오기 (가능하다면)
    let followerUserId = followerUserId1;
    console.log("fdafds"+ followerUserId);
    // 컴포저 객체에서 대상 사용자 정보 가져오기
    let targetUserId = targetUserId1;
    
    // 팔로우 상태를 토글하기 위한 AJAX 요청
    
    
    $.ajax({
        type: 'POST',
        url: '/follow/toggle',
        data: {
            followerUserId: followerUserId,
            targetUserId: targetUserId
        },
        success: function () {
            // 버튼 텍스트나 다른 UI 변경사항 업데이트
            updateFollowButton();
        },
        error: function (error) {
            console.error('팔로우 토글 중 오류 발생:', error);
        }
    });
}

function updateFollowButton() {
    // 팔로우 상태에 따라 버튼 텍스트나 스타일 업데이트 가능
    let isFollowing = /* 응답이나 다른 소스에서 팔로우 상태를 가져오세요 */;
    
    if (isFollowing) {
        $('#followBtn').text('언팔로우');
    } else {
        $('#followBtn').text('팔로우');
    }
}