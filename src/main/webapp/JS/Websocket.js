import {MessageHandler} from "./MessageHandler.js";

export class Websocket {
    static webSocket;
    static messageId = 0;

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

        this.webSocket.onmessage = MessageHandler.handle;

        this.webSocket.onclose = function(event) {
            console.log('Connection closed')
        };

        this.webSocket.onerror = function(event) {
            console.log("Connection Error: " + event)
        }.bind(this)
    }

    static close() {
        this.webSocket.close();
    }

    static waitForSocketConnection(callback){
        setTimeout(
            function () {
                if (Websocket.webSocket.readyState === 1) {
                    if (callback != null){
                        callback();
                    }
                } else {
                    Websocket.waitForSocketConnection(callback);
                }

            }, 10);
    }

}

