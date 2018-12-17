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
            <th>Id</th>
            <th>Driver</th>
            <th>Capacity</th>
            <th>Date</th>
            <th>From</th>
            <th>To</th>
            <th>...</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drives}" var="drive">
            <tr>
                <td><c:out value="${drive.id}"/></td>
                <td><c:out value="${drive.driver.firstName} ${drive.driver.lastName}"/></td>
                <td><c:out value="${drive.capacity}"/></td>
                <td><c:out value="${drive.date.date}.${drive.date.month}.${drive.date.year}"/></td>
                <td><c:out value="${drive.fromCity.name}"/></td>
                <td><c:out value="${drive.toCity.name}"/></td>
                <td>
                    <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/drives/view/${drive.id}">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
