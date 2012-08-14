package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.GrupoBO;
import app.bo.UsuarioBO;
import app.dto.Grupo;
import app.dto.Usuario;

/**
 * Usuario Bean
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 14-08-2012
 * 
 */
@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioManagedBean {
	private UsuarioBO usuarioBO = new UsuarioBO();
	private List<Usuario> usuarios = new LinkedList<Usuario>();
	private Usuario usuario = new Usuario();
	
	private GrupoBO grupoBO = new GrupoBO();
	private List<Grupo> grupos = new LinkedList<Grupo>();

	public String novo() {
		this.usuario = new Usuario();
		return "novo";
	}

	public String formularioEditar() {
		return "formularioEditar";
	}

	public String listar() {
		//this.usuario = new Usuario();
		return "listar";
	}

	public String visualizar() {
		return "visualizar";
	}

	public String adicionar() {
		if (usuarioBO.adicionar(this.usuario)) {
			this.usuario = new Usuario();
			return "adicionadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o usuario");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public String excluir() {
		if (usuarioBO.excluir(usuario)) {
			this.usuario = new Usuario();
			return "excluidoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível excluir o usuario");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}

	}

	public String editar() {
		if (usuarioBO.editar(this.usuario)) {
			this.usuario = new Usuario();
			return "editadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o usuario");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		this.usuarios = this.usuarioBO.getAllBy(this.usuario);
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Grupo> getGrupos() {
		this.grupos = this.grupoBO.getAllBy(new Grupo());
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
