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
    <title><fmt:message key="index.nav.authorization" /></title>
    <style>
        <%@include file='WEB-INF/css/bootstrap.min.css' %>
        <%@include file='WEB-INF/css/main.css' %>
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
                    <a class="nav-link" href="#"><fmt:message key="index.nav.authorization" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register"><fmt:message key="index.nav.register" /></a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form>
                    <input type="hidden" name="command" value="index">
                    <select class="form-control" title="language" id="language" name="language" onchange="submit()">
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>

    <div class="container">
        <form class="form-signin" name="Login" action="FrontController" method="post">
            <h1 class="form-signin-heading"><fmt:message key="index.container.h1" /></h1>

            <input type="hidden" name="command" value="authorization">

            <label for="inputLogin" class="sr-only"></label>
            <input name="login" type="text" id="inputLogin" class="form-control" pattern="[a-zA-Z][\s\w]{4,25}"
                   placeholder=<fmt:message key="index.container.login" /> required="" autofocus="">

            <label for="inputPassword" class="sr-only"></label>
            <input name="password" type="password" id="inputPassword" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder=<fmt:message key="index.container.password" /> required="" autocomplete="no">
            <a href="forgotPassword"><fmt:message key="password.forgot" /></a>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <h6 class="form-signin-heading"> ${notificationMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="index.container.log" />
            </button>
            <button class="btn btn-lg btn-primary btn-block" type="reset"><fmt:message key="index.container.reset" />
            </button>
        </form>

    </div>

    <script>
        <%@include file='WEB-INF/js/main.js' %>
    </script>
</body>
</html>