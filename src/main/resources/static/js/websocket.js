$(document).ready(function() {
    var socket = new SockJS('/schedule');
    var stompClient = Stomp.over(socket);

    stompClient.connect({ }, function() {
        stompClient.subscribe("/message", function() { // subscribe to the /schedule/message endpoint
            window.location.reload();
        });
    });
});