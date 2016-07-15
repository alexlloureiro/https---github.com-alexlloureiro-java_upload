var app = angular.module('app', ['ngRoute', 'ui.mask', 'ui.date', 'ui.bootstrap', 'ngAnimate', 'angular-loading-bar',
                                 'angularFileUpload'])
.config(function($routeProvider){
	
	$routeProvider.when('/login',{
		templateUrl:'partials/login.html',
		controller: 'loginController'
	});
	
	$routeProvider.when('/painelinicial',{
		templateUrl:'partials/painelinicial.html',
		controller: 'painelInicialController'
	});
	
	
	$routeProvider.when('/gerenciarImagens/:noticiaid',{
		templateUrl:'partials/gerenciarImagens.html',
		controller: 'GerenciarImagensController'
	});
	
	$routeProvider.otherwise({ redirectTo:'/login'});
       		
});
