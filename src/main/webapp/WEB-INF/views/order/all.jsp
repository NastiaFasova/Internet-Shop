<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>
<h1>List of your orders:</h1>
<table border="1">
    <tr>
        <th>Order Id</th>
        <th>List of Orders</th>
        <th>Click to delete</th>
    </tr>
    <c:forEach var = "order" items = "${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/order/show?id=${order.id}">Show details</a>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/order/delete?id=${order.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/">
    <button>Come back to the Main Menu</button>
</form>
</body>
</html>
