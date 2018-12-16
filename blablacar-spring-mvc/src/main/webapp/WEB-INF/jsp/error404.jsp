<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="blablacar-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<blablacar-tags:page-template title="404">
    <jsp:attribute name="head">
        <!--Load only necessary files-->
        <meta name="google-signin-client_id"
              content="332736943859-mrr2173fc1kseq1l2i4h0na68mnpmbp3.apps.googleusercontent.com">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/welcome.css"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/style.css" type="text/css"
              crossorigin="anonymous">

    </jsp:attribute>
    <jsp:attribute name="body">

        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <h1>This page is invalid.</h1>
        <a href="${contextPath}" class="btn btn-warning">Back to homepage</a>

    </jsp:attribute>
    <jsp:attribute name="foot">

    </jsp:attribute>
</blablacar-tags:page-template>