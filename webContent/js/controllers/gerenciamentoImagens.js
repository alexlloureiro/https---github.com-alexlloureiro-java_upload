app.controller('GerenciarImagensController', function($scope, servicosAPI,
		$routeParams, $location, FileUploader) {

	servicosAPI.getNoticia($routeParams.noticiaid).success(function(data) {
		$scope.noticia = data;
	}).error(function(erro) {
		alert("Falha em obter notícia");
	});

	servicosAPI.getNoticias().success(function(data) {
		$scope.allNoticias = data;
	}).error(function(erro) {
		alert("Falha em obter as notícias");
	});

	servicosAPI.getImagens().success(function(data) {
		$scope.imagens = data;
	}).error(function(erro) {
		alert("Falha em obter notícia");
	});

	var uploader = $scope.uploader = new FileUploader({

		url : 'rest/upload/' + $routeParams.noticiaid
	});

	uploader.filters.push({
		name : "tamanhoFila",
		fn : function(item, options) {
			return this.queue.length < 4;
		}

	});

	uploader.onBeforeUploadItem = function(item) {
		item.formData.push({
			titulo : item.titulo

		})

	};

	uploader.onSuccessItem = function(fileItem) {
		servicosAPI.getImagens().success(function(data) {
			$.gritter.add({
				title : "Sucesso!",
				text : "Imagem incluída com sucesso sucesso!",
				class_name : "gritter"
			});

		}).error(function(erro) {
			alert("Falha em obter notícia");
		});
		fileItem.remove();
	};

	uploader.addWhenAddingFailed = function(fileItem) {
		console.info("Erro ao adicionar elemento", fileItem);

	};

	

	
})