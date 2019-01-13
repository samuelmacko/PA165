<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Comments">
<jsp:attribute name="body">

    <table class="table">
        <tr>
            <th><p>Author</p></th>
            <th><p>Created date</p></th>
            <th><p>Drive</p></th>
            <th><p>Content</p></th>
            <th><p>Detail</p></th>
        </tr>
        <tbody>
        <c:forEach items="${comments}" var="comment">
            <tr>
                <td><my:a href="/user/view/${comment.authorID}" class="btn btn-primary">Author</my:a></td>
                <td><p><c:out value="${comment.createdDate}"/></p></td>
                <td><my:a href="/drives/view/${comment.drive.id}" class="btn btn-primary">Drive</my:a></td>
                <td><p><c:out value="${comment.content}"/></p></td>
                <td><my:a href="/comment/view/${comment.id}" class="btn btn-primary">Detail</my:a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
