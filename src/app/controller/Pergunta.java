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
	private Usuario usuario = new Usuario();
	private String descricao;
	private Character tipo_pergunta;
	private Character nivel_pergunta;
	private String enunciado;
	private String comentario;

	private PerguntaDAO perguntaDAO = new PerguntaDAO();

	public Pergunta() {

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
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public Pergunta(Map<String, Object> map, boolean carregarRelacionamentos) {
		this.carregarObjeto(map, carregarRelacionamentos);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 *            Map espelhando a tabela correspondente deste objeto
	 * @param carregarRelacionamentos
	 */
	private void carregarObjeto(Map<String, Object> map,
			boolean carregarRelacionamentos) {

		this.id_pergunta = (Integer) map.get("id_pergunta");
		this.usuario.setId_usuario((Integer) map.get("id_usuario"));
		this.descricao = (String) map.get("descricao");
		this.tipo_pergunta = map.get("tipo_pergunta").toString().charAt(0);
		this.nivel_pergunta = map.get("nivel_pergunta").toString().charAt(0);
		this.enunciado = (String) map.get("enunciado");
		this.comentario = (String) map.get("comentario");

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
	 * @param carregarRelacionamentos
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

		List<Pergunta> listPergunta = new ArrayList<Pergunta>();

		for (Map<String, Object> map : listMap) {
			listPergunta.add(new Pergunta(map, carregarRelacionamentos));
		}

		return listPergunta;
	}

	/**
	 * 
	 * @return
	 */
	public List<Pergunta> listarDisciplinas() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Assunto> listarAssuntos() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Prova> listarProvas() {
		// TODO: Pergunta - Desenvolver
		return null;
	}

	/**
	 * @return the id_pergunta
	 */
	public Integer getId_pergunta() {
		return id_pergunta;
	}

	/**
	 * @param id_pergunta
	 *            the id_pergunta to set
	 */
	public void setId_pergunta(Integer id_pergunta) {
		this.id_pergunta = id_pergunta;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the tipo_pergunta
	 */
	public Character getTipo_pergunta() {
		return tipo_pergunta;
	}

	/**
	 * @param tipo_pergunta
	 *            the tipo_pergunta to set
	 */
	public void setTipo_pergunta(Character tipo_pergunta) {
		this.tipo_pergunta = tipo_pergunta;
	}

	/**
	 * @return the nivel_pergunta
	 */
	public Character getNivel_pergunta() {
		return nivel_pergunta;
	}

	/**
	 * @param nivel_pergunta
	 *            the nivel_pergunta to set
	 */
	public void setNivel_pergunta(Character nivel_pergunta) {
		this.nivel_pergunta = nivel_pergunta;
	}

	/**
	 * @return the enunciado
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado
	 *            the enunciado to set
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
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
