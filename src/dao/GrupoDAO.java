package dao;

import util.AbstractDAO;

public class GrupoDAO extends AbstractDAO {
	public Integer id_grupo;
	public String descricao;
	public Character tipo;

	@Override
	protected void config() {
		nomeDaTabela = "grupo";
		primaryKey = new String[] { "id_grupo" };
	}
}
