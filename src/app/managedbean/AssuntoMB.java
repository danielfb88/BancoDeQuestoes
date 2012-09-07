package app.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.Assunto;

/**
 * Bean Gerenciável de Assunto.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 07-09-2012
 * 
 */
public class AssuntoMB {
	private Assunto assunto = new Assunto();
	private List<Assunto> assuntos;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.assunto = new Assunto();
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
		if (assunto.adicionar()) {
			this.assunto = new Assunto();
			return this.paginaListar();

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

	/**
	 * Edita registro
	 * 
	 * @return
	 */
	public String editar() {
		if (assunto.editar()) {
			this.assunto = new Assunto();
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o assunto");
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
		if (assunto.excluir()) {
			this.assunto = new Assunto();
			return this.paginaListar();

		} else {
			this.assunto = new Assunto();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o assunto");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Assunto
	 * 
	 * @return
	 */
	public Assunto getAssunto() {
		return assunto;
	}

	/**
	 * Retornar Assuntos
	 * 
	 * @return
	 */
	public List<Assunto> getAssuntos() {
		this.assuntos = this.assunto.listar();
		return this.assuntos;
	}

	/**
	 * @param assunto
	 *            the assunto to set
	 */
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	/**
	 * @param assuntos
	 *            the assuntos to set
	 */
	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

}
