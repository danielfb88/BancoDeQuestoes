package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.curso.AnoSemestre;
import dominio.curso.Grade;
import dominio.curso.GradePeriodo;
import dominio.curso.Periodo;
import dominio.prova.Prova;

/**
 * Bean Gerenciável de Prova.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 27-09-2012
 * 
 */
public class ProvaMB {
	private Prova prova = new Prova();
	private GradePeriodo gradePeriodo = new GradePeriodo();
	private Grade grade = new Grade();
	private Periodo periodo = new Periodo();
	private AnoSemestre anoSemestre = new AnoSemestre();

	private List<Prova> lista;

	private void injetarObjetos() {
		gradePeriodo.setGrade(grade);
		gradePeriodo.setPeriodo(periodo);
		gradePeriodo = gradePeriodo.listar().get(0);

		prova.setGradePeriodo(gradePeriodo);
		prova.setAnoSemestre(anoSemestre);
	}

	private void novosObjetos() {
		prova = new Prova();
		gradePeriodo = new GradePeriodo();
		grade = new Grade();
		periodo = new Periodo();
		anoSemestre = new AnoSemestre();
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

		if (prova.adicionar()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o Prova");
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

		if (prova.editar()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o Prova");
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
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.prova = new Prova();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o Prova");
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
	 * Retornar Lista de Provas
	 * 
	 * @return
	 */
	public List<Prova> getLista() {
		if (this.lista == null)
			this.lista = this.prova.listar();

		return this.lista;
	}

	/**
	 * @param Prova
	 *            the Prova to set
	 */
	public void setProva(Prova Prova) {
		this.prova = Prova;
	}

	public GradePeriodo getGradePeriodo() {
		return gradePeriodo;
	}

	public void setGradePeriodo(GradePeriodo gradePeriodo) {
		this.gradePeriodo = gradePeriodo;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

}
