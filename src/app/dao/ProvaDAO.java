package app.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class ProvaDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public ProvaDAO() {
		nomeDaTabela = "prova";
		primaryKey = new String[] { "id_prova" };
		campos = new String[] { "id_grade_periodo", "id_anosemestre",
				"descricao", "data_prova" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_grade_periodo
	 * @param id_anoSemestre
	 * @param descricao
	 * @param data_prova
	 * @return
	 */
	public int adicionar(Integer id_grade_periodo, Integer id_anoSemestre,
			String descricao, Date data_prova) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grade_periodo);
		campoValor.put(campos[1], id_anoSemestre);
		campoValor.put(campos[2], descricao);
		campoValor.put(campos[3], data_prova);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_prova
	 * @param id_grade_periodo
	 * @param id_anoSemestre
	 * @param descricao
	 * @param data_prova
	 * @return
	 */
	public int editar(Integer id_prova, Integer id_grade_periodo,
			Integer id_anoSemestre, String descricao, Date data_prova) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_prova);
		campoValor.put(campos[0], id_grade_periodo);
		campoValor.put(campos[1], id_anoSemestre);
		campoValor.put(campos[2], descricao);
		campoValor.put(campos[3], data_prova);

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
	 * @param id_grade_periodo
	 * @param id_anoSemestre
	 * @param descricao
	 * @param data_prova
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_grade_periodo,
			Integer id_anoSemestre, String descricao, Date data_prova) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grade_periodo);
		campoValor.put(campos[1], id_anoSemestre);
		campoValor.put(campos[2], descricao);
		campoValor.put(campos[3], data_prova);

		return super._listarPor(campoValor);
	}
}
