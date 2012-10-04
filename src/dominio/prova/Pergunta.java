package dominio.prova;

import java.util.ArrayList;
import java.util.List;

import dao.jdbc.PerguntaDAO;
import dominio.usuario.Usuario;

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
			Character tipo_pergunta, Character nivel_pergunta, String enunciado, String comentario) {
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
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		perguntaDAO.id_pergunta = this.id_pergunta;
		perguntaDAO.id_usuario = this.usuario.getId_usuario();
		perguntaDAO.descricao = this.descricao;
		perguntaDAO.tipo_pergunta = this.tipo_pergunta;
		perguntaDAO.nivel_pergunta = this.nivel_pergunta;
		perguntaDAO.enunciado = this.enunciado;
		perguntaDAO.comentario = this.comentario;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		this.id_pergunta = perguntaDAO.id_pergunta;
		this.usuario.setId_usuario(perguntaDAO.id_usuario);
		this.descricao = perguntaDAO.descricao;
		this.tipo_pergunta = perguntaDAO.tipo_pergunta;
		this.nivel_pergunta = perguntaDAO.nivel_pergunta;
		this.enunciado = perguntaDAO.enunciado;
		this.comentario = perguntaDAO.comentario;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		perguntaDAO.limparAtributos();
		validarDadosParaEntrada();

		return perguntaDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		perguntaDAO.limparAtributos();
		validarDadosParaEntrada();

		if (perguntaDAO.carregar()) {
			validarDadosParaSaida();

			if (carregarRelacionamentos) {
				this.usuario.carregar(carregarRelacionamentos);
			}

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
		perguntaDAO.limparAtributos();
		validarDadosParaEntrada();

		return perguntaDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		perguntaDAO.limparAtributos();
		validarDadosParaEntrada();

		return perguntaDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pergunta> listar(boolean carregarRelacionamentos) {
		perguntaDAO.limparAtributos();
		validarDadosParaEntrada();

		List<Pergunta> listPergunta = new ArrayList<Pergunta>();
		List<PerguntaDAO> listPerguntaDAO = (List<PerguntaDAO>) perguntaDAO.listar();

		for (PerguntaDAO pDAO : listPerguntaDAO) {
			Usuario usuario = new Usuario();
			usuario.setId_usuario(pDAO.id_usuario);

			if (carregarRelacionamentos) {
				usuario.carregar(carregarRelacionamentos);
			}

			listPergunta.add(
					new Pergunta(pDAO.id_pergunta, usuario, pDAO.descricao, pDAO.tipo_pergunta,
							pDAO.nivel_pergunta, pDAO.enunciado, pDAO.comentario));
		}

		return listPergunta;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((id_pergunta == null) ? 0 : id_pergunta.hashCode());
		result = prime * result + ((nivel_pergunta == null) ? 0 : nivel_pergunta.hashCode());
		result = prime * result + ((tipo_pergunta == null) ? 0 : tipo_pergunta.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
