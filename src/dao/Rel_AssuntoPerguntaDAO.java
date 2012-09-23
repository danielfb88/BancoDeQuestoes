package dao;

import util.AbstractDAO;

public class Rel_AssuntoPerguntaDAO extends AbstractDAO {
	public Integer id_assunto_pergunta;
	public Integer id_assunto;
	public Integer id_pergunta;

	@Override
	protected void config() {
		nomeDaTabela = "assunto_pergunta";
		primaryKey = new String[] { "id_assunto_pergunta" };
	}
}