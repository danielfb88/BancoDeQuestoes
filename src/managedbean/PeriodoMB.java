package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.curso.Periodo;


/**
 * Bean Gerenciável de Periodo.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 27-09-2012
 * 
 */
public class PeriodoMB {
	private Periodo periodo = new Periodo();
	private List<Periodo> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.periodo = new Periodo();
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
		if (periodo.adicionar()) {
			this.periodo = new Periodo();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o Periodo");
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
		if (periodo.editar()) {
			this.periodo = new Periodo();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar o Periodo");
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
		if (periodo.excluir()) {
			this.periodo = new Periodo();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.periodo = new Periodo();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir o Periodo");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Periodo
	 * 
	 * @return
	 */
	public Periodo getPeriodo() {
		return periodo;
	}

	/**
	 * Retornar Lista de Periodos
	 * 
	 * @return
	 */
	public List<Periodo> getLista() {
		if (this.lista == null)
			this.lista = this.periodo.listar();

		return this.lista;
	}

	/**
	 * @param Periodo
	 *            the Periodo to set
	 */
	public void setPeriodo(Periodo Periodo) {
		this.periodo = Periodo;
	}

}
