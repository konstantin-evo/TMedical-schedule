$(document).ready(function() {
    var messageList = $("#messages"); // defined a connection to a new socket endpoint
    var socket = new SockJS('/schedule');
    var stompClient = Stomp.over(socket);

    stompClient.connect({ }, function(frame) {
        stompClient.subscribe("/room.2", function(data) { // subscribe to the /topic/message endpoint
            var message = data.body;
            window.location.reload();
        });
    });
});