<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Products</title>
    <style>
        table{
            width: 750px;
            margin: 40px 30px;
            font-family:  Arial, serif;
        }
        td{
            padding:5px;
            text-align: center;
        }
        th{
            padding:5px;
        }
        h1{
            margin-left: 50px;
            font-family:  Arial, serif;
            margin-top: 30px;
        }
        button{
            width: 200px;
            background-color: royalblue;
            color: ivory;
            font-size: 15px;
            border-radius: 20px;
            padding: 7px;
            margin-left:250px;
        }
        button:hover{
            border-color: deepskyblue;
            border-width: 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>All products:</h1>
<table border="1">
    <tr>
        <th>Product Id</th>
        <th>Product Name</th>
        <th>Product Price</th>
        <th>Click to delete</th>
    </tr>
    <c:forEach var = "product" items = "${products}">
        <tr>
            <td>
                <c:out value="${product.id}" />
            </td>
            <td>
                <c:out value="${product.name}" />
            </td>
            <td>
                <c:out value="${product.price}" />
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/product/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/product/add">
    <button>Add new products</button>
</form>
<br>
<form action="${pageContext.request.contextPath}/">
    <button>Come back to Main Menu</button>
</form>
</body>
</html>
