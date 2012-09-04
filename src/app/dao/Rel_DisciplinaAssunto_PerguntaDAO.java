package app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 29-08-2012
 * 
 */
public class Rel_DisciplinaAssunto_PerguntaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;
	private Rel_DisciplinaAssuntoDAO rel_disciplinaAssunto = new Rel_DisciplinaAssuntoDAO();
	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public Rel_DisciplinaAssunto_PerguntaDAO() {
		nomeDaTabela = "disciplina_assunto__pergunta";
		primaryKey = new String[] { "id_disciplina_assunto__pergunta" };
		campos = new String[] { "id_disciplina_assunto", "id_pergunta" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public int adicionar(Integer id_disciplinaAssunto, Integer id_pergunta) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_disciplinaAssunto_pergunta
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public int editar(Integer id_disciplinaAssunto_pergunta,
			Integer id_disciplinaAssunto, Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_disciplinaAssunto_pergunta);
		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

		return super._editar(campoValor);
	}

	/**
	 * Excluir
	 * 
	 * @param id
	 * @return
	 */
	public int excluir(Integer id) {
		return super._excluir(id);
	}

	/**
	 * Buscar por ID
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> buscarPorId(Integer id) {
		return super._buscarPorId(id);
	}

	/**
	 * Listar Por
	 * 
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_disciplinaAssunto,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

		return super._listarPor(campoValor);
	}

	/**
	 * Lista Disciplina por Pergunta
	 * 
	 * @param id_pergunta
	 * @return
	 */
	public List<Map<String, Object>> listarDisciplinasPorPergunta(
			Integer id_pergunta) {

		StringBuilder builder = new StringBuilder();

		// TODO: DESENVOLVER QUERY

		return null;
	}

	/**
	 * Lista Pergunta por Disciplina
	 * 
	 * @param id_disciplina
	 * @return
	 */
	public List<Map<String, Object>> listarPerguntasPorDisciplina(
			Integer id_disciplina) {

		StringBuilder builder = new StringBuilder();

		// TODO: DESENVOLVER QUERY

		return null;
	}
}
