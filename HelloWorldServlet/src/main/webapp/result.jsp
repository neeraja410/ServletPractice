<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result Page</title>
</head>
<body>
    <h1>Hello, <%= request.getAttribute("name") %>!</h1>
    <h2>Age: <%= request.getAttribute("age") %></h2>
    <a href="index.html">Go Back to Form</a>
</body>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <p><%= request.getAttribute("usersList") %></p>
    <a href="index.html">Go Back to Form</a>
</body>
</html>


