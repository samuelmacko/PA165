<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Users">
<jsp:attribute name="body">

    <table class="table">
        <caption>Users</caption>
        <thead>
        <tr>
            <th><p>ID</p></th>
            <th><p>Login</p></th>
            <th><p>FistName</p></th>
            <th><p>Last Name</p></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><p>${user.id}</p></td>
                <td><p><c:out value="${user.login}"/></p></td>
                <td><p><c:out value="${user.firstName}"/></p></td>
                <td><p><c:out value="${user.lastName}"/></p></td>
                <td><my:a href="/user/view/${user.id}" class="btn btn-primary">View</my:a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
