package dao;

import util.AbstractDAO;

public class GradeDAO extends AbstractDAO {
	public Integer id_grade;
	public Integer id_curso;
	public Integer id_anosemestre_inicial;
	public Integer id_anosemestre_final;
	public Integer descricao;

	@Override
	protected void config() {
		nomeDaTabela = "grade";
		primaryKey = new String[] { "id_grade" };
	}
}
