package dao;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
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
