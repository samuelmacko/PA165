<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<mys:page-template title="Internal error">
    <jsp:attribute name="body">
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <h1>Page not foundd</h1>
        <a href="${contextPath}" class="btn btn-warning">Back to homepage</a>
    </jsp:attribute>
</mys:page-template>