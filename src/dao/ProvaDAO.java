package dao;

import java.sql.Date;

import util.AbstractDAO;

public class ProvaDAO extends AbstractDAO {
	public Integer id_prova;
	public Integer id_grade_periodo;
	public Integer id_anosemestre;
	public String descricao;
	public Date data_prova;
	
	@Override
	protected void config() {
		nomeDaTabela = "prova";
		primaryKey = new String[] { "id_prova" };
	}
}
