package dao;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class PerguntaDAO extends AbstractDAO {
	public Integer id_pergunta;
	public Integer id_usuario;
	public String descricao;
	public Character tipo_pergunta;
	public Character nivel_pergunta;
	public String enunciado;
	public String comentario;

	@Override
	protected void config() {
		nomeDaTabela = "pergunta";
		primaryKey = new String[] { "id_pergunta" };
	}
}
