<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="menu-container">
	<a href="${pageContext.request.contextPath}/">Home</a> | <a
		href="${pageContext.request.contextPath}/user_list"> User List</a> |
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="${pageContext.request.contextPath}/add_form"> Add User </a>          |    </security:authorize>

</div>