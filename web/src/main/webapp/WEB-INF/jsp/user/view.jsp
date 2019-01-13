<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User Administration">
<jsp:attribute name="body">

<!--    <form method="post" action="${pageContext.request.contextPath}/user/delete/${user.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>-->

    <div class="row">
        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <img src="<c:url value='/images/userImages/user-${user.id}.jpg'/>"
                         style="width: 150px; height: 150px"
                    />
                </div>
            </div>
        </div>
    </div>
    <p>ID = ${user.id}</p>
    <p>Name = ${user.firstName} ${user.lastName}</p>
    <p>Login = ${user.login}</p>
    <h2>Comments</h2>
    <div class="row">
        <table class="table">
            <tbody>
                <c:forEach items="${comments}" var="comment">
                    <tr>
                        <td>${comment.content}</td>
                        <td><my:a href="/drives/drive/${comment.drive.id}" class="btn btn-primary">View drive</my:a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</jsp:attribute>
</my:pagetemplate>