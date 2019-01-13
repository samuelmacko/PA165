<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create a drive">
<jsp:attribute name="body">
    <form:form action="${pageContext.request.contextPath}/drives/find" method="post"
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
    <button type="submit" class="btn btn-primary">Find</button>
    </form:form>
    <button type="submit" class="btn btn-primary">Find All</button>

</jsp:attribute>
</my:pagetemplate>