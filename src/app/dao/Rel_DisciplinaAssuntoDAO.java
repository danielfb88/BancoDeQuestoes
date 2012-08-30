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
}