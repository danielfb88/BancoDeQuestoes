package app.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.Prova;

/**
 * Bean Gerenciável de Prova.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 07-09-2012
 * 
 */
public class ProvaMB {
	private Prova prova = new Prova();
	private List<Prova> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.prova = new Prova();
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
		if (prova.adicionar()) {
			this.prova = new Prova();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar a Prova");
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
		if (prova.editar()) {
			this.prova = new Prova();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar a Prova");
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
		if (prova.excluir()) {
			this.prova = new Prova();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.prova = new Prova();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir a Prova");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Prova
	 * 
	 * @return
	 */
	public Prova getProva() {
		return prova;
	}

	/**
	 * Retornar Lista de Prova
	 * 
	 * @return
	 */
	public List<Prova> getLista() {
		if (this.lista == null)
			this.lista = this.prova.listar(false);

		return this.lista;
	}

	/**
	 * @param Prova
	 *            the Prova to set
	 */
	public void setResposta(Prova prova) {
		this.prova = prova;
	}

}
