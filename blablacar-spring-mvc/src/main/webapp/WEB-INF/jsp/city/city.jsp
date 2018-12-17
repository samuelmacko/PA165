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

<my:pagetemplate title="City - ${cityDTO.name}">
<jsp:attribute name="body">
    <div class="col-md-3">
    <table class="table table-hover">
        <tr class="">
            <td>ID</td>
            <td>
                <c:out value="${cityDTO.id}"></c:out>
            </td>
        </tr>
        <tr class="">
            <td>Name</td>
            <td>
                <c:out value="${cityDTO.name}"></c:out>
            </td>
        </tr>
    </table>
    </div>
</jsp:attribute>
</my:pagetemplate>
