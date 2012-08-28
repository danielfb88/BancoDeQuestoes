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
public class AssuntoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public AssuntoDAO() {
		nomeDaTabela = "assunto";
		primaryKey = new String[] { "id_assunto" };
		campos = new String[] { "descricao" };
	}

	/**
	 * 
	 * @param descricao
	 * @return
	 */
	public int adicionar(String descricao) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);

		return super._adicionar(campoValor);
	}
	
	/**
	 * 
	 * @param id_assunto
	 * @param descricao
	 * @return
	 */
	public int editar(Integer id_assunto, String descricao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_assunto);
		campoValor.put(campos[0], descricao);

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
	 * @param descricao
	 * @return
	 */
	public List<Map<String, Object>> listarPor(String descricao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], descricao);

		return super._listarPor(campoValor);
	}
}
