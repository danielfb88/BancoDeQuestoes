package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.PerguntaBO;
import app.controller.Pergunta;

/**
 * Pergunta Bean
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
@ManagedBean(name = "perguntaBean")
@RequestScoped
public class PerguntaManagedBean {
	private PerguntaBO perguntaBO = new PerguntaBO();
	private List<Pergunta> perguntas = new LinkedList<Pergunta>();
	private Pergunta pergunta = new Pergunta();
	
	public String novo() {
		this.pergunta = new Pergunta();
		return "novo";
	}

	public String formularioEditar() {
		return "formularioEditar";
	}

	public String listar() {
		//this.pergunta = new Pergunta();
		return "listar";
	}

	public String visualizar() {
		return "visualizar";
	}

	public String adicionar() {
		if (perguntaBO.adicionar(this.pergunta)) {
			this.pergunta = new Pergunta();
			return "adicionadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o pergunta");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public String excluir() {
		if (perguntaBO.excluir(pergunta)) {
			this.pergunta = new Pergunta();
			return "excluidoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível excluir o pergunta");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}

	}

	public String editar() {
		if (perguntaBO.editar(this.pergunta)) {
			this.pergunta = new Pergunta();
			return "editadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o pergunta");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public List<Pergunta> getPerguntas() {
		this.perguntas = this.perguntaBO.getAllBy(this.pergunta);
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

}
