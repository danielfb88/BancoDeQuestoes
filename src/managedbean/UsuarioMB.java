package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dominio.usuario.Grupo;
import dominio.usuario.Usuario;

/**
 * Bean Gerenciável de Usuario.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 05-09-2012
 * 
 */
public class UsuarioMB {
	private Usuario usuario = new Usuario();
	private Grupo grupo = new Grupo();

	private List<Usuario> lista;

	private String senhaAtual;
	private String senhaNova;
	private String confirmaSenha;

	private void injetarObjetos() {
		usuario.setGrupo(grupo);
	}

	private void novosObjetos() {
		usuario = new Usuario();
		grupo = new Grupo();
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
		// verifica se a senha e a confirmação sao iguais
		if (!this.confirmaSenha.equals(usuario.getSenha())) {
			FacesMessage facesMessage = new FacesMessage("Senha não confirmada corretamente");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			// Vai permanecer na mesma página
			return null;
		}

		injetarObjetos();
		if (usuario.adicionar()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			// Adicionando uma mensagem ao contexto do JSF
			FacesMessage facesMessage = new FacesMessage("Não foi possível adicionar o usuario");
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
		// Aumentando a segurança. para editar. a pessoa deve saber a senha
		if (this.senhaAtual.equals(usuario.getSenha())) {
			// se ele solicitar mudar de senha.
			if (this.senhaNova != null && !this.senhaNova.isEmpty()) {
				// se a confirmaçao estiver ok
				if (this.senhaNova.equals(this.confirmaSenha)) {
					usuario.setSenha(this.senhaNova);

				} else {
					FacesMessage facesMessage = new FacesMessage("Senha não confirmada corretamente");
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, facesMessage);
					// Vai permanecer na mesma página
					return null;
				}
			}

			// editando
			injetarObjetos();
			if (usuario.editar()) {
				novosObjetos();

				// nulando a lista para obriga-lo a buscar novamente do banco
				this.lista = null;
				// sucesso!
				return this.paginaListar();

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage facesMessage = new FacesMessage("Não foi possível editar o usuario");
				context.addMessage(null, facesMessage);
				// Vai permanecer na mesma página
				return null;
			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Você deve inserir a senha atual para que seja possível editar este usuário");
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
		if (usuario.excluir()) {
			novosObjetos();

			// nulando a lista para obriga-lo a buscar novamente do banco
			this.lista = null;
			return this.paginaListar();

		} else {
			this.usuario = new Usuario();
			FacesMessage facesMessage = new FacesMessage("Não é possível excluir o usuario");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	/**
	 * Retornar Usuario
	 * 
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	/**
	 * Retornar Lista de Usuarios
	 * 
	 * @return
	 */
	public List<Usuario> getLista() {
		if (this.lista == null)
			this.lista = this.usuario.listar();

		return this.lista;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the senhaAtual
	 */
	public String getSenhaAtual() {
		return senhaAtual;
	}

	/**
	 * @param senhaAtual
	 *            the senhaAtual to set
	 */
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	/**
	 * @return the senhaNova
	 */
	public String getSenhaNova() {
		return senhaNova;
	}

	/**
	 * @param senhaNova
	 *            the senhaNova to set
	 */
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	/**
	 * @return the confirmaSenha
	 */
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	/**
	 * @param confirmaSenha
	 *            the confirmaSenha to set
	 */
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
