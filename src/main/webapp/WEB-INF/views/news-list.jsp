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

            <h2>Welcome, ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
            <h5>You are logged as <c:if test="${sessionScope.isAdmin}"> Admin </c:if>
                                  <c:if test="${sessionScope.isAdmin == false}"> User </c:if>
            </h5>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <h2><a href="/login">LogIn for more actions</a></h2>
        </c:if>
    </div>

    <c:if test="${sessionScope.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/news/showFormForAdd">Create news</a>
    </c:if>

    <br><br>
    <form action="/news/deleteFewNews" method="get">
    <c:forEach var="tempNews" items="${newsList}">
        <div>
            <c:url var="showNewsLink" value="/news/showOneNews">
                <c:param name="newsId" value="${tempNews.id}"/>
            </c:url>
            <c:url var="updateLink" value="/news/showFormForUpdate">
                <c:param name="newsId" value="${tempNews.id}"/>
            </c:url>
            <c:url var="deleteOneNewsLink" value="/news/deleteOneNews">
                <c:param name="newsId" value="${tempNews.id}"/>
            </c:url>


            <table>
                <tbody>
                <tr>
                    <td><label>Title:</label></td>
                    <td>${tempNews.title}</td>
                </tr>
                <tr>
                    <td><label>Brief:</label></td>
                    <td>${tempNews.brief}</td>
                </tr>
                <tr>
                    <td><label>Date:</label></td>
                    <td>${tempNews.formatDate}</td>
                </tr>
                <tr>
                    <td><label>Text:</label></td>
                    <td>${tempNews.text}</td>
                </tr>
                </tbody>
            </table>

            <a href="${showNewsLink}">Show news</a>
            <c:if test="${sessionScope.isAdmin}"> || <a href="${updateLink}">Update</a> ||
                <a href="${deleteOneNewsLink}">Delete</a>
                <br>
                <input type="checkbox" id="newsIdsForDelete" name="${tempNews.id}">
                <label for="newsIdsForDelete">Delete news</label>
            </c:if>
        </div>
        <br>
    </c:forEach>
        <c:if test="${sessionScope.isAdmin == true && newsList.size()>0}">
            <button type="submit">Delete checked news</button>
        </c:if>
    </form>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
