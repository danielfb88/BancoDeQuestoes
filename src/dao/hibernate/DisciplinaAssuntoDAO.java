package dao.hibernate;

import java.util.List;

import util.hibernate.HibernateAbstractDAO;
import dominio.disciplina.DisciplinaAssunto;

public class DisciplinaAssuntoDAO extends HibernateAbstractDAO<DisciplinaAssunto> {
	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param disciplina
	 * @return
	 */
	public List<DisciplinaAssunto> listar(DisciplinaAssunto disciplinaAssunto) {
		return super.listar(DisciplinaAssunto.class, disciplinaAssunto);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<DisciplinaAssunto> listarTodos() {
		return super.listarTodos(DisciplinaAssunto.class);
	}
}
