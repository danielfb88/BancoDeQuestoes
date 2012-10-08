package dao.hibernate;

import java.util.List;

import dominio.disciplina.Assunto;
import util.hibernate.HibernateAbstractDAO;

public class AssuntoDAO extends HibernateAbstractDAO<Assunto> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param assunto
	 * @return
	 */
	public List<Assunto> listar(Assunto assunto) {
		return super.listar(Assunto.class, assunto);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Assunto> listarTodos() {
		return super.listarTodos(Assunto.class);
	}
}
