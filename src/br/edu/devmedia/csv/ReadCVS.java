package br.edu.devmedia.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.devmedia.entidade.Noticia;
import br.edu.devmedia.entidade.Status;
import br.edu.devmedia.factory.ConexaoFactory;

public class ReadCVS {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException {

		ReadCVS obj = new ReadCVS();
		obj.run();

	}

	public void run() throws SQLException {
		// String csvFile = "http://localhost:8080/jersey-rest-devmedia/upload/1.csv";
		String csvFile = "C:/Users/alexandre/Documents/devmedia/jersey-rest-devmedia/WebContent/upload/1.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		conn = ConexaoFactory.getConexao();
		pst = conn.prepareStatement("INSERT INTO TB_NOTICIA(TITULO, DESCRICAO, TEXTO, STATUS) VALUES(?, ?, ?, ?)");
	
		
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] noticia = line.split(cvsSplitBy);

				System.out.println("Country [code= " + noticia[4] + " , name="
						+ noticia[5] + "]");
				
				
				pst.setString(1, noticia[0]);
				pst.setString(2, noticia[1]);
				pst.setString(3, noticia[2]);
                pst.setInt(4, Status.ATIVA.ordinal());
                
                pst.execute();
			    
			}
			
			conn.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

}
