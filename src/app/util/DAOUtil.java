package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * @version 1.0
 * 
 * Classe Singleton de Conexão ao Banco e Facade do objeto Connection.
 *
 */
public class DAOUtil {
	private static DAOUtil instance = null;
    private Connection con = null;

    private DAOUtil() { }
    
    public static DAOUtil getInstance() {
    	if (instance == null) 
    		instance = new DAOUtil();
    	return instance;
    }

    /**
     * Retorna objeto Connection para todo tipo de operação de BD.
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
        	e2.getMessage();
        	e2.printStackTrace();
        }

        return this.con;
    }

    /**
     * Retorna um Statment. 
     * Objeto utilizado para a execução de consultas simples (estaticas), sem entrada de dados.
     * 
     * @return Statement
     */
    public Statement getStatement() {
    	Statement stmt = null;
    	
    	try {
			stmt = this.getConnection().createStatement();
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
    	return stmt;
    }

    /**
     * Retorna um PreparedStatment. 
     * Objeto utilizado para a preparação de consultas com entrada de dados.
     * 
     * @param String sql
     * @return PreparedStatement
     */
    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement p_stmt = null;
        
    	try {
    		p_stmt = getConnection().prepareStatement(sql);
    	
    	} catch (SQLException e) {
    		e.getMessage();
    		e.printStackTrace();
    	}
    	return p_stmt;
    }

    /**
     *  Fecha a conexão.
     *  
     * @throws SQLException
     */
    public void closeConnection() throws SQLException{        
    	if(this.con != null) {
    		try {
    			this.con.close();
    			
    		} catch (SQLException e) {
    			e.getMessage();
    			e.printStackTrace();
    		}
        }
    }

}