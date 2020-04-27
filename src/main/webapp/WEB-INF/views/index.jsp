<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1>Main Menu</h1>

<form action="${pageContext.request.contextPath}/registration">
    <button>Register</button>
</form>
<form action="${pageContext.request.contextPath}/products/all">
    <button>Show all products</button>
</form>
</form>
<form action="${pageContext.request.contextPath}/users/all">
    <button>Show all users</button>
</form>
<form action="${pageContext.request.contextPath}/addProduct">
    <button>Add products</button>
</form>
<form action="${pageContext.request.contextPath}/buckets/all">
    <button>Show your bucket</button>
</form>

</body>
</html>
