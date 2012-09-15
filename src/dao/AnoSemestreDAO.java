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
public class AnoSemestreDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public AnoSemestreDAO() {
		nomeDaTabela = "anosemestre";
		primaryKey = new String[] { "id_anosemestre" };
		campos = new String[] { "ano", "semestre" };
	}

	/**
	 * Adicionar
	 * 
	 * @param ano
	 * @param semestre
	 * @return
	 */
	public int adicionar(Integer ano, Integer semestre) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], ano);
		campoValor.put(campos[1], semestre);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_anoSemestre
	 * @param ano
	 * @param semestre
	 * @return
	 */
	public int editar(Integer id_anoSemestre, Integer ano, Integer semestre) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_anoSemestre);
		campoValor.put(campos[0], ano);
		campoValor.put(campos[1], semestre);

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
	 * @param ano
	 * @param semestre
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer ano, Integer semestre) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], ano);
		campoValor.put(campos[1], semestre);

		// Recebendo os objetos
		return super._listarPor(campoValor);
	}
}
