var stompClient = null;

function createChatroom() {
    //테스트용 sessionId = document.getElementById("session_id").value;
    console.log(userNickname)
    console.log(followerId)

    var data = {
        session_id: userNickname,
        follower_id: followerId
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "connect_chatroom", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var responseData = JSON.parse(xhr.response);
                roomId = responseData.roomId;

                // 모달 열기
                openModal();

                // 서버에서 채팅 리스트 받아오기
                getChatList(roomId);

                // WebSocket 연결
                connectWebSocket(roomId);

                console.log(roomId);
            } else {
                console.error("통신 실패: " + xhr.status);
            }
        }
    };

    xhr.onerror = function () {
        console.error("네트워크 오류로 통신에 실패했습니다.");
    };

    xhr.send(JSON.stringify(data));
}

function getChatList(roomId) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "get_chat_list?roomId=" + roomId, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var chatList = JSON.parse(xhr.responseText);
                displayChatList(chatList);
            } else {
                console.error("통신 실패: " + xhr.status);
            }
        }
    };

    xhr.onerror = function () {
        console.error("네트워크 오류로 통신에 실패했습니다.");
    };

    xhr.send();
}

function displayChatList(chatList) {
    var messageArea = document.getElementById("messageArea");
    messageArea.innerHTML = "";

    for (var i = 0; i < chatList.length; i++) {
        var messageElement = document.createElement("div");
        
        // 본인과 상대방에 따라 클래스를 추가
	        if (chatList[i].sender === userNickname) {
	            messageElement.classList.add("own-chat");
	        } else {
	            messageElement.classList.add("other-chat");
	        }
	        
	        
        messageElement.innerText = chatList[i].sender + "\n" + chatList[i].message + "\n" + chatList[i].cur_date;
        messageArea.appendChild(messageElement);
    }
}

function connectWebSocket(roomId) {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected to WebSocket: ' + frame);

        stompClient.subscribe('/sub/topic/' + roomId, function (response) {
            var message = JSON.parse(response.body);
            console.log("message 확인 " + message);
            displayMessage(message);
        });
    });
}

function openModal() {
    var modal = document.getElementById("myModal");
    var websocketResult = document.getElementById("websocketResult");

    websocketResult.innerText = "채팅방";
    websocketResult.style.textAlign = "center";
    websocketResult.style.fontWeight = 700;

    modal.style.display = "block";
    
    setTimeout(function () {
       		scrollChatAreaToBottom();
    	}, 500);
}

function scrollChatAreaToBottom() {
    var messageArea = document.getElementById("messageArea");

    if (messageArea) {
        // 스크롤을 맨 아래로 이동
        messageArea.scrollTop = messageArea.scrollHeight;
    } else {
        console.error("Message Area element not found!");
    }
}

function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";

    disconnectWebSocket();
}

function disconnectWebSocket() {
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log("Disconnected from WebSocket");
    }
}

function displayMessage(message) {
    var messageArea = document.getElementById("messageArea");
    var messageElement = document.createElement("div");
    
    // 본인과 상대방에 따라 클래스를 추가
	    if (message.sender === userNickname) {
	        messageElement.classList.add("own-chat");
	    } else {
	        messageElement.classList.add("other-chat");
	    }
	    
    messageElement.innerText = message.sender + "\n" + message.message + "\n" + message.cur_date;
    messageArea.appendChild(messageElement);
    
    // 스크롤을 맨 아래로 이동
    messageArea.scrollTop = messageArea.scrollHeight;
}

document.getElementById("chatInput").addEventListener("keyup", function (event) {
    if (event.key === "Enter") {
        sendMessage();
    }
});

function sendMessage() {
    var chatInput = document.getElementById("chatInput");
    var message = chatInput.value.trim();

    if (message !== "") {
        stompClient.send("/pub/chat", {}, JSON.stringify({'sender': userNickname, 'message': message, 'roomId': roomId}));
        chatInput.value = "";
    }
}