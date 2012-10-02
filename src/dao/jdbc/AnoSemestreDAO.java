package dao.jdbc;

import util.AbstractDAO;

public class AnoSemestreDAO extends AbstractDAO {
	public Integer id_anosemestre;
	public Integer ano;
	public Integer semestre;

	@Override
	protected void config() {
		nomeDaTabela = "anosemestre";
		primaryKey = new String[] { "id_anosemestre" };
		is_autoIncrement = true;
	}
}
