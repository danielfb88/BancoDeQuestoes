package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.GrupoDAO;
import dao.UsuarioDAO;

/**
 * Usuario
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Usuario {
	private Integer id_usuario;
	private Grupo grupo = new Grupo();
	private String nome;
	private String login;
	private String senha;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario() {

	}

	/**
	 * Usuario
	 * 
	 * @param id_usuario
	 * @param grupo
	 * @param nome
	 * @param login
	 * @param senha
	 */
	public Usuario(Integer id_usuario, Grupo grupo, String nome, String login, String senha) {
		super();
		this.id_usuario = id_usuario;
		this.grupo = grupo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		usuarioDAO.id_usuario = this.id_usuario;
		usuarioDAO.id_grupo = this.grupo.getId_grupo();
		usuarioDAO.nome = this.nome;
		usuarioDAO.login = this.login;
		usuarioDAO.senha = this.senha;

		boolean retornoOk = usuarioDAO.adicionar() > 0;
		usuarioDAO.reset();

		return retornoOk;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		usuarioDAO.id_usuario = this.usuarioDAO;
		usuarioDAO.id_grupo = this.grupo.getId_grupo();
		

		boolean retornoOk = grupoDAO.carregar();

		this.id_grupo = grupoDAO.id_grupo;
		this.descricao = grupoDAO.descricao;
		this.tipo = grupoDAO.tipo;

		grupoDAO.reset();
		return retornoOk;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;

		boolean retornoOk = grupoDAO.editar() > 0;
		grupoDAO.reset();

		return retornoOk;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		grupoDAO.id_grupo = this.id_grupo;
		boolean retornoOk = grupoDAO.excluir() > 0;
		grupoDAO.reset();
		return retornoOk;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		List<Grupo> listGrupo = new ArrayList<Grupo>();

		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;

		List<GrupoDAO> listDAO = grupoDAO.listar();
		for (GrupoDAO g : listDAO) {
			listGrupo.add(new Grupo(g.id_grupo, g.descricao, g.tipo));
		}

		grupoDAO.reset();
		return listGrupo;
	}

	/**
	 * @return the id_usuario
	 */
	public Integer getId_usuario() {
		return id_usuario;
	}

	/**
	 * @param id_usuario
	 *            the id_usuario to set
	 */
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * @return the grupo
	 */
	public Grupo getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo
	 *            the grupo to set
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result
				+ ((id_usuario == null) ? 0 : id_usuario.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
