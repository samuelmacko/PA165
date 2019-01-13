<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="My Drives">
<jsp:attribute name="body">
    
    <div class="row mb-2">
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
          <strong class="d-inline-block mb-2 text-primary">Drives as driver</strong>
          <table class="table">
            <thead>
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>View</th>
                </tr>
        </thead>  
            <tbody>
                <c:forEach items="${drivesAsDriver}" var="drive">
                    <tr>
                        <td>${drive.fromCity.name}</td>
                        <td>${drive.toCity.name}</td>
                        <td><my:a href="/drives/view/${drive.id}" class="btn btn-primary">View drive</my:a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      </div>
    </div>
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
          <strong class="d-inline-block mb-2 text-primary">Drives as passenger</strong>
          <table class="table">
            <thead>
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>View</th>
                </tr>
        </thead>  
            <tbody>
                <c:forEach items="${drivesAsPassenger}" var="drive">
                    <tr>
                        <td>${drive.fromCity.name}</td>
                        <td>${drive.toCity.name}</td>
                        <td><my:a href="/drives/view/${drive.id}" class="btn btn-primary">View drive</my:a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      </div>
    </div>
  </div>
</jsp:attribute>
</my:pagetemplate>