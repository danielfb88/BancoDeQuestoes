package app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class DisciplinaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public DisciplinaDAO() {
		nomeDaTabela = "disciplina";
		primaryKey = new String[] { "id_disciplina" };
		campos = new String[] { "id_curso", "descricao", "sigla" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public int adicionar(Integer id_curso, String descricao, String sigla) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], sigla);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_disciplina
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public int editar(Integer id_disciplina, Integer id_curso,
			String descricao, String sigla) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_disciplina);
		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], sigla);

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
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_curso,
			String descricao, String sigla) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], descricao);
		campoValor.put(campos[2], sigla);

		return super._listarPor(campoValor);
	}
}
