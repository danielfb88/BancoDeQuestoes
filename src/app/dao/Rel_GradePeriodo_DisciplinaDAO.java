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
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Rel_GradePeriodo_DisciplinaDAO() {
		nomeDaTabela = "grade_periodo__disciplina";
		primaryKey = new String[] { "id_grade_periodo__disciplina" };
		campos = new String[] { "id_grade_periodo", "id_disciplina" };
	}

	/**
	 * Adicionar
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
	 * Editar
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
	 * Listar Por
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

	/**
	 * Lista Disciplinas por GradePeriodo
	 * 
	 * @param id_grade_periodo
	 * @return
	 */
	public List<Map<String, Object>> listarDisciplinasPorGradePeriodo(
			Integer id_grade, Integer id_periodo) {

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT d.id_disciplina, d.id_curso ");
		builder.append("d.descricao, d.sigla ");

		builder.append("FROM disciplina d ");

		builder.append("JOIN grade_periodo__disciplina gp_d ");
		builder.append("ON (gp_d.id_disciplina = d.id_disciplina) ");

		builder.append("JOIN grade_periodo gp ");
		builder.append("ON (gp.id_grade_periodo = gp_d.id_grade_periodo) ");

		builder.append("WHERE gp.id_grade = " + id_grade + " ");
		builder.append("AND gp.id_periodo = " + id_periodo + " ");

		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.disciplinaDAO.getAtributos());
	}
}
