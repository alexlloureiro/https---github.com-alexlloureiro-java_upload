package br.edu.devmedia.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.devmedia.entidade.Imagem;
import br.edu.devmedia.entidade.Noticia;
import br.edu.devmedia.entidade.Status;
import br.edu.devmedia.factory.ConexaoFactory;

public class NoticiasDao {

	public void novaNoticia(Noticia noticia) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			conn = ConexaoFactory.getConexao();
			pst = conn
					.prepareStatement("INSERT INTO TB_NOTICIA(TITULO, DESCRICAO, TEXTO, DATA, STATUS) VALUES(?, ?, ?, ?, ?)");
			pst.setString(1, noticia.getTitulo());
			pst.setString(2, noticia.getDescricao());
			pst.setString(3, noticia.getTexto());

			Date data = new Date(format.parse(noticia.getData()).getTime());

			pst.setDate(4, data);
			pst.setInt(5, Status.ATIVA.ordinal());

			pst.execute();
			conn.close();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
	}

	public List<Noticia> listarNoticias() {

		List<Noticia> noticias = new ArrayList<Noticia>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConexaoFactory.getConexao();

			pst = conn.prepareStatement("SELECT * FROM TB_NOTICIA");
			rs = pst.executeQuery();
			while (rs.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(rs.getInt("id"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTexto(rs.getString("texto"));
				noticia.setData(rs.getDate("data").toString());

				noticias.add(noticia);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
		return noticias;
	}

	public Noticia noticia(int id) {

		Noticia noticia = new Noticia();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConexaoFactory.getConexao();

			pst = conn
					.prepareStatement("select * from tb_noticia n where n.id = ? ");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {

				noticia.setId(rs.getInt("id"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTexto(rs.getString("texto"));
				noticia.setData(rs.getDate("data").toString());

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
		return noticia;
	}

	public void deleta_noticia(int id) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConexaoFactory.getConexao();

			pst = conn.prepareStatement("delete from tb_imagem where id_noticia = ? ");
			pst.setInt(1, id);
			pst.execute();			
			
			pst = null;
			
			pst = conn.prepareStatement("delete from tb_noticia where id = ? ");
			pst.setInt(1, id);
			pst.execute();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
	}
	
	public void editar_noticia(Integer id, Noticia noticia) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			
			conn = ConexaoFactory.getConexao();

			pst = conn.prepareStatement("update tb_noticia set titulo = ?, descricao= ?, texto=?, data=? where id=? ");
			pst.setString(1, noticia.getTitulo());
			pst.setString(2, noticia.getDescricao());
			pst.setString(3, noticia.getTexto());
			
			Date data = new Date(format.parse(noticia.getData()).getTime());
			pst.setDate(4, data);
			
			pst.setInt(5, id);
			
			pst.execute();

			conn.close();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
	}

	public void novaImagen(String titulo, String arquivo, Integer id_noticia ) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			
			conn = ConexaoFactory.getConexao();
			pst = conn
					.prepareStatement("INSERT INTO TB_IMAGEM(titulo, arquivo, id_noticia) VALUES(?, ?, ?)");
			pst.setString(1, titulo);
			pst.setString(2, arquivo);
			pst.setInt(3, id_noticia);

			pst.execute();
			conn.close();
		} catch (SQLException  e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
	}

	
	public List<Imagem> listarImagens() {

		List<Imagem> imagens = new ArrayList<Imagem>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConexaoFactory.getConexao();

			pst = conn.prepareStatement("SELECT * FROM TB_IMAGEM");
			rs = pst.executeQuery();
			while (rs.next()) {
				Imagem imagem = new Imagem();
				imagem.setId(rs.getInt("id"));
				imagem.setTitulo(rs.getString("titulo"));
				imagem.setArquivo(rs.getString("arquivo"));
				imagem.setId_noticia(rs.getInt("id_noticia"));
				
				imagens.add(imagem);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.closeConnetion(conn, pst, rs);
		}
		return imagens;
	}


}
