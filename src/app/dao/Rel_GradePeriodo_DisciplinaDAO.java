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
public class Rel_GradePeriodo_DisciplinaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public Rel_GradePeriodo_DisciplinaDAO() {
		nomeDaTabela = "grade_periodo__disciplina";
		primaryKey = new String[] { "id_grade_periodo__disciplina" };
		campos = new String[] { "id_grade_periodo", "id_disciplina" };
	}

	/**
	 * 
	 * @param id_gradePeriodo
	 * @param id_disciplina
	 * @return
	 */
	public int adicionar(Integer id_gradePeriodo, Integer id_disciplina) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_gradePeriodo);
		campoValor.put(campos[1], id_disciplina);

		return super._adicionar(campoValor);
	}

	/**
	 * 
	 * @param id_gradePeriodo
	 * @param id_disciplina
	 * @return
	 */
	public int editar(Integer id_gradePeriodo_disciplina,
			Integer id_gradePeriodo, Integer id_disciplina) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_gradePeriodo_disciplina);
		campoValor.put(campos[0], id_gradePeriodo);
		campoValor.put(campos[1], id_disciplina);

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
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_gradePeriodo,
			Integer id_disciplina) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_gradePeriodo);
		campoValor.put(campos[1], id_disciplina);

		return super._listarPor(campoValor);
	}
}
