<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.contextPath }/">
<title>User Story 001 - Create Client</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="menu.jsp" />

	<div class="page-title">Current User List</div>

	<table border="1" style="width: 100%">
		<tr>
			<th>Client Number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
		</tr>
		<c:forEach items="${listUser }" var="lsUser">
			<tr>
				<th>${lsUser.clientNumber }</th>
				<th>${lsUser.firstName }</th>
				<th>${lsUser.lastName }</th>
				<th>${lsUser.gender }</th>
				<th><td><a
					href="${pageContext.request.contextPath}/user_infor?clientNumber=${lsUser.clientNumber}">
						Inquire</a></td></th>

				<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/edit_user?clientNumber=${lsUser.clientNumber}"> Modify
						</a>
					</security:authorize></th>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="footer.jsp" />
</body>
</html>