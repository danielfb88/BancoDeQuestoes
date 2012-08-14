package app.bo;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.dao.iterface.IUsuarioDAO;
import app.dto.Usuario;
import app.util.FactoryDAO;

/**
 * Usuario BO
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 *
 */
public class UsuarioBO {
	private IUsuarioDAO usuarioDAO;

	public UsuarioBO() {
		this.usuarioDAO = FactoryDAO.criarUsuarioDAO();
	}
	
	/**
	 * Verifica se o login já existe na base de dados
	 * @param login
	 * @return
	 */
	public boolean loginDisponivel(String login) {
		List<Usuario> listUsuario = this.usuarioDAO.
				getAllBy(new Usuario(null, null, null, login, null));
		
		return listUsuario.size() == 0;
	}
	
	public boolean adicionar(Usuario usuario) {
		if (this.loginDisponivel(usuario.getLogin())) {
			return this.usuarioDAO.adicionar(usuario) > 0;
			
		} else {
			FacesMessage facesMessage = new FacesMessage("O login informado já existe na base de dados");
			FacesContext context = FacesContext.getCurrentInstance();
			
			context.addMessage(null, facesMessage);
			return false;
		}
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
