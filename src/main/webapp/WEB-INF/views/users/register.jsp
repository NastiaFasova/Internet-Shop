<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        div{
            width: 550px;
            margin: 20px auto;
        }
        form{
            height: 500px;
            width: 550px;
            border-radius: 10px;
            margin: 20px auto;
            background-color: lightgray;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
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
            margin:20px auto;
            display: block;
            padding: 10px;
            background-color: royalblue;
            color: ivory;
            font-size: 20px;
            border-radius: 20px;
        }
        h1{
            padding-top:30px;
            margin:20px 160px;
            font-size: 40px;
            font-family:  Arial, serif;
            text-shadow: 2px 2px 5px rgba(255,255,255,0.7);
            color: #666666;
        }
        button:hover{
            border-color: deepskyblue;
            border-width: 2px;
            cursor: pointer;
            box-shadow: inset 5px 5px 10px rgba(255,255,255,0.7);
        }
        p{
            display: block;
            margin-left: 60px;
        }
    </style>
</head>
<body>
<div>
    <h4 style="color: red">${message}</h4>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <h1>Registration</h1>
        <input type="text" placeholder="Enter your Name" name="name" required>
        <input type="text" placeholder="Enter Login" name="login" required>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
        <button type="submit">Register</button>
        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a>.</p>
    </form>
</div>
</body>
</html>

