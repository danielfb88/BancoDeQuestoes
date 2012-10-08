package dao.hibernate;

import java.util.List;

import dominio.prova.Prova;
import util.hibernate.HibernateAbstractDAO;

public class ProvaDAO extends HibernateAbstractDAO<Prova> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param prova
	 * @return
	 */
	public List<Prova> listar(Prova prova) {
		return super.listar(Prova.class, prova);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Prova> listarTodos() {
		return super.listarTodos(Prova.class);
	}
}
