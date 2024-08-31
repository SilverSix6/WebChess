import {Websocket} from "./Websocket.js";

document.addEventListener('DOMContentLoaded', function () {
    let leaderboard = document.getElementById('leader-board')

    Websocket.connect()

    let data = {
        messageType: 1
    }

    Websocket.send(data, function (message) {
        let entries = message.messages
        for (let i = 0; i < entries.length; i+=2) {
            let entry = document.createElement('div');
            let user = document.createElement('div');
            let score = document.createElement('div');

            entry.classList.add('leader-board-entry')
            user.classList.add('user')
            score.classList.add('win-loss')

            user.innerHTML = entries[i]
            score.innerHTML = entries[i + 1]

            entry.append(user)
            entry.append(score)

            leaderboard.append(entry)

        }
    })
})