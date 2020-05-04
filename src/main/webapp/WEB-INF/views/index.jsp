<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <style>
        div{
            margin:20px auto;
            width: 970px;
        }
        form{
            display: inline-block;
            margin-right: 10px;
            margin-top:20px;
        }
        h1{
            margin:20px 350px;
            font-size: 40px;
        }
        button{
            padding:10px;
            border-radius: 10px;
            background-color: royalblue;
            color: ivory;
            font-size: 17px;
            font-family: Calibri, sans-serif;
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
    <h1>Main Menu</h1>
    <form action="${pageContext.request.contextPath}/users/register">
        <button>Register</button>
    </form>
    <form action="${pageContext.request.contextPath}/products/buy">
        <button>Buy products</button>
    </form>
    <form action="${pageContext.request.contextPath}/bucket/show">
        <button>Show your bucket</button>
    </form>
    </form>
    <form action="${pageContext.request.contextPath}/products/all">
        <button>Add and delete products</button>
    </form>
    <form action="${pageContext.request.contextPath}/users/all">
        <button>Show all users</button>
    </form>
    <form action="${pageContext.request.contextPath}/order/all">
        <button>Show all orders</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout">
        <button>Logout</button>
    </form>
    <form action="${pageContext.request.contextPath}/injectData">
        <button>Inject test Data</button>
    </form>
</div>
</body>
</html>
