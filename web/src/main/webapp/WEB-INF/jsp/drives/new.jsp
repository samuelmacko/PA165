<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create a drive">
<jsp:attribute name="body">
    <form:form action="${pageContext.request.contextPath}/drives/create"  method="post"
               modelAttribute="driveCreateDTO" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="fromCity" cssClass="col-sm-2 control-label">From</form:label>
                <div class="col-sm-10">
                    <form:select path="fromCity" cssClass="form-control">
                        <c:forEach items="${cities}" var="city">
                            <form:option value="${city}">${city.name}</form:option>
                        </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <form:label path="toCity" cssClass="col-sm-2 control-label">To</form:label>
                <div class="col-sm-10">
                    <form:select path="toCity" cssClass="form-control">
                        <c:forEach items="${cities}" var="city">
                            <form:option value="${cityFacade.findCityById(city.id)}">${city.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
        </div>
        <div class="form-group">        
            <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
                <div class="col-sm-10">
                    <form:input id="input-date" path="date" type="date" form="drive-create-form" cssClass="form-control"/>
                </div>
        </div>
        <div class="form-group">
            <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
            <div class="col-sm-10">
                <form:input id="input-price" path="price" type="number" cssClass="form-control"/>
            </div>
        </div>
        <div class="form-group">    
            <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
            <div class="col-sm-10">
                <form:input id="input-capacity" path="capacity" type="number" cssClass="form-control"/>
                <%-- <form:errors path="capacity" cssClass="help-block"/> --%>
            </div>
        </div>
            <button type="submit" class="btn btn-primary" style="margin-top:1%; margin-right:15px;">Create a drive</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
