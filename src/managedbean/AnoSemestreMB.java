package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.curso.AnoSemestre;


/**
 * Bean Gerenciável de AnoSemestre.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 27-09-2012
 * 
 */
public class AnoSemestreMB {
	private AnoSemestre anoSemestre = new AnoSemestre();
	private List<AnoSemestre> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.anoSemestre = new AnoSemestre();
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
		if (anoSemestre.adicionar()) {
			this.anoSemestre = new AnoSemestre();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o AnoSemestre");
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
		if (anoSemestre.editar()) {
			this.anoSemestre = new AnoSemestre();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o AnoSemestre");
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
		if (anoSemestre.excluir()) {
			this.anoSemestre = new AnoSemestre();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.anoSemestre = new AnoSemestre();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o AnoSemestre");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar AnoSemestre
	 * 
	 * @return
	 */
	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	/**
	 * Retornar Lista de AnoSemestres
	 * 
	 * @return
	 */
	public List<AnoSemestre> getLista() {
		if (this.lista == null)
			this.lista = this.anoSemestre.listar();

		return this.lista;
	}

	/**
	 * @param AnoSemestre
	 *            the AnoSemestre to set
	 */
	public void setAnoSemestre(AnoSemestre AnoSemestre) {
		this.anoSemestre = AnoSemestre;
	}

}
