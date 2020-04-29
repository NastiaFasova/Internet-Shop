<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
Let's add some products
<form action="${pageContext.request.contextPath}/products/add" method="post">
    <h1>Add product</h1>
    <p>Please fill in this form to add the product</p>
    <hr>
    <input type="text" placeholder="Product name " name="name" required>
    <input type="text" placeholder="Product price" name="price" required>
    <hr>
    <button type="submit">Add product</button>
</form>
</body>
</html>
