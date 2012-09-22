package dao;

import util.AbstractDAO;

public class PeriodoDAO extends AbstractDAO {
	public Integer id_periodo;
	public String descricao;
	public Integer numero;

	@Override
	protected void config() {
		nomeDaTabela = "periodo";
		primaryKey = new String[] { "id_periodo" };
	}
}
