import {Websocket} from "./Websocket.js";
import {Waiting} from "./Waiting.js";
import {getBoardFactory} from "./loadBoard.js";
import {User} from "./User.js";


export class MessageHandler {

    static eventListeners = new Map();

    static handle(event) {
        let message = JSON.parse(event.data)

        //MessageHandler.logReceive(message)

        switch (message.messageType) {
            case "2":
                MessageHandler.handleMove(message)
                return
            case "6":
                MessageHandler.handleGameStart(message)
                return
            case "8":
                MessageHandler.handleTurn(message)
                return;
            default:
                MessageHandler.handleDefault(message)
                return;
        }
    }

    static handleMove(message) {
        console.log('MOVE: ', message)

        let data = message.messages
        if (data.length === 4)
            getBoardFactory().board.movePiece(data[0],data[1],data[2],data[3])
    }

    static handleGameStart(message) {
        console.log('GAME START: ', message)
        console.log('Starting User: ', message.messages[0], ' Current User: ', User.userId)

        Waiting.hide()

        getBoardFactory().loadPieces(message.messages[0])

    }

    static handleTurn(message) {
        console.log('TURN: ', message)
        
        User.turn = message.messages[0] === User.userId;
    }

    static handleDefault(message) {
        console.log('DEFAULT: ', message)
        let listener = this.eventListeners.get(message.messageId)
        listener(message)

        this.eventListeners.delete(message.messageId)
    }

    static logSend(data) {
        console.log('Sent: ', data)
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