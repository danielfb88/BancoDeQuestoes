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
public class RespostaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public RespostaDAO() {
		nomeDaTabela = "resposta";
		primaryKey = new String[] { "id_resposta" };
		campos = new String[] { "id_pergunta", "descricao", "correta",
				"observacao" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_pergunta
	 * @param descricao
	 * @param correta
	 * @param observacao
	 * @return
	 */
	public int adicionar(Integer id_pergunta, String descricao,
			Integer correta, String observacao) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_pergunta);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], correta);
		campoValor.put(campos[3], observacao);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_resposta
	 * @param id_pergunta
	 * @param descricao
	 * @param correta
	 * @param observacao
	 * @return
	 */
	public int editar(Integer id_resposta, Integer id_pergunta,
			String descricao, Integer correta, String observacao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_resposta);
		campoValor.put(campos[0], id_pergunta);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], correta);
		campoValor.put(campos[3], observacao);

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
	 * @param id_resposta
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_pergunta,
			String descricao, Integer correta, String observacao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_pergunta);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], correta);
		campoValor.put(campos[3], observacao);

		return super._listarPor(campoValor);
	}
}
