<%--
  Created by IntelliJ IDEA.
  User: hh
  Date: 2025/12/5
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="simpleStyle.css">
</head>
<body>
<h1>Lost&Found</h1>
    <block>
        <form action="Signup-Servlet" method="post">
            <h2>SIGN UP</h2>
            <input type="text" name="username" placeholder="Input your username" required>
            <input type="password" name="password" placeholder="Input your password" required>
            <input type="password" name="re-password" placeholder="Input your password again" required>
            <p>${error}</p>
            <input type="submit" name="signup" value="Sign Up">
        </form>
    </block>
</body>
</html>
