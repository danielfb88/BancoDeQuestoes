package dao.hibernate;

import java.util.List;

import dominio.prova.Resposta;
import util.hibernate.HibernateAbstractDAO;

public class RespostaDAO extends HibernateAbstractDAO<Resposta> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param resposta
	 * @return
	 */
	public List<Resposta> listar(Resposta resposta) {
		return super.listar(Resposta.class, resposta);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Resposta> listarTodos() {
		return super.listarTodos(Resposta.class);
	}
}
