<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>News manager</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2>${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </c:if>
    </div>


    <c:url var="deleteOneNewsLink" value="/news/deleteOneNews">
        <c:param name="newsId" value="${news.id}"/>
    </c:url>
    <c:url var="updateLink" value="/news/showFormForUpdate">
        <c:param name="newsId" value="${news.id}"/>
    </c:url>

    <div>
        <table>
            <tbody>
            <tr>
                <td><label>Title:</label></td>
                <td>${news.title}</td>
            </tr>
            <tr>
                <td><label>Brief:</label></td>
                <td>${news.brief}</td>
            </tr>
            <tr>
                <td><label>Date:</label></td>
                <td>${news.formatDate}</td>
            </tr>
            <tr>
                <td><label>Text:</label></td>
                <td>${news.text}</td>
            </tr>
            </tbody>
        </table>
        <br>
        <c:if test="${sessionScope.isAdmin}"><a href="${updateLink}">Update</a> ||
            <a href="${deleteOneNewsLink}">Delete</a>
        </c:if>
        <br>
        <a href="${pageContext.request.contextPath}/news/list">Back to News List page</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
