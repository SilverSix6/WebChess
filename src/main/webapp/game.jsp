<%--
  User: aidan
  Date: 2024-08-24
  Time: 2:38 p.m.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chess</title>
    <script>
        let contextPath = '${pageContext.request.contextPath}'
    </script>

    <script type="module" src="${pageContext.request.contextPath}/JS/Websocket.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/JS/loadBoard.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/JS/Login.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/JS/loadLeaderboard.js"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fragment+Mono:ital@0;1&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/chessboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/general.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/menus.css">
</head>
<body>
    <div id="play-area">
        <div id="board"></div>
        <div id="pieces"></div>
    </div>

    <div id="waiting">

    </div>

    <div id="top-bar">
        <h1 id="logo">Web Chess</h1>
        <h1 id="user"></h1>
    </div>

    <div id="leader-board">
        <div class="leader-board-title">Leaderboard:</div>
        <div class="leader-board-entry"><div class="user">Username:</div><div class="win-loss">Win/Loss</div></div>
    </div>

    <div id="login-focus" class="focus">
        <div id="login-menu" class="center">
            <div id="login-menu-title">
                <h1>Login</h1>
            </div>

            <div id="login-menu-options">
                <div class="hidden required" id="username-required">required*</div>
                <input type="text" placeholder="Username" id="username">

                <div class="hidden required" id="password-required">required*</div>
                <input type="password" placeholder="Password" id="password">

                <input type="button" value="Login" id="login-button">
            </div>

        </div>
    </div>
</body>
</html>
