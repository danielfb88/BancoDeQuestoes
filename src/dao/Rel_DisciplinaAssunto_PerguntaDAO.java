package dao;

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
}