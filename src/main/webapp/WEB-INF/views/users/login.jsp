<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        div{
            width: 450px;
            margin: 30px auto;
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
            margin:30px 165px;
            font-size: 40px;
            font-family:  Arial, serif;
        }
        h4{
            margin:30px 130px;
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
    <h4 style="color: red">${error}</h4>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <h1>Login</h1>
        <input type="text" placeholder="Enter Login" name="login" required>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <button type="submit">Login</button>
    </form>
    <form action="${pageContext.request.contextPath}/users/register">
        <button>Register</button>
    </form>
</div>
</body>
</html>
