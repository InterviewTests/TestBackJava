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
		<link href="${cssPath}/jquery-ui.css" rel="stylesheet" type="text/css"/>
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
		
			<h3>Detalhe do Gasto</h3>
			<hr/>
			<c:choose>
				<c:when test="${(gasto.categoria != null) && (not empty gasto.categoria.nomeCategoria)}">
					<form action="<c:url value='/'/>" method="get">
						<input type="hidden" name="id" value="${gasto.id}">
						<div class="form-group">
							<label>Data:</label>
							<input type="text" id="data" name="data" value="<fmt:formatDate value="${gasto.data}" pattern="dd/MM/yyyy HH:mm:ss"/>" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Descrição:</label>
							<input type="text" id="descricao" name="descricao" value="${gasto.descricao}" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Valor:</label>
							<input type="text" id="valor" name="valor" value="${gasto.valor}" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Usuário:</label>
							<input type="text" id="usuario" name="usuario.nome" value="${gasto.usuario.nome}" 
								   class="form-control" readonly="readonly" />
							<input type="hidden" name="usuario.id" value="${gasto.usuario.id}">
						</div>
						<div class="form-group">
							<label>Categoria:</label>
							<input type="text" id="categoria" name="categoria" value="${gasto.categoria.nomeCategoria}" 
								   class="form-control" readonly="readonly" />
						</div>
						<hr/>
						<input type="submit" value="Voltar para Lista de Gastos" class="btn btn-primary">
					</form>
				</c:when>
				<c:otherwise>
					<form:form action="${spring:mvcUrl('GC#cadastrarCategoria').build()}" method="post" modelAttribute="gasto">
						<input type="hidden" name="id" value="${gasto.id}">
						<div class="form-group">
							<label>Data:</label>
							<input type="text" id="data" name="data" value="<fmt:formatDate value="${gasto.data}" pattern="dd/MM/yyyy HH:mm:ss"/>" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Descrição:</label>
							<input type="text" id="descricao" name="descricao" value="${gasto.descricao}" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Valor:</label>
							<input type="text" id="valor" name="valor" value="${gasto.valor}" 
								   class="form-control" readonly="readonly" />
						</div>
						<div class="form-group">
							<label>Usuário:</label>
							<input type="text" id="usuario" name="usuario.nome" value="${gasto.usuario.nome}" 
								   class="form-control" readonly="readonly" />
							<input type="hidden" name="usuario.id" value="${gasto.usuario.id}">
						</div>
						<div class="form-group">
							<label>Categoria:</label> <form:errors path="categoria.nomeCategoria" />
							<input type="text" id="categoria" name="categoria.nomeCategoria" value="${gasto.categoria.nomeCategoria}" maxlength="50" 
								   class="form-control ui-widget" />
						</div>
						<hr/>
						<input type="submit" value="Atualizar" class="btn btn-primary">
					</form:form>
				</c:otherwise>
			</c:choose>
		</div>
		
	</body>
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.12.1.js'/>"></script>
	<script>
		$( function() {
			var categorias = '${categorias}';
			categorias = categorias.replace("[","");
			categorias = categorias.replace("]","");
			categorias = categorias.replace(" ","");
			categorias = categorias.split(",");
			
			$('#categoria').autocomplete({
				source: categorias
			});
		    
		});
  	</script>
	
</html>