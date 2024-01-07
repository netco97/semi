function showFollowerListModal2(followerList) {
	$('#followerListModalContent2').html(buildFollowerListHTML2(followerList));
	$('#followerListModal2').show();
}
function closeFollowerListModal2() {
	$('#followerListModal2').hide();
}
function buildFollowerListHTML2(followerList) {
    var html = '<ul>';

    console.log(followerList)

    for (var i = 0; i < followerList.length; i++) {
        html += '<div id="otherfollower" onclick="gotodetail2(' + "" + followerList[i].followerUserId + ');" class="follower-list-item">' +
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

function gotodetail2(phone_follower) {

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