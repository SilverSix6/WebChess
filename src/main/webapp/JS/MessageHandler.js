import {Websocket} from "./Websocket.js";
import {Waiting} from "./Waiting.js";
import {getBoardFactory} from "./loadBoard.js";
import {User} from "./User.js";


export class MessageHandler {

    static eventListeners = new Map();

    static handle(event) {
        let message = JSON.parse(event.data)

        MessageHandler.logReceive(message)

        switch (message.messageType) {
            case "2":
                MessageHandler.handleMove(message)
                return
            case "6":
                MessageHandler.handleGameStart(message)
                return;
            default:
                MessageHandler.handleDefault(message)
                return;
        }
    }

    static handleMove(message) {
        console.log('Message type move: ')
        console.log(message)
    }

    static handleGameStart(message) {
        console.log('Game start: ')
        console.log(message)

        Waiting.hide()

        console.log(message.messages[0])
        console.log(User.userId)
        getBoardFactory().loadPieces(message.messages[0])

    }

    static handleDefault(message) {
        let listener = this.eventListeners.get(message.messageId)
        listener(message)

        this.eventListeners.delete(message.messageId)
    }

    static logSend(messageData) {
        console.log('Message Sent: ')
        console.log(messageData)
    }

    static logReceive(messageData) {
        console.log('Message Received: ')
        console.log(messageData)
    }

    static send(data, listener) {
        Websocket.connect()

        Websocket.waitForSocketConnection(function () {
                data.messageId = Websocket.messageId;
                Websocket.webSocket.send(JSON.stringify(data));
                Websocket.messageId++;
                MessageHandler.logSend(data)
                MessageHandler.eventListeners.set(data.messageId, listener);
            })
    }
}