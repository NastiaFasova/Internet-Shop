<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4 style="color: red">${error}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <h1>Login</h1>
    <p>Please fill in this form to login.</p>
    <hr>
    <input type="text" placeholder="Enter Login" name="login" required>
    <input type="password" placeholder="Enter Password" name="psw" required>
    <hr>
    <button type="submit">Login</button>
</form>
</body>
</html>
