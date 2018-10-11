<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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

		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${spring:mvcUrl('GC#listar').build()}">Gestão de Gastos</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
							<li>
								<a href="${spring:mvcUrl('RC#listarRoles').build()}">Lista de Roles</a>
							</li>
							<li>
								<a href="${spring:mvcUrl('RC#abrirFormularioInclusao').build()}">Cadastro de Roles</a>
							</li>
						</security:authorize>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<security:authorize access="isAuthenticated()">
							<li><a href="#"> <security:authentication property="principal" var="cliente" />Cliente: ${cliente.nome}
							</a></li>
							<li><a href="<c:url value="/logout"/>">SAIR</a></li>
						</security:authorize>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
		
			<h1>Lista de Gastos</h1>
			<hr/>
			<form action="<c:url value='/pesquisar'/>" method="get">
				<div class="form-group">
					<label>Data:</label>
					<input type="text" id="data" name="data" value="" class="form-control" />
				</div>
				<input type="submit" value="Filtrar" class="btn btn-primary">
			</form>
			<hr/>
			<p> ${mensagem} </p>
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>DATA</th>
						<th>DESCRIÇÃO</th>
						<th>VALOR</th>
						<th>AÇÃO</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gastos}" var="gasto">
						<tr>
							<td><fmt:formatDate value="${gasto.data}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
							<td>${gasto.descricao}</td>
							<td>${gasto.valor}</td>
							<td>
								<form action="${spring:mvcUrl('GC#detalhar').arg(0, gasto.id).build()}" method="get">
									<input type="submit" value="Ver Detalhe">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		
	</body>
	
</html>