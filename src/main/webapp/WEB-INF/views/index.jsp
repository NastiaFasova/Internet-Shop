<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
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
</body>
</html>
