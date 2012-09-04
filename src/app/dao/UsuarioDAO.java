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
public class UsuarioDAO extends AbstractDAO {
	private Map<Object, Object> campoValor;

	public UsuarioDAO() {
		nomeDaTabela = "usuario";
		primaryKey = new String[] { "id_usuario" };
		campos = new String[] { "id_grupo", "nome", "login", "senha" };
	}

	/**
	 * Adicionar
	 * 
	 * @param id_grupo
	 * @param nome
	 * @param login
	 * @param senha
	 * @return
	 */
	public int adicionar(Integer id_grupo, String nome, String login,
			String senha) {
		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grupo);
		campoValor.put(campos[1], nome);
		campoValor.put(campos[2], login);
		campoValor.put(campos[3], login);
		campoValor.put(campos[4], senha);

		return super._adicionar(campoValor);
	}

	/**
	 * Editar
	 * 
	 * @param id_usuario
	 * @param id_grupo
	 * @param nome
	 * @param login
	 * @param senha
	 * @return
	 */
	public int editar(Integer id_usuario, Integer id_grupo, String nome,
			String login, String senha) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(primaryKey[0], id_usuario);
		campoValor.put(campos[0], id_grupo);
		campoValor.put(campos[1], nome);
		campoValor.put(campos[2], login);
		campoValor.put(campos[3], senha);

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
	 * @param id_grupo
	 * @param nome
	 * @param login
	 * @param senha
	 * @return
	 */
	public List<Map<String, Object>> listarPor(Integer id_grupo, String nome,
			String login, String senha) {

		campoValor = new HashMap<Object, Object>();

		campoValor.put(campos[0], id_grupo);
		campoValor.put(campos[1], nome);
		campoValor.put(campos[2], login);
		campoValor.put(campos[3], senha);

		return super._listarPor(campoValor);
	}
}
