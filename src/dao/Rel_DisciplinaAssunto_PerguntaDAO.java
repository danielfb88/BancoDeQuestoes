package dao;

import java.util.List;

import util.AbstractDAO;

public class Rel_DisciplinaAssunto_PerguntaDAO extends AbstractDAO {
	public Integer id_disciplina_assunto__pergunta;
	public Integer id_disciplina_assunto;
	public Integer id_pergunta;

	@Override
	protected void config() {
		nomeDaTabela = "disciplina_assunto__pergunta";
		primaryKey = new String[] { "id_disciplina_assunto__pergunta" };
	}

	/**
	 * Lista Pergunta pelo Id da disciplina
	 * 
	 * @param id_disciplina
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarPerguntasPorDisciplina(Integer id_disciplina) {

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

		return this.executarQuery(builder.toString());
	}
}