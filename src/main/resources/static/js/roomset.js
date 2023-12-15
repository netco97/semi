function createChatroom() {
    // 사용자가 입력한 데이터 가져오기
    var sessionId = document.getElementById("session_id").value;
    var followerId = document.getElementById("follower_id").value;

	    var data = {
	    session_id: sessionId,
	    follower_id: followerId
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "connect_chatroom", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	
	xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4) {
	        if (xhr.status === 200) {
				var responseData = JSON.parse(xhr.response);
				
	            console.log(responseData);
	        } else {
	            console.error("통신 실패: " + xhr.status);
	        }
	    }
	};
	
	xhr.onerror = function() {
	    console.error("네트워크 오류로 통신에 실패했습니다.");
	};
	
	xhr.send(JSON.stringify(data));
}