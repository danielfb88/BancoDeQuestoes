package dao;

import util.AbstractDAO;

public class Rel_GradePeriodoDAO extends AbstractDAO {
	public Integer id_grade_periodo;
	public Integer id_grade;
	public Integer id_periodo;

	@Override
	protected void config() {
		nomeDaTabela = "grade_periodo";
		primaryKey = new String[] { "id_grade_periodo" };
	}
}
