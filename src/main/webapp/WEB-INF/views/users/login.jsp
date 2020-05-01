<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        .container{
            width: 550px;
            margin: 30px auto;
            background-color: lightgray;
            height: 400px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
        }
        form{
            border-radius: 10px;
            margin: 20px auto;
        }
        input{
            display: block;
            margin: 20px auto;
            padding: 10px;
            width: 450px;
            border-radius: 10px;
        }
        button{
            width:200px;
            margin:30px auto;
            display: block;
            padding: 10px;
            background-color: royalblue;
            color: ivory;
            font-size: 20px;
            border-radius: 20px;
        }
        h1{
            margin:30px 215px;
            padding-top: 20px;
            font-size: 40px;
            font-family:  Arial, serif;
            text-shadow: 2px 2px 5px rgba(255,255,255,0.7);
            color: #666666;
        }
        h4{
            margin:30px 170px;
        }
        button:hover{
            border-color: deepskyblue;
            border-width: 2px;
            cursor: pointer;
            box-shadow: inset 5px 5px 10px rgba(255,255,255,0.7);
        }
    </style>
</head>
<body>
<div class="container">
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
