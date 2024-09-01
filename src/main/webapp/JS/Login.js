import {Websocket} from "./Websocket.js";
import {Matchmaking} from "./Matchmaking.js";
import {Signup} from "./Signup.js";
import {MessageHandler} from "./MessageHandler.js";
import {User} from "./User.js";

document.addEventListener('DOMContentLoaded', initializeLogin)

let passwordInput
let passwordRequired
let usernameInput
let usernameRequired
let loginButton
let loginFocus
let loginMenu
let userDisplay
let signUpButton

function initializeLogin () {
    loginButton = document.getElementById('login-button')
    usernameInput = document.getElementById('username')
    usernameRequired = document.getElementById('username-required')
    passwordInput = document.getElementById('password')
    passwordRequired = document.getElementById('password-required')
    loginFocus = document.getElementById('login-focus')
    loginMenu = document.getElementById('login-menu')
    userDisplay = document.getElementById('user')
    signUpButton = document.getElementById('signup-button')


    loginButton.addEventListener('click', login)

    usernameInput.addEventListener('click', function () {
        usernameRequired.classList.add('hidden')
    })

    passwordInput.addEventListener('click', function () {
        passwordRequired.classList.add('hidden')
    })

    signUpButton.addEventListener('click', function () {
        loginFocus.classList.add('hidden')
        Signup.start()
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

    let data = {
        messages: [usernameInput.value, passwordInput.value],
        messageType: 0
    }

    MessageHandler.send(data, function (message) {
        if (message.messages[0] === "True") {
            loginFocus.classList.add('hidden')
            userDisplay.append(usernameInput.value);
            User.username = usernameInput.value
            User.userId = message.messages[1]
            Matchmaking.start()
        } else {
            // Invalid login
            usernameInput.value = ''
            passwordInput.value = ''
        }
    })


}