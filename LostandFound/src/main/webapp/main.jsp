<%--
  Created by IntelliJ IDEA.
  User: hh
  Date: 2025/12/8
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" type="text/css" href="simpleStyle.css">
</head>
<body>
    <h1>Lost & Found</h1>
    <block>
        <form action="NewItem-Servlet" method="post">
            <p>Submit a FOUND item here</p>
            <input type="text" name="name" placeholder="Item name" required>
            <label>
                Item Type
                <select name="type" required>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                </select>
            </label>
            <textarea name="content" placeholder="Detailed description" rows="5" cols="50"></textarea>
            <input type="text" name="contact" placeholder="Contact" required>
        </form>
        <form action="NewItem-Servlet" method="post">
            <p>Search for a LOST item here</p>
            <input type="text" name="name" placeholder="Item name" required>
            <label>
                Item Type
                <select name="type" required>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                </select>
            </label>
        </form>
    </block>
    <c:forEach var="item" items="${requestScope.itemList}">
        <block>
            <p>Item Name: ${item.name}</p>
            <p>Description: ${item.content}</p>
            <p>Contact: ${item.contact}</p>
        </block>
    </c:forEach>
</body>
</html>
