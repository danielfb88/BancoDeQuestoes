package dao.hibernate;

import java.util.List;

import util.hibernate.HibernateAbstractDAO;
import dominio.usuario.Usuario;

public class UsuarioDAO extends HibernateAbstractDAO<Usuario> {

	/**
	 * Listar utilizando os valores contidos no objeto como filtro.
	 * 
	 * @param grupo
	 * @return
	 */
	public List<Usuario> listar(Usuario grupo) {
		return super.listar(Usuario.class, grupo);
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
