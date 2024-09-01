

export class Waiting {

    static waitingWindow
    static leaveGame
    static start(gameID) {
        Waiting.loadElements(gameID)
    }

    static loadElements(gameID) {
        Waiting.waitingWindow = document.getElementById('waiting')
        Waiting.leaveGame = document.getElementById('leaveQueue')

        Waiting.leaveGame.addEventListener('click', function () {
            Waiting.waitingWindow.classList.add('hidden')
        })

        Waiting.waitingWindow.classList.remove('hidden')


        let GameID = document.getElementById('waiting-game-id')
        GameID.value = "Game ID: " + gameID

    }

    static hide() {
        document.getElementById('waiting').classList.add('hidden')
    }



}