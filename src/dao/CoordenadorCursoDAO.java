package dao;

import util.AbstractDAO;

public class CoordenadorCursoDAO extends AbstractDAO {
	public Integer id_coordenador_curso;
	public Integer id_usuario;
	public Integer id_curso;
	public Integer data_entrada;
	public Integer data_saida;

	@Override
	protected void config() {
		nomeDaTabela = "coordenador_curso";
		primaryKey = new String[] { "id_coordenador_curso" };
	}
}
