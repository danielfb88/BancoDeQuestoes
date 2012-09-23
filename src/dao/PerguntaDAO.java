package dao;

import java.util.List;

import util.AbstractDAO;

public class PerguntaDAO extends AbstractDAO {
	public Integer id_pergunta;
	public Integer id_usuario;
	public String descricao;
	public Character tipo_pergunta;
	public Character nivel_pergunta;
	public String enunciado;
	public String comentario;

	@Override
	protected void config() {
		nomeDaTabela = "pergunta";
		primaryKey = new String[] { "id_pergunta" };
	}

	/**
	 * Lista Perguntas pelo Id da disciplina
	 * 
	 * @param id_disciplina
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PerguntaDAO> listarPerguntasPorDisciplina(Integer id_disciplina) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT p.id_pergunta, p.id_usuario, ");
		builder.append("p.descricao, p.tipo_pergunta, p.nivel_pergunta, ");
		builder.append("p.enunciado, p.comentario ");

		builder.append("FROM pergunta p ");

		builder.append("JOIN disciplina_assunto__pergunta da_p ");
		builder.append("ON (da_p.id_pergunta = p.id_pergunta) ");

		builder.append("JOIN disciplina_assunto da ");
		builder.append("ON (da.id_disciplina_assunto = da_p.id_disciplina_assunto)");

		builder.append(" WHERE ");
		builder.append("id_disciplina = " + id_disciplina);
		builder.append(";");

		return ((List<PerguntaDAO>) this.executarQuery(builder.toString()));
	}

	/**
	 * Lista perguntas pelo ID do assunto.
	 * 
	 * @param id_assunto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PerguntaDAO> listarPerguntasPorAssunto(Integer id_assunto) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT ");
		builder.append("p.id_pergunta, p.id_usuario, p.descricao, ");
		builder.append("p.tipo_pergunta, p.nivel_pergunta, ");
		builder.append("p.enunciado, p.comentario ");

		builder.append("FROM pergunta p ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (p.id_pergunta = ap.id_pergunta) ");

		builder.append("JOIN assunto a ");
		builder.append("ON (ap.id_assunto = a.id_assunto) ");

		builder.append("WHERE a.id_assunto = " + id_assunto + " ");
		builder.append(";");

		return ((List<PerguntaDAO>) super.executarQuery(builder.toString()));
	}
}
