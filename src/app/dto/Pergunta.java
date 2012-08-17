package app.dto;

import java.io.Serializable;

public class Pergunta implements Serializable {

	private static final long serialVersionUID = -9065667475237707844L;
	private Integer id_pergunta;
	private Usuario usuario;
	private String descricao;
	private Character tipo_pergunta;
	private Character nivel_pergunta;
	private String enunciado;
	private String comentario;

	public Pergunta() {
		this.usuario = new Usuario();
	}

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
