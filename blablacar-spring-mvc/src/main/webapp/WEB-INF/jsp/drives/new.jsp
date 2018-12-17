<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create a drive">
<jsp:attribute name="body">

    <div class="row">

            <form:form action="${pageContext.request.contextPath}/drives/create" id="drive-create-form" method="post"
                       modelAttribute="driveCreateDTO" cssClass="form-horizontal">
                     <%--modelAttribute="driveFormDTO" cssClass="form-horizontal">--%>

                <%--<div class="form-group ${name_error?'has-error':''}">--%>
                <div class="form-group">

                    <%--<form:label path="fromCityId" cssClass="col-sm-2 control-label">From</form:label>--%>
                    <form:label path="fromCity" cssClass="col-sm-2 control-label">From</form:label>
                    <div class="col-sm-10">
                            <%--<form:select id="select-to" path="fromCityId" type="text" form="drive-create-form"--%>
                        <form:select id="select-to" path="fromCity" type="text" form="ride-create-form"
                                     cssClass="form-control">
                            <c:forEach items="${cities}" var="city">
                               <%--<option value="${city.id}">--%>
                               <option value="${city}">
                                       ${city.name}
                               </option>
                            </c:forEach>
                        </form:select>
                        <%--<form:errors path="destinationPlaceId" cssClass="help-block"/>--%>
                    </div>


                    <%--<form:label path="toCityId" cssClass="col-sm-2 control-label">To</form:label>--%>
                    <form:label path="toCity" cssClass="col-sm-2 control-label">To</form:label>
                    <div class="col-sm-10">
                        <%--<form:select id="select-to" path="toCityId" type="text" form="drive-create-form"--%>
                        <form:select id="select-to" path="toCity" type="text" form="drive-create-form"
                                     cssClass="form-control">
                        <c:forEach items="${cities}" var="city">
                           <%--<option value="${city.id}">--%>
                           <option value="${city}">
                                   ${city.name}
                           </option>
                        </c:forEach>
                    </form:select>
                    </div>

                    <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
                    <div class="col-sm-10">
                        <form:input id="input-date" path="date" type="date" form="drive-create-form"
                                    cssClass="form-control"/>
                        <%--<form:errors path="departure" cssClass="help-block"/>--%>
                    </div>

                    <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                    <div class="col-sm-10">
                        <form:input id="input-price" path="price" type="number" cssClass="form-control"/>
                    </div>

                    <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
                    <div class="col-sm-10">
                        <form:input id="input-capacity" path="capacity" type="number" cssClass="form-control"/>
                        <%--<form:errors path="capacity" cssClass="help-block"/>--%>
                    </div>

                    <button type="submit" class="btn btn-default pull-right" style="margin-top:1%; margin-right:15px;">Create a drive</button>
                </div>
            </form:form>
    </div>

</jsp:attribute>
</my:pagetemplate>
