package dao.jdbc;

import util.jdbc.AbstractDAO;

public class RespostaDAO extends AbstractDAO {
	public Integer id_resposta;
	public Integer id_pergunta;
	public String descricao;
	public Integer correta;
	public String observacao;

	@Override
	protected void config() {
		nomeDaTabela = "resposta";
		primaryKey = new String[] { "id_resposta" };
		is_autoIncrement = true;
	}
}
