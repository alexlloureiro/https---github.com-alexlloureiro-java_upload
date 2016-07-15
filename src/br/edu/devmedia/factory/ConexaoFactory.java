package br.edu.devmedia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConexao() {

		Connection connection = null;

		try {

			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			String url = "jdbc:mysql://127.0.0.1:3306/news_devmedia";
			String username = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {
				System.out.println("STATUS--->Conectado com sucesso!");
			} else {
				System.out
						.println("STATUS--->Não foi possivel realizar conexão");
			}
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Não foi possivel conectar ao Banco de Dados Mysql.");
			return null;
		}
	}

	public static void closeConnetion(Connection conn, PreparedStatement pst,
			ResultSet rs) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
