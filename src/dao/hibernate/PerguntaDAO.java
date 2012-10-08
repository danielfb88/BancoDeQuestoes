package dao.hibernate;

import java.util.List;

import dominio.prova.Pergunta;
import util.hibernate.HibernateAbstractDAO;

public class PerguntaDAO extends HibernateAbstractDAO<Pergunta> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param pergunta
	 * @return
	 */
	public List<Pergunta> listar(Pergunta pergunta) {
		return super.listar(Pergunta.class, pergunta);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Pergunta> listarTodos() {
		return super.listarTodos(Pergunta.class);
	}
}
