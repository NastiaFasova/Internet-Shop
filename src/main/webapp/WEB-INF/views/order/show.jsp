<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order</title>
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
<h1>Information about your order:</h1>
<table border="1">
    <tr>
        <th>Product id</th>
        <th>Product Name</th>
        <th>Product Price</th>
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
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/order/all">
    <button>Show all orders</button>
</form>
<br>
<form action="${pageContext.request.contextPath}/">
    <button>Come back to the Main Menu</button>
</form>
</body>
</html>
