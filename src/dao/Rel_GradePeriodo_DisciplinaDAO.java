package dao;

import java.util.List;

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

	/**
	 * Query de Listar disciplina pelo Id da Grade
	 * 
	 * @param id_grade
	 * @return
	 */
	private StringBuilder queryListarDisciplinasPorGrade(int id_grade) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT d.id_disciplina, d.id_curso ");
		builder.append("d.descricao, d.sigla ");

		builder.append("FROM disciplina d ");

		builder.append("JOIN grade_periodo__disciplina gp_d ");
		builder.append("ON (gp_d.id_disciplina = d.id_disciplina) ");

		builder.append("JOIN grade_periodo gp ");
		builder.append("ON (gp.id_grade_periodo = gp_d.id_grade_periodo) ");

		builder.append("WHERE gp.id_grade = " + id_grade + " ");

		return builder;
	}

	/**
	 * Lista Disciplinas pelo Id da Grade
	 * 
	 * @param id_grade
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarDisciplinasPorGrade(int id_grade) {

		StringBuilder builder = this.queryListarDisciplinasPorGrade(id_grade);
		builder.append(";");

		return super.executarQuery(builder.toString());
	}

	/**
	 * Lista Disciplinas pelo Id da Grade e Id do Periodo
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarDisciplinasPorGradePeriodo(int id_grade, int id_periodo) {

		StringBuilder builder = this.queryListarDisciplinasPorGrade(id_grade);
		builder.append("AND gp.id_periodo = " + id_periodo + " ");
		builder.append(";");

		return super.executarQuery(builder.toString());
	}

	/**
	 * Remove a Disciplina de uma Grade e Periodo específico.
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @param id_disciplina
	 * @return
	 */
	public boolean removerDisciplina(int id_grade, int id_periodo, int id_disciplina) {
		StringBuilder builder = new StringBuilder();

		builder.append("DELETE FROM grade_periodo__disciplina ");
		builder.append("WHERE id_grade_periodo = ");

		// subquery
		builder.append("(");
		builder.append("SELECT id_grade_periodo ");
		builder.append("FROM grade_periodo ");
		builder.append("WHERE ");
		builder.append("id_grade = " + id_grade);
		builder.append(" AND ");
		builder.append("id_periodo = " + id_periodo);
		builder.append(")");

		// continuando query principal
		builder.append(" AND ");
		builder.append("id_disciplina = " + id_disciplina);
		builder.append(";");

		return super.executarUpdate(builder.toString()) > 0;
	}
}
