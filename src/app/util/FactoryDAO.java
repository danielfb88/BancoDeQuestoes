package app.util;

import app.dao.iterface.*;
import app.dao.postgresdialect.*;

/**
 * Fábrica que gera Objetos DAO reduzindo o acoplamento do dialeto do banco de dados 
 * com a aplicação.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-08-2012
 *
 */
public class FactoryDAO {

	private FactoryDAO() { }
		
	/**
	 * GrupoDAO 
	 * @return GrupoDAO
	 */
	public static IGrupoDAO criarGrupoDAO() {
		return new GrupoDAO();
	}
	
	/**
	 *  UsuarioDAO
	 * @return
	 */
	public static IUsuarioDAO criarUsuarioDAO() {
		return new UsuarioDAO();
	}
	
	/**
	 * CursoDAO
	 * @return
	 */
	public static ICursoDAO criarCursoDAO() {
		return new CursoDAO();
	}
	
	/**
	 * DisciplinaDAO
	 * @return
	 */
	public static IDisciplinaDAO criarDisciplinaDAO() {
		return new DisciplinaDAO();
	}

}
