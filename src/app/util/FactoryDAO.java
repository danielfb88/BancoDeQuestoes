package app.util;

import app.dao.iterface.*;
import app.dao.postgresdialect.*;

/**
 * Fábrica que gera Objetos DAO reduzindo o acoplamento do dialeto do banco de dados com a aplicação.
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * @version 1.0
 *
 */
public class FactoryDAO {

	private FactoryDAO() { }
		
	/**
	 * GrupoDAO
	 * 
	 * @return GrupoDAO
	 */
	public static IGrupoDAO criarGrupoDAO() {
		return new GrupoDAO();
	}

}
