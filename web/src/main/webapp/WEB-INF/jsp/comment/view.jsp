<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Comments Detail">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/comment/delete/${comment.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>

    <p><b>Created date</b> ${comment.createdDate}</p>
    <p><b>Update date </b>${comment.updateDate}</p>
    <div>
        <p>Content</p>
        <p>${comment.content}</p>
    </div>
    <div>
        <p>Author</p>
        <a href="/pa165/user/view/${comment.authorID}">Author</a>
    </div>
    <div>
        <p>Drive </p>
        <a href="/pa165/drives/view/${comment.drive.id}">Drive</a>
    </div>

</jsp:attribute>
</my:pagetemplate>