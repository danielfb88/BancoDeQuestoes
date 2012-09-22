package dao;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<PerguntaDAO> listarTodoasPerguntas() {
		String query = "SELECT * FROM pergunta;";
		return executarQuery(query);
	}

	public int deletarRegistroDeID(int id) {
		return executarUpdate("DELETE FROM pergunta WHERE id_pergunta = " + id + ";");
	}
	
	@SuppressWarnings("unchecked")
	public List<PerguntaDAO> listarPor() {
		String query = "SELECT * FROM pergunta WHERE id_usuario = ? AND descricao LIKE ?";
		List<Object> listValores = new ArrayList<Object>();
		listValores.add(5);
		listValores.add("Et");
		return executarQueryPreparada(query, listValores);
		
	}
}
