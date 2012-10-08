package dao.hibernate;

import java.util.List;

import dominio.disciplina.Disciplina;
import util.hibernate.HibernateAbstractDAO;

public class DisciplinaDAO extends HibernateAbstractDAO<Disciplina> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param disciplina
	 * @return
	 */
	public List<Disciplina> listar(Disciplina disciplina) {
		return super.listar(Disciplina.class, disciplina);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Disciplina> listarTodos() {
		return super.listarTodos(Disciplina.class);
	}
}
