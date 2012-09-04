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
public class Rel_GradePeriodoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public Rel_GradePeriodoDAO() {
		nomeDaTabela = "grade_periodo";
		primaryKey = new String[] { "id_grade_periodo" };
		campos = new String[] { "id_grade", "id_periodo" };
	}

	/**
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public int adicionar(Integer id_grade, Integer id_periodo) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grade);
		campoValor.put(campos[1], id_periodo);

		return super._adicionar(campoValor);
	}

	/**
	 * 
	 * @param id_grade_periodo
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public int editar(Integer id_grade_periodo, Integer id_grade,
			Integer id_periodo) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_grade_periodo);
		campoValor.put(campos[0], id_grade);
		campoValor.put(campos[1], id_periodo);

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
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_grade,
			Integer id_periodo) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grade);
		campoValor.put(campos[1], id_periodo);

		return super._listarPor(campoValor);
	}
	
	// TODO: Desenvolver Query de Relacionamentos
}
