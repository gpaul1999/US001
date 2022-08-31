<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<div class="page-title">Add User</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<table style="text-align: left;">
		<tr>
			<td>Client Number</td>
			<td><a>${userInfor.clientNumber }</a></td>
		</tr>
		<tr>
			<td>First Name</td>
			<td><a>${userInfor.firstName }</a></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><a>${userInfor.lastName }</a></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td><a>${userInfor.gender }</a></td>
		</tr>
		<tr>
			<td>Birth</td>
			<td><a><fmt:formatDate value="${userInfor.birth}" type="date"
						pattern="dd/MM/yyyy" /></a></td>
		</tr>
		<tr>
			<td>Identity Number</td>
			<td><a>${userInfor.identity }</a></td>
		</tr>
		<tr>
			<td>Marital Status</td>
			<td><a>${userInfor.marital }</a></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><a>${userInfor.address }</a></td>
		</tr>
		<tr>
			<td>Country</td>
			<td><a>${userInfor.country }</a></td>
		</tr>
		<tr>
			<td></td>
			<td><security:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/edit_user?clientNumber=${userInfor.clientNumber}">
						Modify </a>
				</security:authorize></td>
		</tr>
	</table>



	<jsp:include page="footer.jsp" />
</body>
</html>