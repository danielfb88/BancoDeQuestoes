package dao;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class AnoSemestreDAO extends AbstractDAO {
	public Integer id_anosemestre;
	public Integer ano;
	public Integer semestre;

	@Override
	protected void config() {
		nomeDaTabela = "anosemestre";
		primaryKey = new String[] { "id_anosemestre" };
	}
}
