import {Waiting} from "./Waiting.js";
import {Websocket} from "./Websocket.js";


export class Matchmaking {

    static gameIDDisplay
    static gameID
    static joinGame
    static newGame
    static matchmakingWindow

    static start() {
        Matchmaking.loadElements()
    }

    static loadElements() {
        Matchmaking.gameIDDisplay = document.getElementById('gameID')
        Matchmaking.joinGame = document.getElementById('joinGame')
        Matchmaking.newGame = document.getElementById('newGame')
        Matchmaking.matchmakingWindow = document.getElementById('matchmaking')

        Matchmaking.matchmakingWindow.classList.remove('hidden')

        Matchmaking.addListeners()
    }

    static addListeners() {
        this.newGame.addEventListener('click', Matchmaking.processNewGame)
        this.joinGame.addEventListener('click', Matchmaking.processJoinGame)
    }

    static processNewGame() {
        console.log('Process New Game')

        Websocket.connect();

        let data = {
            messageType: 4,
        }

        Websocket.send(data, function (response) {

            Matchmaking.gameID = response.messages[0];

            Matchmaking.finish()
        })
    }

    static processJoinGame() {
        console.log('Process Join Game: ' + Matchmaking.gameIDDisplay.value)

        Websocket.connect();

        let data = {
            messageType: 5,
            messages: [Matchmaking.gameIDDisplay.value]
        }

        Websocket.send(data, function (response) {

            if (response.messages[0] === "NOTFOUND") {
                console.log("Game Not Found")
                return; // todo: Inform user
            }

            Matchmaking.gameID = response.messages[0]

            Matchmaking.finish(response.messages[1] === "1")
        })
    }


    static finish(starting) {
        Matchmaking.matchmakingWindow.classList.add('hidden')
        if (starting) {
            // todo
            console.log("Game starting")
        } else {
            Waiting.start(this.gameID)
        }

    }

}