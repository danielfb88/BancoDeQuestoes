package dao;

import java.util.List;

import util.AbstractDAO;

public class AssuntoDAO extends AbstractDAO {
	public Integer id_assunto;
	public String descricao;

	@Override
	protected void config() {
		nomeDaTabela = "assunto";
		primaryKey = new String[] { "id_assunto" };
	}

	/**
	 * Lista Assuntos pelo ID da pergunta.
	 * 
	 * @param id_pergunta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AssuntoDAO> listarAssuntosPorPergunta(Integer id_pergunta) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT a.id_assunto, a.descricao ");
		builder.append("FROM assunto a ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (a.id_assunto = ap.id_assunto) ");

		builder.append("JOIN pergunta p ");
		builder.append("ON (ap.id_pergunta = p.id_pergunta) ");

		builder.append("WHERE p.id_pergunta = " + id_pergunta + " ");
		builder.append(";");

		return ((List<AssuntoDAO>) super.executarQuery(builder.toString()));
	}

	/**
	 * Lista Assuntos pelo Id da Disciplina.
	 * 
	 * @param id_disciplina
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AssuntoDAO> listarAssuntosPorDisciplina(Integer id_disciplina) {
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

		return ((List<AssuntoDAO>) super.executarQuery(builder.toString()));
	}
}
