package dao;

import util.AbstractDAO;

public class Rel_ProvaPerguntaDAO extends AbstractDAO {
	public Integer id_prova_pergunta;
	public Integer id_prova;
	public Integer id_pergunta;
	public Integer ordem;

	@Override
	protected void config() {
		nomeDaTabela = "prova_pergunta";
		primaryKey = new String[] { "id_prova_pergunta" };
	}
}
