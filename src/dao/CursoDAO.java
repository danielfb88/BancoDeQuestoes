package dao;

import util.AbstractDAO;

public class CursoDAO extends AbstractDAO {
	public Integer id_curso;
	public String descricao;
	public String sigla;
	public String tipo_graduacao;

	@Override
	protected void config() {
		nomeDaTabela = "curso";
		primaryKey = new String[] { "id_curso" };
	}
}
