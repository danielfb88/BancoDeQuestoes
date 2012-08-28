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
		campoValor.put(campos[1], tipo);

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
	 * 
	 * @param id_grupo
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_curso,
			Integer id_anoSemestre_inicial, Integer id_anoSemestre_final,
			String descricao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], id_anoSemestre_inicial);
		campoValor.put(campos[2], id_anoSemestre_final);
		campoValor.put(campos[3], descricao);

		return super._listarPor(campoValor);
	}
}
