package dao.hibernate;

import java.util.List;

import util.hibernate.HibernateAbstractDAO;
import dominio.usuario.Grupo;

public class GrupoDAO extends HibernateAbstractDAO<Grupo> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param grupo
	 * @return
	 */
	public List<Grupo> listar(Grupo grupo) {
		return super.listar(Grupo.class, grupo);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Grupo> listarTodos() {
		return super.listarTodos(Grupo.class);
	}

}
