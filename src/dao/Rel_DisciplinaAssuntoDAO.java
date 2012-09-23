package dao;

import java.util.List;

import util.AbstractDAO;

public class Rel_DisciplinaAssuntoDAO extends AbstractDAO {
	public Integer id_disciplina_assunto;
	public Integer id_disciplina;
	public Integer id_assunto;

	@Override
	protected void config() {
		nomeDaTabela = "disciplina_assunto";
		primaryKey = new String[] { "id_disciplina_assunto" };
	}

	/**
	 * Lista Disciplinas pelo Id do Assunto.
	 * 
	 * @param id_assunto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarDisciplinasPorAssunto(Integer id_assunto) {
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

		return super.executarQuery(builder.toString());
	}

	/**
	 * Lista Assuntos pelo Id da Disciplina.
	 * 
	 * @param id_disciplina
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarAssuntosPorDisciplina(Integer id_disciplina) {
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

		return super.executarQuery(builder.toString());
	}

}
