package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;
import app.util.conexao.DAOUtil;

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
		Map<String, Object> campoValorRetorno = new HashMap<String, Object>();
		List<Map<String, Object>> camposValoresRetornados = new ArrayList<Map<String, Object>>();

		builder.append("SELECT * ");
		builder.append("FROM pergunta p ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (p.id_pergunta = ap.id_pergunta) ");

		builder.append("JOIN assunto a ");
		builder.append("ON (ap.id_assunto = a.id_assunto) ");

		builder.append("WHERE id_assunto = ? ");
		builder.append(";");

		try {
			PreparedStatement ps = DAOUtil.getInstance().getPreparedStatement(
					builder.toString());

			ps.setInt(1, id_assunto);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				rs.close();
				ps.close();
				return null;
			}

			String[] perguntaPK = this.perguntaDAO.getPrimaryKey();
			String[] perguntaCampos = this.perguntaDAO.getCampos();

			// inserindo primary keys no hashMap
			super.preencherMap(campoValorRetorno, rs, perguntaPK);

			// inserindo Campos restates no hashMap
			super.preencherMap(campoValorRetorno, rs, perguntaCampos);

			// inserindo o hashMap no arrayList
			camposValoresRetornados.add(campoValorRetorno);

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return camposValoresRetornados;
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
		Map<String, Object> campoValorRetorno = new HashMap<String, Object>();
		List<Map<String, Object>> camposValoresRetornados = new ArrayList<Map<String, Object>>();

		builder.append("SELECT * ");
		builder.append("FROM assunto a ");

		builder.append("JOIN assunto_pergunta ap ");
		builder.append("ON (a.id_assunto = ap.id_assunto) ");

		builder.append("JOIN pergunta p ");
		builder.append("ON (ap.id_assunto = p.id_pergunta) ");

		builder.append("WHERE id_pergunta = ? ");
		builder.append(";");

		try {
			PreparedStatement ps = DAOUtil.getInstance().getPreparedStatement(
					builder.toString());

			ps.setInt(1, id_pergunta);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				rs.close();
				ps.close();
				return null;
			}

			String[] assuntoPK = this.assuntoDAO.getPrimaryKey();
			String[] assuntoCampos = this.assuntoDAO.getCampos();

			// inserindo primary keys no hashMap
			super.preencherMap(campoValorRetorno, rs, assuntoPK);

			// inserindo Campos restates no hashMap
			super.preencherMap(campoValorRetorno, rs, assuntoCampos);

			// inserindo o hashMap no arrayList
			camposValoresRetornados.add(campoValorRetorno);

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return camposValoresRetornados;
	}
}
