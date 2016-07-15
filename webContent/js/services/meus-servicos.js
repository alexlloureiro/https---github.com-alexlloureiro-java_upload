angular.module("app").factory("servicosAPI", function($http, config){
	var _postLogin = function(login){
		return $http.post(config.baseUrl +'login', login); 
	};
	
    var _getNoticia = function(id){
		return $http.get(config.baseUrl +'noticia/noticia_id/' + id); 
	};
	
	var _getExcluiNoticia = function(id){
		return $http.get(config.baseUrl +'noticia/excluir/' + id); 
	};
	
	var _getNoticias = function(){
		return $http.get(config.baseUrl +'noticia/listar'); 
	};
	
	var _getImagens = function(){
		return $http.get(config.baseUrl +'noticia/list_img'); 
	};
	
	var _postNoticias = function(noticia){
		return $http.post(config.baseUrl +'noticia/new',noticia); 
	};
    
    var _postAlteraNoticia = function(id, news){
		return $http.post(config.baseUrl +'noticia/alterar/'+id, news); 
	};
		
	return {
		postLogin         :   _postLogin,
		getNoticia        :   _getNoticia,
		getExcluiNoticia  :   _getExcluiNoticia,
		getNoticias       :   _getNoticias,
		getImagens        :   _getImagens,
		postNoticias      :   _postNoticias,
        postAlteraNoticia :   _postAlteraNoticia
		
	};
	
});