package dao;

import util.AbstractDAO;

public class DisciplinaDAO extends AbstractDAO {
	public Integer id_disciplina;
	public Integer id_curso;
	public String descricao;
	public String sigla;

	@Override
	protected void config() {
		nomeDaTabela = "disciplina";
		primaryKey = new String[] { "id_disciplina" };
	}
}
