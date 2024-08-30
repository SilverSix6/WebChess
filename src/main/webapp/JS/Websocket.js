
export class Websocket {
    static webSocket;
    static messageId = 0;
    static eventListeners = new Map();
    static wsUrl = (location.protocol === "https:" ? "wss://" : "ws://") + location.host + "/WebChess_war_exploded/interface";

    static connect() {

        // open the connection if one does not exist
        if (this.webSocket !== undefined
            && this.webSocket.readyState !== WebSocket.CLOSED) {
            return;
        }

        console.log('Connecting to WebSocket: ' + this.wsUrl)

        // Create a websocket
        this.webSocket = new WebSocket(this.wsUrl);

        this.webSocket.onopen = function(event) {
            console.log('Connection successful')
        };

        this.webSocket.onmessage = function(event) {
            console.log("Message: " + event.data)

            let message = JSON.parse(event.data);

            let listener = Websocket.eventListeners.get(message.messageId)
            listener(message)

            Websocket.eventListeners.delete(message.messageId)
        };

        this.webSocket.onclose = function(event) {
            console.log('Connection closed')
        };

        this.webSocket.onerror = function(event) {
            console.log("Connection Error: " + event)
        }.bind(this)
    }

    static send(data, listener) {
        this.waitForSocketConnection(this.webSocket,
            function () {
                data.messageId = Websocket.messageId;
                Websocket.webSocket.send(JSON.stringify(data));
                Websocket.messageId++;

                console.log(data)
                Websocket.eventListeners.set(data.messageId, listener);
            })
    }

    static close() {
        this.webSocket.close();
    }

    static waitForSocketConnection(socket, callback){
        setTimeout(
            function () {
                if (socket.readyState === 1) {
                    if (callback != null){
                        callback();
                    }
                } else {
                    Websocket.waitForSocketConnection(socket, callback);
                }

            }, 10);
    }

}

