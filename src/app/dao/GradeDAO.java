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
public class GradeDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public GradeDAO() {
		nomeDaTabela = "grade";
		primaryKey = new String[] { "id_grade" };
		campos = new String[] { "id_curso", "id_anosemestre_inicial",
				"id_anosemestre_final", "descricao" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_curso
	 * @param id_anoSemestre_inicial
	 * @param id_anoSemestre_final
	 * @param descricao
	 * @return
	 */
	public int adicionar(Integer id_curso, Integer id_anoSemestre_inicial,
			Integer id_anoSemestre_final, String descricao) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], id_anoSemestre_inicial);
		campoValor.put(campos[2], id_anoSemestre_final);
		campoValor.put(campos[3], descricao);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_grade
	 * @param id_curso
	 * @param id_anoSemestre_inicial
	 * @param id_anoSemestre_final
	 * @param descricao
	 * @return
	 */
	public int editar(Integer id_grade, Integer id_curso,
			Integer id_anoSemestre_inicial, Integer id_anoSemestre_final,
			String descricao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_grade);
		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], id_anoSemestre_inicial);
		campoValor.put(campos[2], id_anoSemestre_final);
		campoValor.put(campos[3], descricao);

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
	 * @param id_grade
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_curso,
			Integer id_anoSemestre_inicial, Integer id_anoSemestre_final,
			String descricao) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_curso);
		campoValor.put(campos[1], id_anoSemestre_inicial);
		campoValor.put(campos[2], id_anoSemestre_final);
		campoValor.put(campos[3], descricao);

		return super._listarPor(campoValor);
	}
}
