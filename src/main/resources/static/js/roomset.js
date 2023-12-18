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
				const roomId = responseData.room_id;
				openModal(roomId);
				//location.href = "room_detail/" +responseData.roomId;
	            console.log(roomId);
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

// 모달 창 열기
function openModal(roomId) {
  var modal = document.getElementById("myModal");
  var modalContent = document.getElementById("modalContent");

  // 모달 창에 내용 추가
  modalContent.innerText = "Room ID: " + roomId;

  // 모달 창 표시
  modal.style.display = "block";
}

// 모달 창 닫기
function closeModal() {
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
}