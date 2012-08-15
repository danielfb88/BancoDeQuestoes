package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.AssuntoBO;
import app.dto.Assunto;

/**
 * Assunto Bean.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
@ManagedBean(name="assuntoBean")
@RequestScoped
public class AssuntoManagedBean {
	private AssuntoBO assuntoBO = new AssuntoBO();
	private Assunto assunto = new Assunto();
	private List<Assunto> assuntos = new LinkedList<Assunto>();

	public String novo() {
		this.assunto = new Assunto();
		return "novo";
	}
	
	public String formularioEditar() {
		return "formularioEditar";
	}

	public String adicionar() {
		if (assuntoBO.adicionar(this.assunto)) {
			this.assunto = new Assunto();
			return "adicionadoComSucesso";
		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o assunto");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	public String excluir() {
		if (assuntoBO.excluir(this.assunto)) {
			this.assunto = new Assunto();
			return "excluidoComSucesso";
			
		} else {
			this.assunto = new Assunto();
			FacesMessage facesMessage = new FacesMessage("Não é possível excluir o assunto");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
		
	}

	public String editar() {
		if (assuntoBO.editar(this.assunto)) {
			this.assunto = new Assunto();
			return "editadoComSucesso";
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o assunto");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}
	
	public String listar() {
		return "listar";
	}

	public List<Assunto> getAssuntos() {
		this.assuntos = assuntoBO.getAllBy(this.assunto);
		return this.assuntos;
	}

	public Assunto getAssunto() {
		return this.assunto;
	}
	
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

}
