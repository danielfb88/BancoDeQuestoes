package app.bo;

import java.util.List;

import app.dao.iterface.IUsuarioDAO;
import app.dto.Usuario;
import app.util.FactoryDAO;

public class UsuarioBO {
	private IUsuarioDAO usuarioDAO;

	public UsuarioBO() {
		this.usuarioDAO = FactoryDAO.criarUsuarioDAO();
	}

	public boolean adicionar(Usuario usuario) {
		return this.usuarioDAO.adicionar(usuario) > 0;
	}

	public boolean editar(Usuario usuario) {
		return this.usuarioDAO.editar(usuario) > 0;
	}

	public boolean excluir(Usuario usuario) {
		return this.usuarioDAO.excluir(usuario) > 0;
	}

	public Usuario getById(int id) {
		return this.usuarioDAO.getById(id);
	}

	public List<Usuario> getAllBy(Usuario usuario) {
		return this.usuarioDAO.getAllBy(usuario);
	}

}
