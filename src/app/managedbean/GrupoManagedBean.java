package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@ManagedBean(name="grupoBean")
@RequestScoped
public class GrupoManagedBean {
	private Grupo grupo = new Grupo();
	private List<Grupo> grupos = new LinkedList<Grupo>();

	public String novo() {
		this.grupo = new Grupo();
		return "novo";
	}
	
	public String formularioEditar() {
		return "formularioEditar";
	}

	public String adicionar() {
		if (grupo.adicionar()) {
			// removendo o grupo atual para montar a query limpa
			this.grupo = new Grupo();
			return "adicionadoComSucesso";
			
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

	public String excluir() {
		if (grupo.excluir()) {
			// removendo o grupo atual para montar a query limpa
			this.grupo = new Grupo();
			return "excluidoComSucesso";
			
		} else {
			this.grupo = new Grupo();
			FacesMessage facesMessage = new FacesMessage("Não é possível excluir o grupo");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
		
	}

	public String editar() {
		if (grupo.editar()) {
			// removendo o grupo atual para montar a query limpa
			this.grupo = new Grupo();
			return "editadoComSucesso";
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o grupo");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}
	
	public String listar() {
		return "listar";
	}

	public List<Grupo> getGrupos() {
		this.grupos = grupo.listar();
		return this.grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}
