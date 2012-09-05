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
	 * Query de Listar disciplina por id_grade
	 * 
	 * @param id_grade
	 * @return
	 */
	private StringBuilder queryListarDisciplinasPorGrade(int id_grade) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT d.id_disciplina, d.id_curso ");
		builder.append("d.descricao, d.sigla ");

		builder.append("FROM disciplina d ");

		builder.append("JOIN grade_periodo__disciplina gp_d ");
		builder.append("ON (gp_d.id_disciplina = d.id_disciplina) ");

		builder.append("JOIN grade_periodo gp ");
		builder.append("ON (gp.id_grade_periodo = gp_d.id_grade_periodo) ");

		builder.append("WHERE gp.id_grade = " + id_grade + " ");

		return builder;
	}

	/**
	 * Lista Disciplinas por Grade
	 * 
	 * @param id_grade
	 * @return
	 */
	public List<Map<String, Object>> listarDisciplinasPorGrade(int id_grade) {

		StringBuilder builder = this.queryListarDisciplinasPorGrade(id_grade);
		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.disciplinaDAO.getAtributos());
	}

	/**
	 * Lista Disciplinas por Grade e Periodo
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	public List<Map<String, Object>> listarDisciplinasPorGradePeriodo(
			int id_grade, int id_periodo) {

		StringBuilder builder = this.queryListarDisciplinasPorGrade(id_grade);
		builder.append("AND gp.id_periodo = " + id_periodo + " ");
		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.disciplinaDAO.getAtributos());
	}

	/**
	 * Remover Disciplina
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @param id_disciplina
	 * @return
	 */
	public boolean removerDisciplina(int id_grade, int id_periodo,
			int id_disciplina) {

		StringBuilder builder = new StringBuilder();

		builder.append("DELETE FROM grade_periodo__disciplina ");
		builder.append("WHERE id_grade_periodo = ");

		// subquery
		builder.append("(");
		builder.append("SELECT id_grade_periodo ");
		builder.append("FROM grade_periodo ");
		builder.append("WHERE ");
		builder.append("id_grade = " + id_grade);
		builder.append(" AND ");
		builder.append("id_periodo = " + id_periodo);
		builder.append(")");

		// continuando query principal
		builder.append(" AND ");
		builder.append("id_disciplina = " + id_disciplina);
		builder.append(";");

		return super.executarUpdate(builder.toString()) > 0;
	}
}
