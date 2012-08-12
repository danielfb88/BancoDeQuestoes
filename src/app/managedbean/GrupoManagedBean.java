package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.bo.GrupoBO;
import app.dto.Grupo;

/**
 * ManagedBean para Grupo. Bean que será gerenciado pelo JSF atraves do arquivo
 * faces-config.xml.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-08-2012
 * 
 */
public class GrupoManagedBean {
	private Grupo grupo = new Grupo();
	private List<Grupo> grupos = new LinkedList<Grupo>();

	public String novo() {
		this.grupo = new Grupo();
		return "grupo";
	}

	public String adicionar() {
		GrupoBO grupoController = new GrupoBO();

		if (grupoController.adicionar(this.grupo)) {
			// removendo o grupo atual para montar a query limpa
			this.grupo = new Grupo();
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
		GrupoBO grupoController = new GrupoBO();
		grupoController.excluir(this.grupo);
		// removendo o grupo atual para montar a query limpa
		this.grupo = new Grupo();
		return "listarGrupos";
	}

	public String editar() {
		GrupoBO grupoController = new GrupoBO();

		if (grupoController.editar(this.grupo)) {
			// removendo o grupo atual para montar a query limpa
			this.grupo = new Grupo();
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
		GrupoBO grupoController = new GrupoBO();
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
