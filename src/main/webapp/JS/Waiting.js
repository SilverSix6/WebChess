

export class Waiting {

    static start(gameID) {
        Waiting.loadElements(gameID)
    }

    static loadElements(gameID) {
        let waitingWindow = document.getElementById('waiting')
        let leaveGame = document.getElementById('leaveQueue')

        leaveGame.addEventListener('click', function () {
            waitingWindow.classList.add('hidden')
        })

        waitingWindow.classList.remove('hidden')


        let GameID = document.getElementById('waiting-game-id')
        GameID.value = "Game ID: " + gameID

    }



}