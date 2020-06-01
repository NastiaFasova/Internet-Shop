<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Menu</title>
    <style>
        div{
            margin:20px auto;
            width: 1200px;
        }
        form{
            display: inline-block;
            margin-right: 10px;
            margin-top:20px;
        }
        h1{
            margin:30px 450px;
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
    <form action="${pageContext.request.contextPath}/registration">
        <button>Register</button>
    </form>
    <form action="${pageContext.request.contextPath}/product/buy">
        <button>Show or Buy products</button>
    </form>
    <form action="${pageContext.request.contextPath}/bucket/show">
        <button>Show your bucket</button>
    </form>
    <form action="${pageContext.request.contextPath}/products">
        <button>Add and delete products</button>
    </form>
    <form action="${pageContext.request.contextPath}/users">
        <button>Show all users</button>
    </form>
    <form action="${pageContext.request.contextPath}/orders">
        <button>Show all orders</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout">
        <button>Logout</button>
    </form>
    <form action="${pageContext.request.contextPath}/inject/data">
        <button>Inject test Data</button>
    </form>
</div>
</body>
</html>
