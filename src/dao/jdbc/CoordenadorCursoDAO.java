package dao.jdbc;

import java.sql.Date;

import util.AbstractDAO;

public class CoordenadorCursoDAO extends AbstractDAO {
	public Integer id_coordenador_curso;
	public Integer id_usuario;
	public Integer id_curso;
	public Date data_entrada;
	public Date data_saida;

	@Override
	protected void config() {
		nomeDaTabela = "coordenador_curso";
		primaryKey = new String[] { "id_coordenador_curso" };
		is_autoIncrement = true;
	}
}
