package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.prova.Pergunta;

/**
 * Bean Gerenciável de Pergunta.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 07-09-2012
 * 
 */
public class PerguntaMB {
	private Pergunta pergunta = new Pergunta();
	private List<Pergunta> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.pergunta = new Pergunta();
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
		if (pergunta.adicionar()) {
			this.pergunta = new Pergunta();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível adicionar o Pergunta");
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
		if (pergunta.editar()) {
			this.pergunta = new Pergunta();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar a Pergunta");
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
		if (pergunta.excluir()) {
			this.pergunta = new Pergunta();
			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.pergunta = new Pergunta();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir a Pergunta");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Pergunta
	 * 
	 * @return
	 */
	public Pergunta getPergunta() {
		return pergunta;
	}

	/**
	 * Retornar Lista de Pergunta
	 * 
	 * @return
	 */
	public List<Pergunta> getLista() {
		if (this.lista == null)
			this.lista = this.pergunta.listar();

		return this.lista;
	}

	/**
	 * @param Pergunta
	 *            the Pergunta to set
	 */
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

}
