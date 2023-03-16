let stompClient;

function getUsername() {
    return document.getElementById("from").value;
}

function setConnected(connected) {
    document.getElementById("from").disabled = connected;
    document.getElementById("connect").disabled = connected;
    document.getElementById("disconnect").disabled = !connected;
    document.getElementById('sendmessage').style.display = connected ? 'flex' : 'none';
}

function onStompConnect() {
    const onMessage = (output) => {
        const messageJson = JSON.parse(output.body);
        const messageTextNode = createTextNode(messageJson);
        showBroadcastMessage(messageTextNode)
    };
    return () => {
        stompClient.subscribe('/topic/messages', onMessage);
        sendConnection(' connected to server');
        setConnected(true);
    };
}

function onStompError() {
    return (err) => alert(err);
}

function connect() {
    if (!getUsername()) {
        alert('Please input a nickname!');
        return;
    }
    const url = 'ws://localhost:8080/broadcast';
    stompClient = Stomp.client(url);
    stompClient.connect({}, onStompConnect(), onStompError());
}

function disconnect() {
    if (!stompClient) {
        return;
    }
    sendConnection(' disconnected from server');
    stompClient.disconnect(() => setConnected(false));
}

function sendConnection(message) {
    const text = getUsername() + message;
    sendBroadcast({'from': 'server', 'text': text});
}

function sendBroadcast(json) {
    stompClient.send("/app/broadcast", {}, JSON.stringify(json));
}

function send() {
    let messageElement = document.getElementById("message");
    const text = messageElement.value;
    sendBroadcast({'from': getUsername(), 'text': text});
    messageElement.value = "";
}

function createTextNode(messageObj) {
    const messageCount = document.getElementsByClassName("alert").length;
    const alertColor = messageCount % 2 === 0 ? 'alert-dark' : 'alert-light';
    return `<div class="row alert ${alertColor}">
                <div class="col-md-8">${messageObj.text}</div>
                    <div class="col-md-4 text-right">
                    <small>[<b>${messageObj.from}</b> ${messageObj.time}]</small>
                </div>
            </div>`;
}

function showBroadcastMessage(message) {
    const contentElement = document.getElementById("content")
    contentElement.innerHTML = contentElement.innerHTML + message;
    document.getElementById("clear").style.display = 'flex';
}

function clearBroadcast() {
    document.getElementById("content").innerHTML = "";
    document.getElementById("clear").style.display = 'none';
}
