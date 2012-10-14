package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.prova.Pergunta;
import dominio.prova.Resposta;

/**
 * Bean Gerenciável de Resposta.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 07-09-2012
 * 
 */
public class RespostaMB {
	private Resposta resposta = new Resposta();
	private Pergunta pergunta = new Pergunta();
	private List<Resposta> lista;

	/**
	 * Direciona para a Página de Adicionar Novo
	 * 
	 * @return
	 */
	public String formularioAdicionar() {
		this.resposta = new Resposta();
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

	private void injetarObjetos() {
		resposta.setPergunta(pergunta);
	}

	private void novosObjetos() {
		resposta = new Resposta();
		pergunta = new Pergunta();
	}

	/**
	 * Grava registro
	 * 
	 * @return
	 */
	public String adicionar() {
		injetarObjetos();

		if (resposta.adicionar()) {
			novosObjetos();

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
		injetarObjetos();

		if (resposta.editar()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Não foi possível editar a Resposta");
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
		if (resposta.excluir()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.resposta = new Resposta();
			FacesMessage facesMessage = new FacesMessage(
					"Não é possível excluir a Pergunta");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Resposta
	 * 
	 * @return
	 */
	public Resposta getResposta() {
		return resposta;
	}

	/**
	 * Retornar Lista de Pergunta
	 * 
	 * @return
	 */
	public List<Resposta> getLista() {
		if (this.lista == null)
			this.lista = this.resposta.listar();

		return this.lista;
	}

	/**
	 * @param Pergunta
	 *            the Pergunta to set
	 */
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

}
