package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.curso.AnoSemestre;
import dominio.curso.Curso;
import dominio.curso.Grade;

/**
 * Bean Gerenciável de Grade.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 27-09-2012
 * 
 */
public class GradeMB {
	private Grade grade = new Grade();

	private Curso curso = new Curso();
	private AnoSemestre anoSemestre_inicial = new AnoSemestre();
	private AnoSemestre anoSemestre_final = new AnoSemestre();

	private List<Grade> lista;

	private void novosObjetos() {
		grade = new Grade();
		curso = new Curso();
		anoSemestre_inicial = new AnoSemestre();
		anoSemestre_final = new AnoSemestre();
	}

	private void injetarObjetos() {
		grade.setCurso(curso);
		grade.setAnoSemestre_inicial(anoSemestre_inicial);
		grade.setAnoSemestre_final(anoSemestre_final);
	}

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		novosObjetos();
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
		injetarObjetos();

		if (grade.adicionar()) {
			novosObjetos();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o Grade");
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
		injetarObjetos();

		if (grade.editar()) {
			novosObjetos();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o Grade");
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
			novosObjetos();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.grade = new Grade();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o Grade");
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
	 * Retornar Lista de Grades
	 * 
	 * @return
	 */
	public List<Grade> getLista() {
		if (this.lista == null)
			this.lista = this.grade.listar();

		return this.lista;
	}

	/**
	 * @param Grade
	 *            the Grade to set
	 */
	public void setGrade(Grade Grade) {
		this.grade = Grade;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public AnoSemestre getAnoSemestre_inicial() {
		return anoSemestre_inicial;
	}

	public void setAnoSemestre_inicial(AnoSemestre anoSemestre_inicial) {
		this.anoSemestre_inicial = anoSemestre_inicial;
	}

	public AnoSemestre getAnoSemestre_final() {
		return anoSemestre_final;
	}

	public void setAnoSemestre_final(AnoSemestre anoSemestre_final) {
		this.anoSemestre_final = anoSemestre_final;
	}

}
