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
public class Rel_AssuntoPerguntaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;
	private AssuntoDAO assuntoDAO = new AssuntoDAO();
	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public Rel_AssuntoPerguntaDAO() {
		nomeDaTabela = "assunto_pergunta";
		primaryKey = new String[] { "id_assunto_pergunta" };
		campos = new String[] { "id_assunto", "id_pergunta" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_assunto
	 * @param id_pergunta
	 * @return
	 */
	public int adicionar(Integer id_assunto, Integer id_pergunta) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_assunto);
		campoValor.put(campos[1], id_pergunta);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_assunto_pergunta
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public int editar(Integer id_assunto_pergunta, Integer id_assunto,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_assunto_pergunta);
		campoValor.put(campos[0], id_assunto);
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
	 * @param id_disciplina
	 * @param id_assunto
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_assunto,
			Integer id_pergunta) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_assunto);
		campoValor.put(campos[1], id_pergunta);

		return super._listarPor(campoValor);
	}

	/**
	 * Lista Perguntas por Assunto
	 * 
	 * @param id_assunto
	 * @return
	 */
	public List<Map<String, Object>> listarPerguntasPorAssunto(
			Integer id_assunto) {

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

		return super.executarQuery(builder.toString(),
				this.perguntaDAO.getAtributos());
	}

	/**
	 * Lista Assuntos por Pergunta
	 * 
	 * @param id_pergunta
	 * @return
	 */
	public List<Map<String, Object>> listarAssuntosPorPergunta(
			Integer id_pergunta) {

		StringBuilder builder = new StringBuilder();

		builder.append("SELECT a.id_assunto, a.descricao ");
		builder.append("FROM assunto a ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (a.id_assunto = ap.id_assunto) ");

		builder.append("JOIN pergunta p ");
		builder.append("ON (ap.id_pergunta = p.id_pergunta) ");

		builder.append("WHERE p.id_pergunta = " + id_pergunta + " ");
		builder.append(";");

		return super.executarQuery(builder.toString(),
				this.assuntoDAO.getAtributos());
	}
}
