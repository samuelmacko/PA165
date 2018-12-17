<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Welcome to Blablacar !</h1>
        <p class="lead">In this seminar project for PA165</p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/example/foo/1/platypus55?b=42"
              role="button">Find drives</a></p>
    </div>
              
         
</jsp:attribute>
</my:pagetemplate>
