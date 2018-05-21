//Aplicação angular
//FIXME Separar em factory

angular.module("TestBackJava", [ "ngMessages" ]);
	angular.module("TestBackJava").controller("HomeController", function($scope, $http, $location ) {
		$scope.title = "TestBackJava";
		$scope.logo = "img/logo.png";
		$scope.filtro = {
			"codigoUsuario" : null,
			"data" : null
		}
		$scope.filtroGastos = false;
		$scope.user = {
			"codigoUsuario" : 6,
			"senha" : '6'
		};
		$scope.message = {
			message : null
		};
		$scope.categoria = "";
		// functions - separar
		$scope.sair = function() {
			location.reload();
		}
		$scope.entrar = function(user) {
			if (user.codigoUsuario == '' || user.senha == '') {
				alert('Erro');
			} else {
				$http({
					url : location + '/entrar',
					method : "POST",
					data : {
						"codigoUsuario" : user.codigoUsuario,
						"senha" : user.senha
					}
				}).then(function(response) {
					$scope.userLogado = response.data;
					$scope.filtro.codigoUsuario = $scope.userLogado.codigoUsuario;
					$scope.message = null;
					$scope.user = null;
				}, function(response) {
					console.log('fail');
					$scope.message = response.data;
				});
			}
		}
		//--
		$scope.mostrarGastosPorFiltro = function() {
			$scope.filtroGastos = true;
		}

		$scope.listarGastos = function(filtro) {
			$scope.dataGastos = [];
			$http({
				url : location + '/listarGastos',
				method : "POST",
				data : {
					'codigoUsuario' : filtro.codigoUsuario,
					"data" : filtro.data
				}
			}).then(function(response) {
				$scope.message = null;

				$('#table').bootstrapTable('destroy');
				$('#table').bootstrapTable({
					data : response.data
				});

			}, function(response) {
				console.log('fail');
				$scope.message = response.data;
				$scope.dataGastos = null;
			});
		}

		$scope.categorizar = function(categoria) {
			var id = angular.element('#id').val();
			$http({
				url : location + '/salvarCategoria',
				method : "POST",
				data : {
					'id' : id,
					"categoria" : categoria
				}
			}).then(function(response) {
				$scope.message = 'Categoria inserida';
				$('#modal').modal('hide');
				$scope.listarGastos({
					'codigoUsuario' : $scope.userLogado.codigoUsuario,
					"data" : $scope.filtro.data
					
				});

			}, function(response) {
				console.log('fail');
				$scope.message = response.data;
				$scope.dataGastos = null;
				$('#modal').modal('hide');
			});
		}
		
		
	});