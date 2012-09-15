package dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AbstractDAO;

/**
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class CoordenadorCursoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public CoordenadorCursoDAO() {
		nomeDaTabela = "coordenador_curso";
		primaryKey = new String[] { "id_coordenador_curso" };
		campos = new String[] { "id_usuario", "id_curso", "data_entrada",
				"data_saida" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_usuario
	 * @param id_curso
	 * @param data_entrada
	 * @param data_saida
	 * @return
	 */
	public int adicionar(Integer id_usuario, Integer id_curso,
			Date data_entrada, Date data_saida) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], id_curso);
		campoValor.put(campos[2], data_entrada);
		campoValor.put(campos[3], data_saida);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_coordenadorCurso
	 * @param id_usuario
	 * @param id_curso
	 * @param data_entrada
	 * @param data_saida
	 * @return
	 */
	public int editar(Integer id_coordenadorCurso, Integer id_usuario,
			Integer id_curso, Date data_entrada, Date data_saida) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_coordenadorCurso);
		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], id_curso);
		campoValor.put(campos[2], data_entrada);
		campoValor.put(campos[3], data_saida);

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
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_usuario,
			Integer id_curso, Date data_entrada, Date data_saida) {

		campoValor = new HashMap<Object, Object>();
		
		campoValor.put(campos[0], id_usuario);
		campoValor.put(campos[1], id_curso);
		campoValor.put(campos[2], data_entrada);
		campoValor.put(campos[3], data_saida);

		// Recebendo os objetos
		return super._listarPor(campoValor);
	}
}
