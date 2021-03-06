package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.curso.Curso;


/**
 * Bean Gerenciável de Curso.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 27-09-2012
 * 
 */
public class CursoMB {
	private Curso curso = new Curso();
	private List<Curso> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.curso = new Curso();
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
		if (curso.adicionar()) {
			this.curso = new Curso();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o Curso");
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
		if (curso.editar()) {
			this.curso = new Curso();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o Curso");
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
		if (curso.excluir()) {
			this.curso = new Curso();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.curso = new Curso();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o Curso");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Curso
	 * 
	 * @return
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * Retornar Lista de Cursos
	 * 
	 * @return
	 */
	public List<Curso> getLista() {
		if (this.lista == null)
			this.lista = this.curso.listar();

		return this.lista;
	}

	/**
	 * @param Curso
	 *            the Curso to set
	 */
	public void setCurso(Curso Curso) {
		this.curso = Curso;
	}

}
