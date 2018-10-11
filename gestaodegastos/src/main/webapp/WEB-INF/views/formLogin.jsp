<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestão de Gastos</title>
		
		<c:url value="/resources/css" var="cssPath"/>
		<link href="${cssPath}/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="${cssPath}/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
	</head>
<body>

	<div class="container">
	
		<h3>Login para acesso ao Sistema de Gestão de Gastos</h3>
		
		<hr/>

		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				${SPRING_SECURITY_LAST_EXCEPTION.message}
			</div>
		</c:if>

		<form:form servletRelativeAction="/login" method="post">
			
			<div class="form-group">
				<label>E-mail:</label>
				<input type="text" name="username" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Senha:</label>
				<input type="password" name="password" class="form-control"/>
			</div>
			<hr/>
			<input type="submit" value="Entrar" class="btn btn-primary">
		</form:form>
		
	</div>
	
</body>
</html>