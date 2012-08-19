package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.CursoBO;
import app.bo.DisciplinaBO;
import app.controller.Disciplina;
import app.dto.Curso;

/**
 * Disciplina Bean
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 14-08-2012
 * 
 */
@ManagedBean(name = "disciplinaBean")
@RequestScoped
public class DisciplinaManagedBean {
	private DisciplinaBO disciplinaBO = new DisciplinaBO();
	private List<Disciplina> disciplinas = new LinkedList<Disciplina>();
	private Disciplina disciplina = new Disciplina();
	
	private CursoBO cursoBO = new CursoBO();
	private List<Curso> cursos = new LinkedList<Curso>();

	public String novo() {
		this.disciplina = new Disciplina();
		return "novo";
	}

	public String formularioEditar() {
		return "formularioEditar";
	}

	public String listar() {
		//this.disciplina = new Disciplina();
		return "listar";
	}

	public String visualizar() {
		return "visualizar";
	}

	public String adicionar() {
		if (disciplinaBO.adicionar(this.disciplina)) {
			this.disciplina = new Disciplina();
			return "adicionadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o disciplina");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public String excluir() {
		if (disciplinaBO.excluir(disciplina)) {
			this.disciplina = new Disciplina();
			return "excluidoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível excluir o disciplina");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}

	}

	public String editar() {
		if (disciplinaBO.editar(this.disciplina)) {
			this.disciplina = new Disciplina();
			return "editadoComSucesso";

		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o disciplina");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		this.disciplinas = this.disciplinaBO.getAllBy(this.disciplina);
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Curso> getCursos() {
		this.cursos = this.cursoBO.getAllBy(new Curso());
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
