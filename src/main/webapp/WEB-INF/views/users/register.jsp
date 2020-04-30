<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        div{
            width: 450px;
            margin: 20px auto;
        }
        input{
            display: block;
            margin-bottom: 20px;
            padding: 10px;
            width: 450px;
            border-radius: 10px;
        }
        button{
            width:200px;
            margin:20px auto;
            display: block;
            padding: 10px;
            background-color: royalblue;
            color: ivory;
            font-size: 20px;
            border-radius: 20px;
        }
        h1{
            margin:20px 150px;
            font-size: 40px;
            font-family:  Arial, serif;
        }
        button:hover{
            border-color: deepskyblue;
            border-width: 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div>
    <h4 style="color: red">${message}</h4>
    <form action="${pageContext.request.contextPath}/users/register" method="post">
        <h1>Register</h1>
        <input type="text" placeholder="Enter your Name" name="name" required>
        <input type="text" placeholder="Enter Login" name="login" required>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
        <button type="submit">Register</button>
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </form>
</div>
</body>
</html>

