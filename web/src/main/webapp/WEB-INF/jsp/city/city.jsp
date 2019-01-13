<%--
  Created by IntelliJ IDEA.
  User: bruno
  Date: 16/12/2018
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="City - ${cityDTO.name} (${cityDTO.id})">
<jsp:attribute name="body">
            <div class="panel panel-default">
                <div class="panel-body">
                    <img src="<c:url value='/images/cityImages/city-${cityDTO.id}.jpg'/>"
                         style="width: 400px; height: 250px"
                    />
                </div>
            </div>
    <div class="col-md-6">
        <h3>Drives FROM ${cityDTO.name}</h3>
        <table class="table table-hover">
            <thead>
            <tr>
                <td>ID</td>
                <td>From</td>
                <td>To</td>
                <td>Date</td>
                <td>Price</td>
                <td>Actions</td>
            </tr>
            </thead>
            <c:forEach var="drive" items="${fromDrives}">
            <tr class="">
                <td>
                    <c:out value="${drive.id}"></c:out>
                </td>
                <td>
                    <c:out value="${drive.fromCity.name}"></c:out>
                </td>
                <td>
                    <c:out value="${drive.toCity.name}"></c:out>
                </td>
                <td>
                    <c:out value="${drive.date}"></c:out>
                </td>
                <td>
                    <c:out value="${drive.price} CZK"></c:out>
                </td>
                <td>
                    <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/drives/${drive.id}">View</a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-6">
        <h3>Drives TO ${cityDTO.name}</h3>
        <table class="table table-hover">
            <thead>
            <tr>
                <td>ID</td>
                <td>From</td>
                <td>To</td>
                <td>Date</td>
                <td>Price</td>
                <td>Actions</td>
            </tr>
            </thead>
            <c:forEach var="drive" items="${toDrives}">
        <tr class="">
            <td>
                <c:out value="${drive.id}"></c:out>
            </td>
            <td>
                <c:out value="${drive.fromCity.name}"></c:out>
            </td>
            <td>
                <c:out value="${drive.toCity.name}"></c:out>
            </td>
            <td>
                <c:out value="${drive.date}"></c:out>
            </td>
            <td>
                <c:out value="${drive.price} CZK"></c:out>
            </td>
            <td>
                <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/drives/${drive.id}">View</a>
            </td>
        </tr>
        </c:forEach>
        </table>
    </div>

</jsp:attribute>
</my:pagetemplate>
