<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
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
<h1>Users:</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Click to delete</th>
    </tr>
    <c:forEach var = "user" items = "${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/deleteUser?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/">
    <button>Come back to Main Menu</button>
</form>
</body>
</html>
