<%--
  Created by IntelliJ IDEA.
  User: hh
  Date: 2025/12/4
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" type="text/css" href="simpleStyle.css">
</head>
<body>
    <h1>Lost&Found</h1>
    <block>
        <form action="Login-Servlet" method="post">
            <h2>LOG IN</h2>
            <input type="text" name="username" placeholder="Input your username" required>
            <input type="password" name="password" placeholder="Input your password" required>
            <input type="submit" name="login" value="Log In">
        </form>
    </block>
    <block>
        <p>No account?</p>
        <a href="Signup-Servlet">Sign Up</a>
    </block>
</body>
</html>
