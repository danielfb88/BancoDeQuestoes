package app.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import app.controller.Grade;

/**
 * Bean Gerenciável de Grade.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 07-09-2012
 * 
 */
public class GradeMB {
	private Grade grade = new Grade();
	private List<Grade> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.grade = new Grade();
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
		if (grade.adicionar()) {
			this.grade = new Grade();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar a Grade");
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
		if (grade.editar()) {
			this.grade = new Grade();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar a Grade");
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
		if (grade.excluir()) {
			this.grade = new Grade();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.grade = new Grade();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir a Grade");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Grade
	 * 
	 * @return
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * Retornar Lista de Grade
	 * 
	 * @return
	 */
	public List<Grade> getLista() {
		if (this.lista == null)
			this.lista = this.grade.listar(false);

		return this.lista;
	}

	/**
	 * @param Grade
	 *            the Grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
