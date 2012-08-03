package app.util;

import app.dao.*;
import app.dao.postgresDialect.UsuarioDAO;

/**
 * Fábrica que gera Objetos DAO reduzindo o acoplamento da aplicação.
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * @version 1.0
 *
 */
public class FactoryDAO {

	private FactoryDAO() { }
	
	public static IUsuarioDAO createUsuarioDAO() {
		return new UsuarioDAO();
	}

}
