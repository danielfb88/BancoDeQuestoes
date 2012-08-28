package app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.util.AbstractDAO;

/**
 * TODO: Diminuindo o acoplamento. Estas classes DAO deverão apenas retornar
 * HashMap
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
public class CursoDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public CursoDAO() {
		nomeDaTabela = "curso";
		primaryKey = new String[] { "id_curso" };
		campos = new String[] { "descricao", "sigla", "tipo_graduacao" };
	}

	/**
	 * Adicionar
	 * 
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 * @return
	 */
	public int adicionar(String descricao, String sigla, String tipo_graduacao) {
		campoValor = new HashMap<Object, Object>(3);

		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], sigla);
		campoValor.put(campos[2], tipo_graduacao);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 * @return
	 */
	public int editar(Integer id_curso, String descricao, String sigla,
			String tipo_graduacao) {

		campoValor = new HashMap<Object, Object>(4);

		campoValor.put(primaryKey[0], id_curso);
		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], sigla);
		campoValor.put(campos[2], tipo_graduacao);

		return super._editar(campoValor);
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
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 * @return
	 */
	public List<Map<String, Object>> listarPor(String descricao, String sigla,
			String tipo_graduacao) {

		campoValor = new HashMap<Object, Object>(3);
		campoValor.put(campos[0], descricao);
		campoValor.put(campos[1], sigla);
		campoValor.put(campos[2], tipo_graduacao);

		// Recebendo os objetos
		return super._listarPor(campoValor);
	}

	/*
	 * TODO: Desenvolver outros motodos específicos de relacionamentos
	 */
}
