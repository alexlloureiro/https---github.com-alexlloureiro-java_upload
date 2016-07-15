package br.edu.devmedia.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import br.edu.devmedia.dao.NoticiasDao;
import br.edu.devmedia.entidade.Imagem;
import br.edu.devmedia.entidade.Noticia;

@Path("noticia")
public class NoticiaService {

	NoticiasDao noticiaDao = new NoticiasDao();
	String nome = "teste";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/new")
	public void logarUsuario(Noticia noticia) {
		System.out.println(noticia);
		noticiaDao.novaNoticia(noticia);

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterar/{id}")
	public void alterar(@PathParam("id") Integer id, Noticia noticia) {
		System.out.println("Id "+ id +"  Noticia objeto " + noticia);
		noticiaDao.editar_noticia(id, noticia);
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar")
	public List<Noticia> listarNoticias() {
		System.out.println("lista");
		List<Noticia> noticias = new ArrayList<Noticia>();

		noticias = noticiaDao.listarNoticias();
		return noticias;
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/excluir/{id}")
	public void excluir(@PathParam("id") Integer id) {
		noticiaDao.deleta_noticia(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/noticia_id/{id}")
	public Noticia noticia(@PathParam("id") Integer id) {
		
		Noticia noticia = new Noticia();

		noticia = noticiaDao.noticia(id);
		return noticia;
	}

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list_img")
	public List<Imagem> list_Img() {
		List<Imagem> imagens = new ArrayList<Imagem>();
		imagens = noticiaDao.listarImagens();
		return imagens;
	}



}
