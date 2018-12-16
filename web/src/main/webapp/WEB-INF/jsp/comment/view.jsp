<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Comments Administration">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/comment/delete/${comment.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>

    <p>ID ${comment.id}</p>
    <p>Created date ${comment.createdDate}</p>
    <p>Update date ${comment.updatedDate}</p>
    <div>
        <span>Author</span>
        <td><my:a href="/user/view/${comment.authorID}" class="btn btn-primary">Author</my:a></td>
    </div>
    <div>
        <span>Drive</span>
        <td><my:a href="/drive/view/${comment.driveID}" class="btn btn-primary">Drive</my:a></td>
    </div>

</jsp:attribute>
</my:pagetemplate>