package dao;

import util.AbstractDAO;

public class Rel_GradePeriodo_DisciplinaDAO extends AbstractDAO {
	public Integer id_grade_periodo__disciplina;
	public Integer id_grade_periodo;
	public Integer id_disciplina;

	@Override
	protected void config() {
		nomeDaTabela = "grade_periodo__disciplina";
		primaryKey = new String[] { "id_grade_periodo__disciplina" };
	}
}
