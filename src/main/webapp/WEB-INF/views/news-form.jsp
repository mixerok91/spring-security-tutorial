<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add news</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h1>Add news</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2>${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </c:if>
    </div>

    <div>
        <form:form action="saveNews" modelAttribute="news" method="POST">
            <form:hidden path="id" />
            <table>
                <tbody>
                    <tr>
                        <td><label>Title:</label></td>
                        <td><form:input path="title"/></td>
                    </tr>
                    <tr>
                        <td><label>Brief:</label></td>
                        <td><form:input path="brief"/></td>
                    </tr>
                    <tr>
                        <td><label>Date:</label></td>
                        <td><form:input placeholder="dd.MM.yyyy, HH:mm" path="date"/></td>
                    </tr>
                    <tr>
                        <td><label>Text:</label></td>
                        <td><form:input path="text"/></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Add news to Data Base" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <br>
    <a href="${pageContext.request.contextPath}/news/list">Back to News List page</a>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
