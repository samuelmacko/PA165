<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <!-- bootstrap loaded from content delivery network -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Blablacar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:if test="${userSession.user.superUser}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.admin"/><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><my:a href="/user/list/all"><f:message key="navigation.admin.users"/></my:a></li>
                        <li><my:a href="/drives/list"><f:message key="navigation.admin.drives"/></my:a></li>
                        <li><my:a href="/cities/"><f:message key="navigation.admin.cities"/></my:a></li>
                        <li><my:a href="/comment/list"><f:message key="navigation.admin.comments"/></my:a></li>
                    </ul>
                </li>
                </c:if>>
                <c:if test="${userSession.userIsLoggedIn}">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/pa165/drives/find">Find drive</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${userSession.userIsLoggedIn}">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/pa165/drives/new">Offer drive</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${userSession.userIsLoggedIn}">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/pa165/user/show/${userSession.userId}">My drives</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${userSession.userIsLoggedIn}">
                <ul class="nav navbar-nav fixed-right">
                    <li>
                        <a href="/pa165/logout">Logout</a>
                    </li>
                </ul>
                </c:if>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <!-- authenticated user info -->
    <c:if test="${sessionScope['user'].isUserLoggedIn}">
        <div class="row">
            <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:out value="${sessionScope['user'].login}"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer">
    </footer>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
