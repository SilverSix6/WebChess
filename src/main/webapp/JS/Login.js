import {Websocket} from "./Websocket.js";

document.addEventListener('DOMContentLoaded', initializeLogin)

let passwordInput
let passwordRequired
let usernameInput
let usernameRequired
let loginButton
let loginFocus
let loginMenu
let userDisplay

function initializeLogin () {
    loginButton = document.getElementById('login-button')
    usernameInput = document.getElementById('username')
    usernameRequired = document.getElementById('username-required')
    passwordInput = document.getElementById('password')
    passwordRequired = document.getElementById('password-required')
    loginFocus = document.getElementById('login-focus')
    loginMenu = document.getElementById('login-menu')
    userDisplay = document.getElementById('user')

    loginButton.addEventListener('click', login)

    usernameInput.addEventListener('click', function () {
        usernameRequired.classList.add('hidden')
    })

    passwordInput.addEventListener('click', function () {
        passwordRequired.classList.add('hidden')
    })

}

function login () {

    if (usernameInput.value === "") {
        usernameRequired.classList.remove('hidden')
        return
    }

    if (passwordInput.value === "") {
        passwordRequired.classList.remove('hidden')
        return
    }

    Websocket.connect()

    let data = {
        messages: [usernameInput.value, passwordInput.value],
        messageType: 0
    }

    Websocket.send(data, function (message) {
        if (message.messages[0] === "True") {
            loginFocus.classList.add('hidden')
            userDisplay.append(usernameInput.value);
            // todo: handle successful login
        } else {
            // Invalid login
            usernameInput.value = ''
            passwordInput.value = ''
        }
    })


}