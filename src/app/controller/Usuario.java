package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.UsuarioDAO;

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
	public Usuario(Integer id_usuario, Grupo grupo, String nome, String login,
			String senha) {
		super();
		this.id_usuario = id_usuario;
		this.grupo = grupo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	/***
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public Usuario(Map<String, Object> map, boolean carregarRelacionamentos) {
		this.carregarObjeto(map, carregarRelacionamentos);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 *            Map espelhando a tabela correspondente deste objeto
	 * @param map
	 * @param carregarRelacionamentos
	 */
	private void carregarObjeto(Map<String, Object> map,
			boolean carregarRelacionamentos) {

		this.id_usuario = (Integer) map.get("id_usuario");
		this.grupo.setId_grupo((Integer) map.get("id_grupo"));
		this.nome = (String) map.get("nome");
		this.login = (String) map.get("login");
		this.senha = (String) map.get("senha");

		if (carregarRelacionamentos)
			this.grupo.carregar();
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.usuarioDAO.adicionar(grupo.getId_grupo(), nome, login,
				senha) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.usuarioDAO.buscarPorId(this.id_usuario);

		if (map != null) {
			this.carregarObjeto(map, carregarRelacionamentos);

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		return this.usuarioDAO.editar(id_usuario, grupo.getId_grupo(), nome,
				login, senha) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.usuarioDAO.excluir(id_usuario) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Usuario> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.usuarioDAO.listarPor(
				grupo.getId_grupo(), nome, login, senha);

		List<Usuario> listUsuario = new ArrayList<Usuario>();

		for (Map<String, Object> map : listMap) {
			listUsuario.add(new Usuario(map, carregarRelacionamentos));
		}

		return listUsuario;
	}

	/**
	 * 
	 */
	public void autenticar() {
		// TODO: Transferir esta responsabilidade para o ManagedBean de Usuario
	}

	public List<Curso> listarCursosAtuais() {
		// TODO: Usuario: Desenvolver
		return null;
	}

	public List<Curso> listarTodosOsCursos() {
		// TODO: Usuario: Desenvolver
		return null;
	}

	/**
	 * Criar Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean criarPergunta(Pergunta pergunta) {
		pergunta.setUsuario(this);
		return pergunta.adicionar();
	}

	/**
	 * Excluir Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean excluirPergunta(Pergunta pergunta) {
		// Exclui apenas pergunta criada por este usuario
		if (pergunta.getUsuario().getId_usuario() == this.id_usuario)
			return pergunta.excluir();

		return false;
	}

	/**
	 * Listar Perguntas deste Usuario
	 * 
	 * @return
	 */
	public List<Pergunta> listarPerguntas() {
		Pergunta pergunta = new Pergunta();
		pergunta.setUsuario(this);
		return pergunta.listar(false);
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
