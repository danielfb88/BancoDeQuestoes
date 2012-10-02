package dao.jdbc;

import java.util.List;

import util.AbstractDAO;

public class PeriodoDAO extends AbstractDAO {
	public Integer id_periodo;
	public String descricao;
	public Integer numero;

	@Override
	protected void config() {
		nomeDaTabela = "periodo";
		primaryKey = new String[] { "id_periodo" };
		is_autoIncrement = true;
	}

	/**
	 * Lista Periodos pelo Id da Grade
	 * 
	 * @param id_grade
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PeriodoDAO> listarPeriodosPorGrade(Integer id_grade) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT p.id_periodo, p.descricao, p.numero ");
		builder.append("FROM periodo p ");

		builder.append("JOIN grade_periodo gp ");
		builder.append("ON (gp.id_periodo = p.id_periodo) ");

		builder.append("JOIN grade g ");
		builder.append("ON (gp.id_grade = g.id_grade) ");

		builder.append("WHERE g.id_grade = " + id_grade + " ");
		builder.append(";");

		return ((List<PeriodoDAO>) super.executarQuery(builder.toString()));
	}
}
