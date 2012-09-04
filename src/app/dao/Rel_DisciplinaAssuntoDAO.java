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
public class Rel_DisciplinaAssuntoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private AssuntoDAO assuntoDAO = new AssuntoDAO();

	public Rel_DisciplinaAssuntoDAO() {
		nomeDaTabela = "disciplina_assunto";
		primaryKey = new String[] { "id_disciplina_assunto" };
		campos = new String[] { "id_disciplina", "id_assunto" };
	}

	/**
	 * 
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public int adicionar(Integer id_disciplina, Integer id_assunto) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplina);
		campoValor.put(campos[1], id_assunto);

		return super._adicionar(campoValor);
	}

	/**
	 * 
	 * @param id_disciplina_assunto
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public int editar(Integer id_disciplina_assunto, Integer id_disciplina,
			Integer id_assunto) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_disciplina_assunto);
		campoValor.put(campos[0], id_disciplina);
		campoValor.put(campos[1], id_assunto);

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
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_disciplina,
			Integer id_assunto) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplina);
		campoValor.put(campos[1], id_assunto);

		return super._listarPor(campoValor);
	}

	/**
	 * Lista Disciplinas por Assunto
	 * 
	 * @param id_assunto
	 * @return
	 */
	public List<Map<String, Object>> listarDisciplinasPorAssunto(
			Integer id_assunto) {

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT ");
		builder.append("d.id_disciplina, p.id_curso, ");
		builder.append("d.descricao, d.sigla ");

		builder.append("FROM disciplina d ");

		builder.append("JOIN disciplina_assunto da ");
		builder.append("ON (d.id_disciplina = da.id_disciplina) ");

		builder.append("JOIN assunto a ");
		builder.append("ON (da.id_assunto = a.id_assunto) ");

		builder.append("WHERE a.id_assunto = " + id_assunto + " ");
		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.disciplinaDAO.getAtributos());
	}

	/**
	 * Lista Assuntos por Disciplina
	 * 
	 * @param id_disciplina
	 * @return
	 */
	public List<Map<String, Object>> listarAssuntosPorDisciplina(
			Integer id_disciplina) {

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT ");
		builder.append("a.id_assunto, a.descricao ");

		builder.append("FROM assunto a ");

		builder.append("JOIN disciplina_assunto da ");
		builder.append("ON (a.id_assunto = da.id_assunto) ");

		builder.append("JOIN disciplina d ");
		builder.append("ON (da.id_disciplina = d.id_disciplina) ");

		builder.append("WHERE d.id_disciplina = " + id_disciplina + " ");
		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.assuntoDAO.getAtributos());
	}
}
