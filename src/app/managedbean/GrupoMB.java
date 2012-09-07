package app.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.Grupo;

/**
 * Bean Gerenciável de Grupo.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 05-09-2012
 * 
 */
public class GrupoMB {
	private Grupo grupo = new Grupo();
	private List<Grupo> grupos;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.grupo = new Grupo();
		return "formularioAdicionar";
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
	public String paginaListar() {
		return "paginaListar";
	}

	/**
	 * Grava registro
	 * 
	 * @return
	 */
	public String adicionar() {
		if (grupo.adicionar()) {
			this.grupo = new Grupo();
			return this.paginaListar();

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
	 * Edita registro
	 * 
	 * @return
	 */
	public String editar() {
		if (grupo.editar()) {
			this.grupo = new Grupo();
			return this.paginaListar();

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
	 * Exclui registro
	 * 
	 * @return
	 */
	public String excluir() {
		if (grupo.excluir()) {
			this.grupo = new Grupo();
			return this.paginaListar();

		} else {
			this.grupo = new Grupo();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o grupo");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Grupo
	 * 
	 * @return
	 */
	public Grupo getGrupo() {
		return grupo;
	}

	/**
	 * Retornar Grupos
	 * 
	 * @return
	 */
	public List<Grupo> getGrupos() {
		this.grupos = this.grupo.listar();
		return this.grupos;
	}

	/**
	 * @param grupo
	 *            the grupo to set
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	/**
	 * @param grupos
	 *            the grupos to set
	 */
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}
