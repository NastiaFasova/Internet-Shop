<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <style>
        div{
            width: 550px;
            margin: 20px auto;
        }
        input{
            display: block;
            width:400px;
            margin: 20px auto;
            padding: 10px;
            border-radius: 10px;
        }
        button{
            display: block;
            width:170px;
            margin: 30px auto;
            background-color: royalblue;
            color: ivory;
            font-size: 20px;
            border-radius: 10px;
            padding: 10px;
        }
        h1{
            margin-left: 190px;
            margin-bottom: 40px;
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
    <form action="${pageContext.request.contextPath}/products/add" method="post">
        <h1>Add product</h1>
        <input type="text" placeholder="Product name " name="name" required>
        <input type="text" placeholder="Product price" name="price" required>
        <button type="submit">Add product</button>
    </form>
</div>
</body>
</html>
