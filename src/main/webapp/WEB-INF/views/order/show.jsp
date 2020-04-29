<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order</title>
</head>
<body>
<h1>Products in your order:</h1>
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
