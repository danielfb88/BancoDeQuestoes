package dao.jdbc;

import util.jdbc.AbstractDAO;

public class Rel_AssuntoPerguntaDAO extends AbstractDAO {
	public Integer id_assunto_pergunta;
	public Integer id_assunto;
	public Integer id_pergunta;

	@Override
	protected void config() {
		nomeDaTabela = "assunto_pergunta";
		primaryKey = new String[] { "id_assunto_pergunta" };
		is_autoIncrement = true;
	}
}