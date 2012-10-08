package dao.hibernate;

import java.util.List;

import dominio.curso.Grade;
import util.hibernate.HibernateAbstractDAO;

public class GradeDAO extends HibernateAbstractDAO<Grade> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param grade
	 * @return
	 */
	public List<Grade> listar(Grade grade) {
		return super.listar(Grade.class, grade);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Grade> listarTodos() {
		return super.listarTodos(Grade.class);
	}
}
