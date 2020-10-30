<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 30.10.2020
  Time: 07:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<h3>Nie znaleziono strony ${url}</h3>

<p>${errorMessage}</p>
<p>${exception}</p>

<a href="/books">Wróć do strony głównej</a>
</body>
</html>
