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
    <script type="module" src="${pageContext.request.contextPath}/JS/loadBoard.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/chessBoard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/piece.css">
</head>
<body>
    <div id="play-area">
        <div id="board"></div>
        <div id="pieces"></div>
    </div>
</body>
</html>
