package dao;

import java.util.List;
import util.AbstractDAO;

public class Rel_AssuntoPerguntaDAO extends AbstractDAO {
	public Integer id_assunto_pergunta;
	public Integer id_assunto;
	public Integer id_pergunta;

	@Override
	protected void config() {
		nomeDaTabela = "assunto_pergunta";
		primaryKey = new String[] { "id_assunto_pergunta" };
	}

	/**
	 * Lista perguntas pelo ID do assunto.
	 * 
	 * @param id_assunto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarPerguntasPorAssunto(Integer id_assunto) {
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

		return super.executarQuery(builder.toString());
	}

	/**
	 * Lista Assuntos pelo ID da pergunta.
	 * 
	 * @param id_pergunta
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listarAssuntosPorPergunta(Integer id_pergunta) {
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT a.id_assunto, a.descricao ");
		builder.append("FROM assunto a ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (a.id_assunto = ap.id_assunto) ");

		builder.append("JOIN pergunta p ");
		builder.append("ON (ap.id_pergunta = p.id_pergunta) ");

		builder.append("WHERE p.id_pergunta = " + id_pergunta + " ");
		builder.append(";");

		return super.executarQuery(builder.toString());
	}
}