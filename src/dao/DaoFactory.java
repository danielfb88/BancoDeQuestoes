package dao;

import dao.hibernate.GrupoDAO;
import dao.hibernate.UsuarioDAO;

/**
 * Fábrica de DAOs
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * 
 */
public class DaoFactory {
	private static GrupoDAO grupoDAO;
	private static UsuarioDAO usuarioDAO;

	/**
	 * GrupoDAO
	 * 
	 * @return
	 */
	public static GrupoDAO getGrupoDAO() {
		if (grupoDAO == null)
			grupoDAO = new GrupoDAO();

		return grupoDAO;
	}

	/**
	 * UsuarioDAO
	 * 
	 * @return
	 */
	public static UsuarioDAO getUsuarioDAO() {
		if (usuarioDAO == null)
			usuarioDAO = new UsuarioDAO();

		return usuarioDAO;
	}
}
