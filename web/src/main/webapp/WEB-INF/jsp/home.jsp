<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Welcome to blablacar">
<jsp:attribute name="body">

    <c:if test="${not userSession.userIsLoggedIn}">
        <h1>Please login before proceeding</h1>
        <form:form name="login" method="POST" action="login" modelAttribute="userSession">
             <table>
                 <tr>
                     <td>User Name</td>
                     <td><form:input path="login"/></td>
                 </tr>
                 <tr>
                     <td>Password</td>
                     <td><form:password path="password"/></td>
                 </tr>
                 <tr>
                     <td></td>
                     <td><input type="submit" value="Submit"/></td>
                 </tr>
             </table>
       </form:form>
    </c:if>
    <c:if test="${userSession.userIsLoggedIn}">
        <h2>Hello ${userSession.user.firstName} ${userSession.user.lastName}!</h2>
    </c:if>
</jsp:attribute>
</my:pagetemplate>
