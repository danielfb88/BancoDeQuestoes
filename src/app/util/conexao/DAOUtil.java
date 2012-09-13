package app.util.conexao;
//TODO: Excluir singleton
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe Singleton de Conexão ao Banco e Facade do objeto Connection.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-08-2012
 * @version 1.0
 * 
 */
public class DAOUtil {
	private static DAOUtil instance = null;
	private Connection con = null;

	private DAOUtil() {
	}

	public static DAOUtil getInstance() {
		if (instance == null)
			instance = new DAOUtil();
		return instance;
	}

	/**
	 * Retorna objeto Connection para todo tipo de operação de BD.
	 * 
	 * @return Connection
	 */
	private Connection getConnection() {
		try {
			if (this.con == null) {
				String url = "jdbc:postgresql://localhost:5432/banco_de_questoes";
				String login = "postgres";
				String senha = "123456";

				Class.forName("org.postgresql.Driver");

				this.con = DriverManager.getConnection(url, login, senha);
			}

		} catch (ClassNotFoundException e1) {
			e1.getMessage();
			e1.printStackTrace();

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return this.con;
	}

	/**
	 * Retorna um Statment. Objeto utilizado para a execução de consultas
	 * simples (estaticas), sem entrada de dados.
	 * 
	 * @return Statement
	 */
	public Statement getStatement() throws SQLException {
		return this.getConnection().createStatement();
	}

	/**
	 * Retorna um PreparedStatment. Objeto utilizado para a preparação de
	 * consultas com entrada de dados.
	 * 
	 * @param String
	 *            sql
	 * @return PreparedStatement
	 */
	public PreparedStatement getPreparedStatement(String sql)
			throws SQLException {
		return this.getConnection().prepareStatement(sql);
	}

	/**
	 * Fecha a conexão.
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() {
		if (this.con != null) {
			try {
				this.con.close();

			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();

			} finally {
				this.con = null;
			}
		}
	}

	/**
	 * Inicia transacao
	 */
	public void iniciarTransacao() {
		try {
			this.getConnection().setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Commita a transação em andamento
	 */
	public void commit() {
		try {
			if (this.getConnection().getAutoCommit() == false) {
				this.getConnection().commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rollback na tranzacao em andamento
	 */
	public void rollback() {
		try {
			if (this.getConnection().getAutoCommit() == false) {
				this.getConnection().rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finaliza transacao
	 */
	public void finalizarTransacao() {
		try {
			this.getConnection().setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}