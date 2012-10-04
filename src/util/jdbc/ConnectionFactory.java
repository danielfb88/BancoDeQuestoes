package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexão
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 14-09-2012
 * @version 1.0
 * 
 */
public class ConnectionFactory {

	/**
	 * Retorna objeto Connection para todo tipo de operação de BD.
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		Connection conn = null;

		try {
			String url = "jdbc:postgresql://localhost:5432/banco_de_questoes";
			String login = "postgres";
			String senha = "123456";

			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(url, login, senha);
			if (conn == null)
				throw new Exception("Não foi possível conectar ao banco de dados: " + url);

		} catch (ClassNotFoundException e1) {
			e1.getMessage();
			e1.printStackTrace();
			System.exit(0);

		} catch (SQLException e2) {
			e2.printStackTrace();
			System.exit(0);

		} catch (Exception e3) {
			e3.printStackTrace();
			System.exit(0);
		}

		return conn;
	}

}