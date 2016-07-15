package br.edu.devmedia.entidade;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Noticia {

	private int id;
	private String titulo;
	private String descricao;
	private String data;
	private String texto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", descricao="
				+ descricao + ", data=" + data + ", texto=" + texto + "]";
	}

	
	
}
