package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.CursoBO;
import app.dto.Curso;

/**
 * Curso Bean.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 15-08-2012
 * 
 */
@ManagedBean(name="cursoBean")
@RequestScoped
public class CursoManagedBean {
	private CursoBO cursoBO = new CursoBO();
	private Curso curso = new Curso();
	private List<Curso> cursos = new LinkedList<Curso>();

	public String novo() {
		this.curso = new Curso();
		return "novo";
	}
	
	public String formularioEditar() {
		return "formularioEditar";
	}

	public String adicionar() {
		if (cursoBO.adicionar(this.curso)) {
			this.curso = new Curso();
			return "adicionadoComSucesso";
		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o curso");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	public String excluir() {
		if (cursoBO.excluir(this.curso)) {
			this.curso = new Curso();
			return "excluidoComSucesso";
			
		} else {
			this.curso = new Curso();
			FacesMessage facesMessage = new FacesMessage("Não é possível excluir o curso");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
		
	}

	public String editar() {
		if (cursoBO.editar(this.curso)) {
			this.curso = new Curso();
			return "editadoComSucesso";
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o curso");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}
	
	public String listar() {
		return "listar";
	}

	public List<Curso> getCursos() {
		this.cursos = cursoBO.getAllBy(this.curso);
		return this.cursos;
	}

	public Curso getCurso() {
		return this.curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
