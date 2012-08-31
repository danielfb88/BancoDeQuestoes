package app.controller;

// TODO: INCOMPLETO
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.PerguntaDAO;

/**
 * Pergunta
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Pergunta {
	private Integer id_pergunta;
	private Usuario usuario;
	private String descricao;
	private Character tipo_pergunta;
	private Character nivel_pergunta;
	private String enunciado;
	private String comentario;

	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	private String[] perguntaPrimaryKey = this.perguntaDAO.getPrimaryKey();
	private String[] perguntaCampos = this.perguntaDAO.getCampos();

	/**
	 * Pergunta
	 */
	public Pergunta() {
		this.usuario = new Usuario();
	}

	/**
	 * Pergunta
	 * 
	 * @param id_pergunta
	 * @param usuario
	 * @param descricao
	 * @param tipo_pergunta
	 * @param nivel_pergunta
	 * @param enunciado
	 * @param comentario
	 */
	public Pergunta(Integer id_pergunta, Usuario usuario, String descricao,
			Character tipo_pergunta, Character nivel_pergunta,
			String enunciado, String comentario) {
		super();
		this.id_pergunta = id_pergunta;
		this.usuario = usuario;
		this.descricao = descricao;
		this.tipo_pergunta = tipo_pergunta;
		this.nivel_pergunta = nivel_pergunta;
		this.enunciado = enunciado;
		this.comentario = comentario;
	}

	/**
	 * Cria objeto baseado no HashMap de entrada
	 * 
	 * @param map
	 */
	Pergunta novoObjeto(Map<String, Object> map, boolean carregarRelacionamentos) {

		Pergunta pergunta = new Pergunta();
		pergunta.setId_pergunta((Integer) map.get(perguntaPrimaryKey[0]));
		pergunta.getUsuario().setId_usuario(
				(Integer) map.get(perguntaCampos[0]));
		pergunta.setDescricao((String) map.get(perguntaCampos[1]));
		pergunta.setTipo_pergunta(map.get(perguntaCampos[2]).toString()
				.charAt(0));
		pergunta.setNivel_pergunta(map.get(perguntaCampos[3]).toString()
				.charAt(0));
		pergunta.setEnunciado((String) map.get(perguntaCampos[4]));
		pergunta.setComentario((String) map.get(perguntaCampos[5]));

		// carregando relacionamento
		if (carregarRelacionamentos)
			pergunta.getUsuario().carregar(carregarRelacionamentos);

		return pergunta;
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada
	 * 
	 * @param map
	 */
	void carregarObjeto(Map<String, Object> map, boolean carregarRelacionamentos) {

		this.id_pergunta = (Integer) map.get(perguntaPrimaryKey[0]);
		this.usuario.setId_usuario((Integer) map.get(perguntaCampos[0]));
		this.descricao = (String) map.get(perguntaCampos[0]);
		this.tipo_pergunta = map.get(perguntaCampos[1]).toString().charAt(0);
		this.nivel_pergunta = map.get(perguntaCampos[2]).toString().charAt(0);
		this.enunciado = (String) map.get(perguntaCampos[3]);
		this.comentario = (String) map.get(perguntaCampos[4]);

		if (carregarRelacionamentos)
			this.usuario.carregar(carregarRelacionamentos);
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.perguntaDAO.adicionar(usuario.getId_usuario(), descricao,
				tipo_pergunta.toString(), nivel_pergunta.toString(), enunciado,
				comentario) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.perguntaDAO
				.buscarPorId(this.id_pergunta);

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
		return this.perguntaDAO.editar(id_pergunta, usuario.getId_usuario(),
				descricao, tipo_pergunta.toString(), nivel_pergunta.toString(),
				enunciado, comentario) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.perguntaDAO.excluir(id_pergunta) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Pergunta> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.perguntaDAO.listarPor(
				usuario.getId_usuario(), descricao, tipo_pergunta.toString(),
				nivel_pergunta.toString(), enunciado, comentario);

		// lista de perguntas
		List<Pergunta> perguntas = new ArrayList<Pergunta>();

		// Iterando
		for (Map<String, Object> map : listMap) {
			Pergunta pergunta = this.novoObjeto(map, carregarRelacionamentos);

			perguntas.add(pergunta);
		}
		// retornando a lista
		return perguntas;
	}

	public List<Pergunta> listarDisciplinas() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	public List<Assunto> listarAssuntos() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	public List<Prova> listarProvas() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	public Integer getId_pergunta() {
		return id_pergunta;
	}

	public void setId_pergunta(Integer id_pergunta) {
		this.id_pergunta = id_pergunta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getTipo_pergunta() {
		return tipo_pergunta;
	}

	public void setTipo_pergunta(Character tipo_pergunta) {
		this.tipo_pergunta = tipo_pergunta;
	}

	public Character getNivel_pergunta() {
		return nivel_pergunta;
	}

	public void setNivel_pergunta(Character nivel_pergunta) {
		this.nivel_pergunta = nivel_pergunta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result
				+ ((id_pergunta == null) ? 0 : id_pergunta.hashCode());
		result = prime * result
				+ ((nivel_pergunta == null) ? 0 : nivel_pergunta.hashCode());
		result = prime * result
				+ ((tipo_pergunta == null) ? 0 : tipo_pergunta.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (id_pergunta == null) {
			if (other.id_pergunta != null)
				return false;
		} else if (!id_pergunta.equals(other.id_pergunta))
			return false;
		if (nivel_pergunta == null) {
			if (other.nivel_pergunta != null)
				return false;
		} else if (!nivel_pergunta.equals(other.nivel_pergunta))
			return false;
		if (tipo_pergunta == null) {
			if (other.tipo_pergunta != null)
				return false;
		} else if (!tipo_pergunta.equals(other.tipo_pergunta))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
