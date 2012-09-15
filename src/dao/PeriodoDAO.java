package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class PeriodoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public PeriodoDAO() {
		nomeDaTabela = "periodo";
		primaryKey = new String[] { "id_periodo" };
		campos = new String[] { "descricao", "numero" };
	}

	/**
	 * Adicionar
	 * 
	 * @param descricao
	 * @param numero
	 * @return
	 */
	public int adicionar(String descricao, Integer numero) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], numero);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_periodo
	 * @param descricao
	 * @param numero
	 * @return
	 */
	public int editar(Integer id_periodo, String descricao, Integer numero) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_periodo);
		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], numero);

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
	 * @param id_periodo
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(String descricao, Integer numero) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], numero);

		return super._listarPor(campoValor);
	}
}
