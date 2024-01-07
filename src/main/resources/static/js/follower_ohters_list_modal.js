function showFollowerListModal(followerList) {
	$('#followerListModalContent').html(buildFollowerListHTML(followerList));
	$('#followerListModal').show();
}
function closeFollowerListModal() {
	$('#followerListModal').hide();
}