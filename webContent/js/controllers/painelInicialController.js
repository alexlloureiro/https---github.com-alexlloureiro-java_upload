app.controller('painelInicialController', function($scope, servicosAPI, $http) {
	$scope.showCadastro = false;
	$scope.noticia = objNoticia();
	$scope.allNoticias = {};

	$scope.dateOptions = {
		changeYear : true,
		changeMonth : true,
		yearRange : '1900:-0',
		dateFormat : "dd-mm-yy",
		showButtonPanel : true

	};

	$scope.abreCadastroNoticia = function() {
		$scope.noticia = objNoticia();
		$scope.showCadastro = true;
	}

	$scope.listarNoticias = function() {
		servicosAPI.getNoticias().success(function(data) {
			$scope.allNoticias = data;
			console.log(data);
		}).error(function(erro) {
			alert("Falha em obter as notícias");
		});
	};

	$scope.getNoticia = function(id) {
		servicosAPI.getNoticia(id).success(function(data) {
			$scope.noticia = data;
			$scope.showCadastro = true;
		}).error(function(erro) {
			alert("Falha em obter as notícias");
		});
	};

	function getFormattedDate(date) {
		var year = date.getFullYear();
		var month = (1 + date.getMonth()).toString();
		month = month.length > 1 ? month : '0' + month;
		var day = date.getDate().toString();
		day = day.length > 1 ? day : '0' + day;
		return month + '/' + day + '/' + year;
	}

	$scope.processaFormNoticia = function() {
		if ($scope.noticia.id === -1) {
			$scope.cadastrarNovaNoticia();
		} else {
			$scope.alterarNoticia();
		}
	};

	$scope.cadastrarNovaNoticia = function() {

		var year = $scope.noticia.data.getFullYear();
		var month = (1 + $scope.noticia.data.getMonth()).toString();
		month = month.length > 1 ? month : '0' + month;
		var day = $scope.noticia.data.getDate().toString();
		day = day.length > 1 ? day : '0' + day;

		$scope.noticia.data = month + '/' + day + '/' + year;

		var string = day + '/' + month + '/' + year;

		$scope.noticia.data = null;
		$scope.noticia.data = string;

		servicosAPI.postNoticias($scope.noticia).success(function(data) {
			alert("Cadastro efetuado com sucesso!");
			$scope.showCadastro = false;
			$scope.noticia = objNoticia();
			$scope.listarNoticias();
		}).error(function(erro) {
			alert("Falha ao cadastrar notícia!");
		});

	};

	$scope.alterarNoticia = function() {

		var year = $scope.noticia.data.getFullYear();
		var month = (1 + $scope.noticia.data.getMonth()).toString();
		month = month.length > 1 ? month : '0' + month;
		var day = $scope.noticia.data.getDate().toString();
		day = day.length > 1 ? day : '0' + day;

		$scope.noticia.data = month + '/' + day + '/' + year;

		var string = day + '/' + month + '/' + year;

		$scope.noticia.data = null;
		$scope.noticia.data = string;

		servicosAPI.postAlteraNoticia($scope.noticia.id, $scope.noticia)
				.success(function(data) {
					$.gritter.add({
						title : "Sucesso!",
						text : "Notícia alterada com sucesso!",
						class_name : "gritter"
					});

					$scope.showCadastro = false;
					$scope.noticia = objNoticia();
					$scope.listarNoticias();
				}).error(function(erro) {
					$.gritter.add({
						title : "Falha!",
						text : "Ocorreu um erro!",
						class_name : "gritter"
					});
				});

	};

	$scope.listarNoticias();

	$scope.excluiNoticia = function(noticiaid) {
        
        if(!confirm("Deseja Realmente Excluir?")) return false;
        
		servicosAPI.getExcluiNoticia(noticiaid).success(
				function(data) {
					
                    $.gritter.add({
						title : "Sucesso!",
						text : "Notícia excluida com sucesso!",
						class_name : "gritter"
					});
                    
					$scope.listarNoticias();

				}).error(function(erro) {
			alert("Falha exclusão notícia");
		});
	};

});

function objNoticia() {
	return {
		id : -1,
		titulo : "",
		descricao : "",
		texto : "",
		data : ""
	};
}
