package dao.hibernate;

import java.util.List;

import dominio.curso.CoordenadorCurso;
import util.hibernate.HibernateAbstractDAO;

public class CoordenadorCursoDAO extends HibernateAbstractDAO<CoordenadorCurso> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param coordenadorCurso
	 * @return
	 */
	public List<CoordenadorCurso> listar(CoordenadorCurso coordenadorCurso) {
		return super.listar(CoordenadorCurso.class, coordenadorCurso);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<CoordenadorCurso> listarTodos() {
		return super.listarTodos(CoordenadorCurso.class);
	}
}
