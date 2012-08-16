package app.managedbean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.bo.AnoSemestreBO;
import app.dto.AnoSemestre;

/**
 * AnoSemestre Bean.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 16-08-2012
 * 
 */
@ManagedBean(name="anoSemestreBean")
@RequestScoped
public class AnoSemestreManagedBean {
	private AnoSemestreBO anoSemestreBO = new AnoSemestreBO();
	private AnoSemestre anoSemestre = new AnoSemestre();
	private List<AnoSemestre> anoSemestres = new LinkedList<AnoSemestre>();

	public String novo() {
		this.anoSemestre = new AnoSemestre();
		return "novo";
	}
	
	public String formularioEditar() {
		return "formularioEditar";
	}

	public String adicionar() {
		if (anoSemestreBO.adicionar(this.anoSemestre)) {
			this.anoSemestre = new AnoSemestre();
			return "adicionadoComSucesso";
		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o anoSemestre");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}

	public String excluir() {
		if (anoSemestreBO.excluir(this.anoSemestre)) {
			this.anoSemestre = new AnoSemestre();
			return "excluidoComSucesso";
			
		} else {
			this.anoSemestre = new AnoSemestre();
			FacesMessage facesMessage = new FacesMessage("Não é possível excluir o anoSemestre");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
		
	}

	public String editar() {
		if (anoSemestreBO.editar(this.anoSemestre)) {
			this.anoSemestre = new AnoSemestre();
			return "editadoComSucesso";
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o anoSemestre");
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}
	}
	
	public String listar() {
		return "listar";
	}

	public List<AnoSemestre> getAnoSemestres() {
		this.anoSemestres = anoSemestreBO.getAllBy(this.anoSemestre);
		return this.anoSemestres;
	}

	public AnoSemestre getAnoSemestre() {
		return this.anoSemestre;
	}
	
	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public void setAnoSemestres(List<AnoSemestre> anoSemestres) {
		this.anoSemestres = anoSemestres;
	}

}
