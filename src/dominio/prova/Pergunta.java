package dominio.prova;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dominio.usuario.Usuario;

/**
 * Pergunta
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
@Entity
@Table(name = "pergunta")
public class Pergunta implements Serializable {
	private static final long serialVersionUID = 4304673703182237198L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pergunta")
	private Integer id_pergunta;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "tipo_pergunta")
	private Character tipo_pergunta;

	@Column(name = "nivel_pergunta")
	private Character nivel_pergunta;

	@Column(name = "enunciado")
	private String enunciado;

	@Column(name = "comentario")
	private String comentario;

	@OneToMany(mappedBy = "pergunta", fetch = FetchType.EAGER)
	private List<Resposta> respostas;

	public Pergunta() {

	}

	public Pergunta(Integer id_pergunta, Usuario usuario, String descricao, Character tipo_pergunta, Character nivel_pergunta, String enunciado, String comentario, List<Resposta> respostas) {
		super();
		this.id_pergunta = id_pergunta;
		this.usuario = usuario;
		this.descricao = descricao;
		this.tipo_pergunta = tipo_pergunta;
		this.nivel_pergunta = nivel_pergunta;
		this.enunciado = enunciado;
		this.comentario = comentario;
		this.respostas = respostas;
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

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((id_pergunta == null) ? 0 : id_pergunta.hashCode());
		result = prime * result + ((nivel_pergunta == null) ? 0 : nivel_pergunta.hashCode());
		result = prime * result + ((respostas == null) ? 0 : respostas.hashCode());
		result = prime * result + ((tipo_pergunta == null) ? 0 : tipo_pergunta.hashCode());
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
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
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
