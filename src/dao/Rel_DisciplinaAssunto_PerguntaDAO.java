package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 29-08-2012
 * 
 */
public class Rel_DisciplinaAssunto_PerguntaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Rel_DisciplinaAssunto_PerguntaDAO() {
		nomeDaTabela = "disciplina_assunto__pergunta";
		primaryKey = new String[] { "id_disciplina_assunto__pergunta" };
		campos = new String[] { "id_disciplina_assunto", "id_pergunta" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public int adicionar(Integer id_disciplinaAssunto, Integer id_pergunta) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_disciplinaAssunto_pergunta
	 * @param id_disciplinaAssunto
	 * @param id_pergunta
	 * @return
	 */
	public int editar(Integer id_disciplinaAssunto_pergunta,
			Integer id_disciplinaAssunto, Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_disciplinaAssunto_pergunta);
		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

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
	public List<Map<String, Object>> listarPor(Integer id_disciplinaAssunto,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_disciplinaAssunto);
		campoValor.put(campos[1], id_pergunta);

		return super._listarPor(campoValor);
	}

	/**
	 * Lista Pergunta por Disciplina
	 * 
	 * @param id_disciplina
	 * @return
	 */
	public List<Map<String, Object>> listarPerguntasPorDisciplina(
			Integer id_disciplina) {

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

		return this.executarQuery(builder.toString(),
				disciplinaDAO.getAtributos());
	}
}
