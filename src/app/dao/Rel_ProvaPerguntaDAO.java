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
public class Rel_ProvaPerguntaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;
	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public Rel_ProvaPerguntaDAO() {
		nomeDaTabela = "prova_pergunta";
		primaryKey = new String[] { "id_prova_pergunta" };
		campos = new String[] { "id_prova", "id_pergunta" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_prova
	 * @param id_pergunta
	 * @return
	 */
	public int adicionar(Integer id_prova, Integer id_pergunta) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_prova);
		campoValor.put(campos[1], id_pergunta);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_prova_pergunta
	 * @param id_prova
	 * @param id_pergunta
	 * @return
	 */
	public int editar(Integer id_prova_pergunta, Integer id_prova,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_prova_pergunta);
		campoValor.put(campos[0], id_prova);
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
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_prova,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_prova);
		campoValor.put(campos[1], id_pergunta);

		return super._listarPor(campoValor);
	}

	/**
	 * Lista Perguntas por Prova
	 * 
	 * @param id_prova
	 * @return
	 */
	public List<Map<String, Object>> listarPerguntasPorProva(Integer id_prova) {

		StringBuilder builder = new StringBuilder();

		// TODO: DESENVOLVER QUERY

		return super.executarQuery(builder.toString(),
				this.perguntaDAO.getAtributos());
	}
}
