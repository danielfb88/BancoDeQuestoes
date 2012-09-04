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
public class GrupoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public GrupoDAO() {
		nomeDaTabela = "grupo";
		primaryKey = new String[] { "id_grupo" };
		campos = new String[] { "descricao", "tipo" };
	}

	/**
	 * Adicionar
	 * 
	 * @param descricao
	 * @param tipo
	 * @return
	 */
	public int adicionar(String descricao, String tipo) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], tipo);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_grupo
	 * @param descricao
	 * @param tipo
	 * @return
	 */
	public int editar(Integer id_grupo, String descricao, String tipo) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_grupo);
		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], tipo.toUpperCase());

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
	 * @param id_grupo
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(String descricao, String tipo) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], tipo);

		return super._listarPor(campoValor);
	}
}
