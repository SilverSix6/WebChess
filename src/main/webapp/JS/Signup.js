import {Websocket} from "./Websocket.js";

export class Signup {

    static signUpWindow
    static usernameInput
    static passwordInput
    static passwordConfirmInput
    static confirmButton

    static start() {
        this.signUpWindow = document.getElementById('signup')
        this.usernameInput = document.getElementById('su-username')
        this.passwordInput = document.getElementById('su-password')
        this.passwordConfirmInput = document.getElementById('su-password-confirm')
        this.confirmButton = document.getElementById('su-confirm')

        this.signUpWindow.classList.remove('hidden')

        this.confirmButton.addEventListener('click', this.signup.bind(this))
    }

    static signup() {
        if (this.usernameInput.value === "" || this.passwordInput.value === "" || this.passwordConfirmInput.value === "")
            return;
        if (this.passwordConfirmInput.value !== this.passwordInput.value)
            return;

        Websocket.connect()

        let data = {
            messageType: 7,
            messages: [this.usernameInput.value, this.passwordInput.value]
        }

        Websocket.send(data, function (result) {
            console.log(result)
            this.signUpWindow.classList.add('hidden')
        }.bind(this))
    }
}