package dao;

import util.AbstractDAO;

public class AssuntoDAO extends AbstractDAO {
	public Integer id_assunto;
	public String descricao;

	@Override
	protected void config() {
		nomeDaTabela = "assunto";
		primaryKey = new String[] { "id_assunto" };
	}
}
