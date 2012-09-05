package app.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.Grupo;

/**
 * Grupo Bean. Poderá ser gerenciado pelo JSF atraves do arquivo
 * faces-config.xml ou por meio de annotations.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-08-2012
 * 
 */
public class GrupoManagedBean {
	private Grupo grupo = new Grupo();
	private List<Grupo> grupos;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String novo() {
		this.grupo = new Grupo();
		return "novo";
	}

	/**
	 * Direciona para a página de edição
	 * 
	 * @return
	 */
	public String formularioEditar() {
		return "formularioEditar";
	}

	/**
	 * Direciona para a página de listagem
	 * 
	 * @return
	 */
	public String listar() {
		this.grupos = new Grupo().listar();

		return "listar";
	}

	/**
	 * Edita registro
	 * 
	 * @return
	 */
	public String editar() {
		if (grupo.editar()) {
			this.grupo = new Grupo();
			return this.listar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o grupo");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	/**
	 * Grava registro
	 * 
	 * @return
	 */
	public String adicionar() {
		if (grupo.adicionar()) {
			this.grupo = new Grupo();
			return this.listar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o grupo");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	/**
	 * Exclui registro
	 * 
	 * @return
	 */
	public String excluir() {
		if (grupo.excluir()) {
			this.grupo = new Grupo();
			return this.listar();

		} else {
			this.grupo = new Grupo();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o grupo");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
