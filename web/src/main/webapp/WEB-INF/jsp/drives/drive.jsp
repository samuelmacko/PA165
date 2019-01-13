<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Drive detail">
<jsp:attribute name="body">
    <c:if test="${userSession.user.superUser || userFacade.getDriverDrives(userSession.userId).contains(driveDTO)}">
        <div class="col-md-12">
           <form method="post" action="${pageContext.request.contextPath}/drives/edit/${driveDTO.id}">
                <button type="submit" class="btn btn-primary">Edit</button>
            </form> 
        </div>
    </c:if>
    
    <c:if test="${userSession.user.superUser || userFacade.getDriverDrives(userSession.userId).contains(driveDTO)}">
        <div class="col-md-12">
            <form method="post" action="${pageContext.request.contextPath}/drives/delete/${driveDTO.id}">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </div>
    </c:if>

    <%--<form:form action="${pageContext.request.contextPath}/drives/delete" id="join-ride"--%>
               <%--method="get">--%>
        <%--<button type="submit" class="btn btn-primary" name="driveId" value="${driveDTO.id}">--%>
            <%--Remove ride--%>
        <%--</button>--%>
    <%--</form:form>--%>

    <div class="col-md-6">
        <h3>Drive: ${driveDTO.id}</h3>
        <table class="table table-hover">

            <tr>
                <td><p>driver</p></td>
                <td>
                    <p>
                        ${driveDTO.driver.firstName} ${driveDTO.driver.lastName}
                        <a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/user/view/${driveDTO.driver.id}">View</a>
                    </p>
                </td>
            </tr>
            <tr>
                <td><p>Capacity</p></td>
                <td><p>${driveDTO.capacity}</p></td>
            </tr>
            <tr>
                <td><p>From</p></td>
                <td><p>${driveDTO.fromCity.name}</p></td>
            </tr>
            <tr>
                <td><p>To</p></td>
                <td><p>${driveDTO.toCity.name}</p></td>
            </tr>
            <tr>
                <td><p>Price</p></td>
                <td><p>${driveDTO.price}</p></td>
            </tr>
            <tr>
                <td><p>Date</p></td>
                <td><p>${driveDTO.date.date}.${driveDTO.date.month}.${driveDTO.date.year}</p></td>
            </tr>
        </table>

        <h3>Customers</h3>
        <table class="table table-hover">
            <tr>
                <td>
                    <c:forEach items="${driveDTO.customers}" var="customer">
                        <p>${customer.firstName} ${customer.lastName}</p>
                    </c:forEach>
                </td>
            </tr>
        </table>

        <h3>Comments</h3>
        <table class="table table-hover">
            <c:forEach items="${driveDTO.comments}" var="comment">
                <tr>
                    <td><p>${comment.content}</p></td>
                    <td><p><a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/user/view/${comment.authorID}">Author</a></p></td>
                </tr>
            </c:forEach>
        </table>
    </div>


</jsp:attribute>
</my:pagetemplate>
