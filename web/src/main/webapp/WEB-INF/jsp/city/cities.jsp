<%--
  Created by IntelliJ IDEA.
  User: bruno
  Date: 16/12/2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Cities">
<jsp:attribute name="body">
    <table class="table table-hover">
        <thead>
        <tr>
            <td><p>Name</p></td>
            <td><p>Actions</p></td>
        </tr>
        </thead>
        <c:forEach var="city" items="${cities}">
        <tr class="">
            <td><p>
                <c:out value="${city.name}"></c:out>
            </p>
            </td>
            <td><p>
                <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/cities/${city.id}">View</a>
            </p>
            </td>
        </tr>
        </c:forEach>
    </table>
</jsp:attribute>
</my:pagetemplate>
