package app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class PerguntaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public PerguntaDAO() {
		nomeDaTabela = "pergunta";
		primaryKey = new String[] { "id_pergunta" };
		campos = new String[] { "id_usuario", "descricao", "tipo_pergunta",
				"nivel_pergunta", "enunciado", "comentario" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_usuario
	 * @param descricao
	 * @param tipo_pergunta
	 * @param nivel_pergunta
	 * @param enunciado
	 * @param comentario
	 * @return
	 */
	public int adicionar(Integer id_usuario, String descricao,
			String tipo_pergunta, String nivel_pergunta, String enunciado,
			String comentario) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], tipo_pergunta.toUpperCase());
		campoValor.put(campos[3], nivel_pergunta.toUpperCase());
		campoValor.put(campos[4], enunciado);
		campoValor.put(campos[5], comentario);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_pergunta
	 * @param id_usuario
	 * @param descricao
	 * @param tipo_pergunta
	 * @param nivel_pergunta
	 * @param enunciado
	 * @param comentario
	 * @return
	 */
	public int editar(Integer id_pergunta, Integer id_usuario,
			String descricao, String tipo_pergunta, String nivel_pergunta,
			String enunciado, String comentario) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_pergunta);
		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], tipo_pergunta);
		campoValor.put(campos[3], nivel_pergunta);
		campoValor.put(campos[4], enunciado);
		campoValor.put(campos[5], comentario);

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
	 * @param id_usuario
	 * @param descricao
	 * @param tipo_pergunta
	 * @param nivel_pergunta
	 * @param enunciado
	 * @param comentario
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_usuario,
			String descricao, String tipo_pergunta, String nivel_pergunta,
			String enunciado, String comentario) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], tipo_pergunta);
		campoValor.put(campos[3], nivel_pergunta);
		campoValor.put(campos[4], enunciado);
		campoValor.put(campos[5], comentario);

		return super._listarPor(campoValor);
	}
}
