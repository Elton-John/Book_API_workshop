<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 24.10.2020
  Time: 09:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>submit deleting</title>
</head>
<body>
Do you want delete ${title}?
<a href="/admin/books/all">No</a>

<a href="/admin/books/delete/${id}">Yes, delete</a>
</body>
</html>
