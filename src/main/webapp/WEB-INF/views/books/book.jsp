<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 24.10.2020
  Time: 09:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<c:out value="${book.isbn}"/>
<c:out value="${book.title}"/>
<c:out value="${book.author}"/>
<c:out value="${book.publisher}"/>
<c:out value="${book.type}"/>
</body>
</html>
