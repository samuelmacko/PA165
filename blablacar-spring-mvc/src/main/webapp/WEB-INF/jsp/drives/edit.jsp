<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create a drive">
<jsp:attribute name="body">

    <div class="row">

            <form:form action="${pageContext.request.contextPath}/drives/edit" id="drive-create-form" method="post"
                       modelAttribute="driveCreateDTO" cssClass="form-horizontal">
                     <%--modelAttribute="driveFormDTO" cssClass="form-horizontal">--%>

                <%--<div class="form-group ${name_error?'has-error':''}">--%>
                <div class="form-group">

                    <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                    <div class="col-sm-10">
                        <form:input id="input-price" path="price" type="number" cssClass="form-control"/>
                    </div>

                    <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
                    <div class="col-sm-10">
                        <form:input id="input-capacity" path="capacity" type="number" cssClass="form-control"/>
                            <%--<form:errors path="capacity" cssClass="help-block"/>--%>
                    </div>

                    <button type="submit" class="btn btn-default pull-right" style="margin-top:1%; margin-right:15px;">Edit a drive</button>
                </div>
            </form:form>
    </div>

</jsp:attribute>
</my:pagetemplate>
