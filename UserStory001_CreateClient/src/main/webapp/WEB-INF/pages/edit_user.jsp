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

	<div class="page-title">Modify User</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<form:form modelAttribute="userForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
		<form:input path="clientNumber" value="${userInfor.clientNumber }" type="hidden"/>
			<tr>
				<td>First Name *</td>
				<td><form:input path="firstName"
						value="${userInfor.firstName }" /></td>
				<td><form:errors path="firstName" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Last Name *</td>
				<td><form:input path="lastName" value="${userInfor.lastName }" /></td>
				<td><form:errors path="lastName" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Gender *</td>
				<td><form:select path="gender">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsGender }"></form:options>
					</form:select></td>
				<td><form:errors path="gender" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Date of Birth *</td>
				<td><form:input path="birth" value="${userInfor.birth }" /></td>
				<td><form:errors path="birth" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Identity Number *</td>
				<td><form:input path="identity" value="${userInfor.identity }"/></td>
				<td><form:errors path="identity" class="error-message" /></td>
			</tr>
			<tr>
				<td>Marital Status *</td>
				<td><form:select path="marital">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsMarital }"></form:options>
					</form:select></td>
				<td><form:errors path="marital" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Address *</td>
				<td><form:input path="address" value="${userInfor.address }" /></td>
				<td><form:errors path="address" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Country *</td>
				<td><form:select path="country">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsCountry }"></form:options>
					</form:select></td>
				<td><form:errors path="country" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Update"></td>
			</tr>
		</table>
	</form:form>



	<jsp:include page="footer.jsp" />
</body>
</html>