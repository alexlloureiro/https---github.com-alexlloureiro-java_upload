app.controller('loginController', function($scope, servicosAPI, $location) {

	$scope.login = {
		usuario : "",
		senha : ""
	};
	
	
	$scope.efetuarLogin = function() {
		
		if ($scope.login.usuario == "" || $scope.login.senha == "") {
			alert("Informe usuário e senha!");
			return;
		}
		
		servicosAPI.postLogin($scope.login).success(function(data) {
			if (data.logado=true) {
				
                $location.path("/painelinicial");
            	} else {
					$("#modal-validacao").modal("show");
					$scope.txtModal = "Usuário/Senha inválidos!";
				}
		}).error(function(erro) {
			console.log("erro ", erro);
		});

		
		
	}
});