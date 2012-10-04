package dao;

import dao.hibernate.GrupoDAO;

/**
 * Fábrica de DAOs
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * 
 */
public class DaoFactory {
	private static GrupoDAO grupoDAO;

	public static GrupoDAO getGrupoDAO() {
		if (grupoDAO == null)
			grupoDAO = new GrupoDAO();

		return grupoDAO;
	}
}
