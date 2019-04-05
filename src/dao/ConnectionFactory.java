package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConexao() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/crud", "root" , "filhas");
		} catch (Exception e) {
			throw new RuntimeException("Erro de conexão: " + e);
		}
	}
}
