package app.controller;

// TODO: INCOMPLETO
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.CoordenadorCursoDAO;
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
	private Grupo grupo;
	private String nome;
	private String login;
	private String senha;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private CoordenadorCursoDAO coordenadorCursoDAO = new CoordenadorCursoDAO();

	private String[] usuarioPrimaryKey = this.usuarioDAO.getPrimaryKey();
	private String[] usuarioCampos = this.usuarioDAO.getCampos();

	/**
	 * Usuario
	 */
	public Usuario() {
		grupo = new Grupo();
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

	/**
	 * Cria objeto baseado no HashMap de entrada
	 * 
	 * @param map
	 */
	private Usuario novoObjeto(Map<String, Object> map) {

		Usuario usuario = new Usuario();
		usuario.setId_usuario((Integer) map.get(usuarioPrimaryKey[0]));
		usuario.grupo.setId_grupo((Integer) map.get(usuarioCampos[0]));
		usuario.setNome((String) map.get(usuarioCampos[1]));
		usuario.setLogin((String) map.get(usuarioCampos[2]));
		usuario.setSenha((String) map.get(usuarioCampos[3]));

		return usuario;
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada
	 * 
	 * @param map
	 */
	private void carregarObjeto(Map<String, Object> map) {

		this.id_usuario = (Integer) map.get(usuarioPrimaryKey[0]);
		this.grupo.setId_grupo((Integer) map.get(usuarioCampos[0]));
		this.nome = (String) map.get(usuarioCampos[1]);
		this.login = (String) map.get(usuarioCampos[2]);
		this.senha = (String) map.get(usuarioCampos[3]);
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
		Map<String, Object> mapUsuario = this.usuarioDAO
				.buscarPorId(this.id_usuario);

		if (mapUsuario != null) {
			this.carregarObjeto(mapUsuario);

			if (carregarRelacionamentos)
				this.grupo.carregar();

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
		List<Map<String, Object>> listMapUsuarios = this.usuarioDAO.listarPor(
				grupo.getId_grupo(), nome, login, senha);

		// lista de usuarios
		List<Usuario> usuarios = new ArrayList<Usuario>();

		// Iterando
		for (Map<String, Object> mapUsuario : listMapUsuarios) {
			Usuario usuario = this.novoObjeto(mapUsuario);

			// carregando relacionamento
			if (carregarRelacionamentos)
				usuario.getGrupo().carregar();

			usuarios.add(usuario);
		}
		// retornando a lista
		return usuarios;
	}

	public void autenticar() {

	}

	public List<Curso> listarCursosAtuais() {

		return null;
	}

	public List<Curso> listarTodosOsCursos() {

		return null;
	}

	public boolean criarPergunta(Pergunta pergunta) {

		return false;
	}

	public boolean excluirPergunta(Pergunta pergunta) {

		return false;
	}

	public List<Pergunta> listarPerguntas() {

		return null;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

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
