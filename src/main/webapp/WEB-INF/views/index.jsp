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
<form action="${pageContext.request.contextPath}/allProducts">
    <button>Show all products</button>
</form>
</form>
<form action="${pageContext.request.contextPath}/allUsers">
    <button>Show all users</button>
</form>
<form action="${pageContext.request.contextPath}/addProduct">
    <button>Add products</button>
</form>
<form action="${pageContext.request.contextPath}/showBucket">
    <button>Show your bucket</button>
</form>
</body>
</html>
