package dao;

import java.util.List;

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

	/**
	 * Lista Periodos pelo Id da Grade
	 * 
	 * @param id_grade
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarPeriodosPorGrade(Integer id_grade) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT p.id_periodo, p.descricao, p.numero ");
		builder.append("FROM periodo p ");

		builder.append("JOIN grade_periodo gp ");
		builder.append("ON (gp.id_periodo = p.id_periodo) ");

		builder.append("JOIN grade g ");
		builder.append("ON (gp.id_grade = g.id_grade) ");

		builder.append("WHERE g.id_grade = " + id_grade + " ");
		builder.append(";");

		return super.executarQuery(builder.toString());
	}
}
