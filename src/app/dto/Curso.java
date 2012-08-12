package app.dto;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = -9075097942861836758L;
	private Integer id_curso;
	private String descricao;
	private String sigla;
	private String tipo_graduacao;

	public Curso() {

	}

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTipo_graduacao() {
		return tipo_graduacao;
	}

	public void setTipo_graduacao(String tipo_graduacao) {
		this.tipo_graduacao = tipo_graduacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_curso == null) ? 0 : id_curso.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result
				+ ((tipo_graduacao == null) ? 0 : tipo_graduacao.hashCode());
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
		Curso other = (Curso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_curso == null) {
			if (other.id_curso != null)
				return false;
		} else if (!id_curso.equals(other.id_curso))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (tipo_graduacao == null) {
			if (other.tipo_graduacao != null)
				return false;
		} else if (!tipo_graduacao.equals(other.tipo_graduacao))
			return false;
		return true;
	}

}
