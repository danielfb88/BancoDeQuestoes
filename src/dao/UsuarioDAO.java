package dao;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class UsuarioDAO extends AbstractDAO {
	public Integer id_usuario;
	public Integer id_grupo;
	public String nome;
	public String login;
	public String senha;

	@Override
	protected void config() {
		nomeDaTabela = "usuario";
		primaryKey = new String[] { "id_usuario" };
	}
}
