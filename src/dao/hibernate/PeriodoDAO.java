package dao.hibernate;

import java.util.List;

import dominio.curso.Periodo;
import util.hibernate.HibernateAbstractDAO;

public class PeriodoDAO extends HibernateAbstractDAO<Periodo> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param periodo
	 * @return
	 */
	public List<Periodo> listar(Periodo periodo) {
		return super.listar(Periodo.class, periodo);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Periodo> listarTodos() {
		return super.listarTodos(Periodo.class);
	}
}
