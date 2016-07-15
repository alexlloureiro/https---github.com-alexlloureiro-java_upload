package br.edu.devmedia.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.ws.rs.Consumes;
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

@Path("upload")
public class UploadArquivoService {

	String url= "C:/Users/alexandre/Documents/devmedia/jersey-rest-devmedia/WebContent/upload/";
	NoticiasDao imagemDao = new NoticiasDao();
	
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response uploadFile(
			@PathParam("id") Integer id,
			@FormDataParam("file") InputStream in,
			@FormDataParam("file") FormDataContentDisposition info) {
		try {
			Files.copy(in, new File(url + id + ".jpg").toPath());
			String arquivo = id + ".jpg";
			imagemDao.novaImagen("teste", arquivo, id);
			return Response.status(Status.OK).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor loalizado: " + e.getMessage()).build();
		}
	}
	
}
