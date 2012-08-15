package app.dto;

import java.io.Serializable;

public class Curso implements Serializable {

	private static final long serialVersionUID = 6663969871047498631L;
	private Integer id_curso;
	private String descricao;
	private String sigla;
	private String tipo_graduacao;

	public Curso() {

	}

	public Curso(Integer id_curso, String descricao, String sigla,
			String tipo_graduacao) {
		super();
		this.id_curso = id_curso;
		this.descricao = descricao;
		this.sigla = sigla;
		this.tipo_graduacao = tipo_graduacao;
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

}
