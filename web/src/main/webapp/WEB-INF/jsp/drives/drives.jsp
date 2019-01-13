<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Drives">
<jsp:attribute name="body">

    <table class="table table-hover">
        <caption>Drives</caption>
        <thead>
        <tr>
            <th><p>Id<p></th>
            <th><p>Driver</p></th>
            <th><p>Capacity</p></th>
            <th><p>Date</p></th>
            <th><p>From</p></th>
            <th><p>To</p></th>
            <th>...</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drives}" var="drive">
            <tr>
                <td><p><c:out value="${drive.id}"/></p></td>
                <td><p><c:out value="${drive.driver.firstName} ${drive.driver.lastName}"/></p></td>
                <td><p><c:out value="${drive.capacity}"/></p></td>
                <td><p><c:out value="${drive.date.date}.${drive.date.month}.${drive.date.year}"/></p></td>
                <td><p><c:out value="${drive.fromCity.name}"/></p></td>
                <td><p><c:out value="${drive.toCity.name}"/></p></td>
                <td>
                    <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/drives/view/${drive.id}">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
