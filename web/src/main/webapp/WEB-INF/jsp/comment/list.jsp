<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Comments">
<jsp:attribute name="body">

    <table class="table">
        <caption>Comments</caption>
        <thead>
        <tr>
            <th>Author</th>
            <th>Created date</th>
            <th>Drive</th>
            <th>Content</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="comment">
            <tr>
                <td><my:a href="/user/view/${comment.authorID}" class="btn btn-primary">Author</my:a></td>
                <td><c:out value="${comment.createdDate}"/></td>
                <td><my:a href="/drive/view/${comment.drive.id}" class="btn btn-primary">Drive</my:a></td>
                <td><c:out value="${comment.content}"/></td>
                <td><my:a href="/comment/view/${comment.id}" class="btn btn-primary">Detail</my:a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
