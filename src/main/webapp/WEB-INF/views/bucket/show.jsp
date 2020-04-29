<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your products</title>
</head>
<body>
<h1>Products in your bucket:</h1>
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
                <a href = "${pageContext.request.contextPath}/deleteProduct?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/products/all">
    <button>Add more products</button>
</form>
<form action="${pageContext.request.contextPath}/order/new">
    <button>Checkout</button>
</form>
<br>
<form action="${pageContext.request.contextPath}/">
    <button>Come back to the Main Menu</button>
</form>
</body>
</html>
