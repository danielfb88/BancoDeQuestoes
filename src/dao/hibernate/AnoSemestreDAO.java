package dao.hibernate;

import java.util.List;

import dominio.curso.AnoSemestre;
import util.hibernate.HibernateAbstractDAO;

public class AnoSemestreDAO extends HibernateAbstractDAO<AnoSemestre> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param anoSemestre
	 * @return
	 */
	public List<AnoSemestre> listar(AnoSemestre anoSemestre) {
		return super.listar(AnoSemestre.class, anoSemestre);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<AnoSemestre> listarTodos() {
		return super.listarTodos(AnoSemestre.class);
	}
}
