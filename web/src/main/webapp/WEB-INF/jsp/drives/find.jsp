<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Find a drive">
<jsp:attribute name="body">
    <form:form action="${pageContext.request.contextPath}/drives/find" method="post"
               modelAttribute="driveFormDTO" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="fromCityId" cssClass="col-sm-2 control-label">From</form:label>
            <div class="col-sm-10">
                    <form:select path="fromCityId" cssClass="form-control">
                        <c:forEach items="${cities}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <form:label path="toCityId" cssClass="col-sm-2 control-label">To</form:label>
            <div class="col-sm-10">
                    <form:select path="toCityId" cssClass="form-control">
                        <c:forEach items="${cities}" var="city">
                            <form:option value="${city.id}">${city.name}</form:option>
                        </c:forEach>
                    </form:select>
            </div>
        </div>
        <div class="form-group">
            <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
            <div class="col-sm-10">
                <input id="input-date" type="date" path="date" name = "dateOld" class="form-control"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-top:1%; margin-right:15px;">Find a drive
            </button>
    </form:form>
    <form method="post" action="${pageContext.request.contextPath}/drives/find/all">
                <button type="submit" class="btn btn-primary">Find all drives</button>
            </form>

</jsp:attribute>
</my:pagetemplate>