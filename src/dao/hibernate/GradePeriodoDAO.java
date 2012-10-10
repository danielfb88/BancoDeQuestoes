package dao.hibernate;

import java.util.List;

import util.hibernate.HibernateAbstractDAO;

import dominio.curso.GradePeriodo;

public class GradePeriodoDAO extends HibernateAbstractDAO<GradePeriodo> {
	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param gradePeriodo
	 * @return
	 */
	public List<GradePeriodo> listar(GradePeriodo gradePeriodo) {
		return super.listar(GradePeriodo.class, gradePeriodo);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<GradePeriodo> listarTodos() {
		return super.listarTodos(GradePeriodo.class);
	}
}
