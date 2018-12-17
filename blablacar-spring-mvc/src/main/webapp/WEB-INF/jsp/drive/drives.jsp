<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Drives">
<jsp:attribute name="body">
    <table class="table table-hover">
        <thead>
        <tr>
            <td>Name</td>
            <td>Actions</td>
        </tr>
        </thead>
        <c:forEach var="drive" items="${drives}">
        <tr class="">
            <td>
                <c:out value="${drive.name}"></c:out>
            </td>
            <td>
                <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/drives/${drive.id}">View</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</jsp:attribute>
</my:pagetemplate>
