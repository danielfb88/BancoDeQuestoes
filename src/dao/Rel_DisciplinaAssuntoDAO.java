package dao;

import util.AbstractDAO;

public class Rel_DisciplinaAssuntoDAO extends AbstractDAO {
	public Integer id_disciplina_assunto;
	public Integer id_disciplina;
	public Integer id_assunto;

	@Override
	protected void config() {
		nomeDaTabela = "disciplina_assunto";
		primaryKey = new String[] { "id_disciplina_assunto" };
		is_autoIncrement = true;
	}
}
