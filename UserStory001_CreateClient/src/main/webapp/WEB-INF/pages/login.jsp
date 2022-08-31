<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<div class="page-title">Login</div>
	<div class="login-container">
		<h3>Please enter your username and password</h3>
		<br> <br>
		<!-- /login?error=true -->
		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<form method="POST"
			action="${pageContext.request.contextPath}/j_spring_security_check">
			<table>
				<tr>
					<td>Username *</td>
					<td><input name="userName" /></td>
				</tr>

				<tr>
					<td>Password *</td>
					<td><input type="password" name="password" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>

		<span class="error-message">${error }</span>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>