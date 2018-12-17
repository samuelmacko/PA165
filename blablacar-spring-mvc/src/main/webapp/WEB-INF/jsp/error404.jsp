<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<my:pagetemplate title="Page Not Found (404)">
    <jsp:attribute name="body">
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <h3>Might be you misspelling the url, or us not implementing this functionality. :-/</h3>
        <a href="${contextPath}" class="btn btn-warning">Back to homepage</a>
    </jsp:attribute>
</my:pagetemplate>