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

	console.log(followerList)

	for (var i = 0; i < followerList.length; i++) {
		html += '<div onclick="gotodetail(' + "" + followerList[i].targetUserId + ');">' +
			'<img src="/images/profile/' + followerList[i].composer_img + '" style="width: 100px; height: 100px;">' +
			'&nbsp;' +
			followerList[i].composer_name +
			'<p>' + '</p>' +
			'</div>';
		console.log(JSON.stringify(followerList[i]))
	}
	html += '</ul>';
	return html;
}

function gotodetail(phone_follower) {

	console.log(phone_follower)

	const detailPageUrl = "/artist_detail";

	const form = $('<form>', {
		'action': detailPageUrl,
		'method': 'post',
		'style': 'display:none;'
	});

	$('<input>').attr({
		'type': 'hidden',
		'name': 'userFullPhoneNumber',
		'value': "0" + phone_follower
	}).appendTo(form);

	form.appendTo('body').submit();
}