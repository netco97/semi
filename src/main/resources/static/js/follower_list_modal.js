// follower_list_modal.js

// 모달창 닫기
function closeFollowerListModal() {
    $('#followerListModal').hide();
}

// 모달창 열기
function showFollowerListModal(followerList) {
    $('#followerListModalContent').html(buildFollowerListHTML(followerList));
    $('#followerListModal').show();
}

// 팔로우 리스트 HTML 생성
function buildFollowerListHTML(followerList) {
    var html = '<ul>';
    for (var i = 0; i < followerList.length; i++) {
        html += '<li>' + followerList[i].userId + '</li>';
    }
    html += '</ul>';
    return html;
}