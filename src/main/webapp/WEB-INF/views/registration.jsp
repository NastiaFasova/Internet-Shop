<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Hello! Please, provide your user details!
<h4 style="color: red">${message}</h4>
<form action="${pageContext.request.contextPath}/registration" method="post">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <input type="text" placeholder="Enter your Name" name="name" required>
        <input type="text" placeholder="Enter Login" name="login" required>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
        <hr>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit">Register</button>
        <p>Already have an account? <a href="#">Sign in</a>.</p>
</form>
</body>
</html>

