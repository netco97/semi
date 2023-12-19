var stompClient = null;
let sessionId;
let followerId;
let roomId;

function createChatroom() {
    sessionId = document.getElementById("session_id").value;
    followerId = document.getElementById("follower_id").value;

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
                roomId = responseData.room_id;
                openModal(roomId);

                // WebSocket 연결
                connectWebSocket(roomId);

                console.log(roomId);
            } else {
                console.error("Communication failed: " + xhr.status);
            }
        }
    };

    xhr.onerror = function() {
        console.error("Network error occurred.");
    };

    xhr.send(JSON.stringify(data));
}

function connectWebSocket(roomId) {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected to WebSocket: ' + frame);

        stompClient.subscribe('/sub/topic/' + roomId, function (response) {
            var message = JSON.parse(response.body);
            console.log("Message: " + message);
            displayMessage(message);
        });
    });
}

function openModal(roomId) {
    var modal = document.getElementById("myModal");
    var websocketResult = document.getElementById("websocketResult");

    websocketResult.innerText = "WebSocket Result: " + roomId;
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";

    // WebSocket 연결 해제
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
    messageElement.innerText = message.sender + " : " + message.message;
    messageArea.appendChild(messageElement);

    // Scroll to the bottom
    messageArea.scrollTop = messageArea.scrollHeight;
}

// Enter key sends the message
document.getElementById("chatInput").addEventListener("keyup", function(event) {
    if (event.key === "Enter") {
        sendMessage();
    }
});

function sendMessage() {
    var chatInput = document.getElementById("chatInput");
    var message = chatInput.value.trim();

    if (message !== "") {
        stompClient.send("/pub/chat", {}, JSON.stringify({'sender' : sessionId, 'message': message , 'roomId' : roomId}));
        chatInput.value = "";
    }
}
