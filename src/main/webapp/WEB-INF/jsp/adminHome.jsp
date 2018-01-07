<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="admin.title" /></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/signin.css' %>
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System. ${pageContext.session.getAttribute("role").login}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#"><fmt:message key="home" />
                        <span class="sr-only">(current)</span></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <form class="margin">
                    <select class="form-control" title="language" id="language" name="language"
                            onchange="location=this.options[this.selectedIndex].value">
                        <option value="http://localhost:8080/hr/adminHome?language=ru"
                            ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="http://localhost:8080/hr/adminHome?language=en"
                            ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>

                <form class="form-signin" action="FrontController" method="post">
                    <input type="hidden" name="command" value="">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="exit" />
                    </button>
                </form>
            </ul>
        </div>
    </nav>

    <div class="container">
        ADMIN HOME
    </div>
</body>
</html>
