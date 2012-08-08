package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.GrupoController;
import app.dto.Grupo;

/**
 * ManagedBean para Grupo. Bean que será gerenciado pelo JSF atraves do arquivo
 * faces-config.xml.
 * 
 * @author Daniel Bonfim
 * @since 03-08-2012
 * 
 */
public class GrupoManagedBean {
	private Grupo grupo = new Grupo();
	private List<Grupo> grupos = new LinkedList<Grupo>();

	public String novo() {
		return "grupo";
	}

	public String adicionar() {
		GrupoController grupoController = new GrupoController();

		if (grupoController.adicionar(this.grupo)) {
			return "listarGrupos";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o grupo");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	public String deletar() {
		GrupoController grupoController = new GrupoController();
		grupoController.excluir(this.grupo);
		return "listarGrupos";
	}

	public String alterar() {
		GrupoController grupoController = new GrupoController();

		if (grupoController.alterar(this.grupo)) {
			return "listarGrupos";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível alterar o grupo");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	public List<Grupo> getGrupos() {
		GrupoController grupoController = new GrupoController();
		this.grupos = grupoController.getAllBy(this.grupo);
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
