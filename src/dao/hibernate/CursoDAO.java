package dao.hibernate;

import java.util.List;

import dominio.curso.Curso;
import util.hibernate.HibernateAbstractDAO;

public class CursoDAO extends HibernateAbstractDAO<Curso> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param curso
	 * @return
	 */
	public List<Curso> listar(Curso curso) {
		return super.listar(Curso.class, curso);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Curso> listarTodos() {
		return super.listarTodos(Curso.class);
	}
}
