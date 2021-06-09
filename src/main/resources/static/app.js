var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {

    var socket = new SockJS('http://localhost:8001/schedule');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onSuccess);
/*    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/therapy-cases', function (therapyCase) {
            showGreeting(JSON.parse(therapyCase.body).content);
        });
    });*/
}
function onSuccess() {
    stompClient.subscribe('/topic/schedule', showGreeting, onError);
   // stompClient.send("/chat/connect", {}, JSON.stringify({sender: username, messageType: messageTypes[0]}) );
}
function onError(error) {
   console.log('Could not connect to WebSocket server. Please refresh this page to try again!');

}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/*function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}*/

function showGreeting(message) {
    console.log(message.body);
    $("#therapyCases").append(
    "<tr th:each=\"therapy : "+message+">"+
     "   <th scope=\"row\"></th>"+
       " <td th:text=\"${therapy.patient}\"></td>"+
        "<td th:text=\"${therapy.medical}\"></td>"+
        "<td th:text=\"${therapy.doctor}\"></td>"+
        "<td th:text=\"${therapy.date}\"></td>"+
       "<td th:text=\"${therapy.time}\"></td>"+
        "<td th:text=\"${therapy.status}\"></td>"+
    "</tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
     connect();
  //  $( "#disconnect" ).click(function() { disconnect(); });
//    $( "#send" ).click(function() { sendName(); });
});
