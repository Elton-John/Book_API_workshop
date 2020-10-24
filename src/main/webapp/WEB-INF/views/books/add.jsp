<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 24.10.2020
  Time: 08:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add book</title>
</head>
<body>
<form:form modelAttribute="book" method="post" action="${pageContext.request.contextPath}/admin/books/add">
    <p>
    <form:label path="isbn">ISBN</form:label>
    <form:input path="isbn"/>
    </p>
    <p>
        <form:label path="title">Title</form:label>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="author">Auhtor</form:label>
        <form:input path="author"/>
    </p>
    <p>
        <form:label path="publisher">publisher</form:label>
        <form:input path="publisher"/>
    </p>
    <p>
        <form:label path="type">type</form:label>
        <form:input path="type"/>
    </p>
    <form:button type="submit">SAVE</form:button>

</form:form>
</body>
</html>
