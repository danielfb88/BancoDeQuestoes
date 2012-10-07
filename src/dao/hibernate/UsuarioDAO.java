package dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.usuario.Usuario;

import util.hibernate.HibernateAbstractDAO;

public class UsuarioDAO extends HibernateAbstractDAO<Usuario> {

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Usuario> listar(Integer id_usuario, Integer id_grupo, String nome, String login, String senha) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", id_usuario);
		map.put("id_grupo", id_grupo);
		map.put("nome", nome);
		map.put("login", login);
		map.put("senha", senha);

		return super.listar(Usuario.class, map);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Usuario> listarTodos() {
		return super.listarTodos(Usuario.class);
	}

}
