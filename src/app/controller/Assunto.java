package app.controller;

import java.util.List;

public class Assunto {
	private Integer id_assunto;
	private String descricao;

	public Assunto() {
		// TODO Auto-generated constructor stub
	}

	public Assunto(Integer id_assunto, String descricao) {
		super();
		this.id_assunto = id_assunto;
		this.descricao = descricao;
	}

	public boolean adicionar() {

		return false;
	}

	public boolean carregar() {

		return false;
	}

	public boolean editar() {

		return false;
	}

	public boolean excluir() {

		return false;
	}

	public List<Assunto> listar() {

		return null;
	}

	public List<Disciplina> listarDisciplinas() {

		return null;
	}

	public boolean inserirPergunta(Pergunta pergunta) {

		return false;
	}
	
	public boolean removerPergunta(Pergunta pergunta) {
		
		return false;
	}
	
	public List<Pergunta> listarPerguntas() {
		
		return null;
	}

	public Integer getId_assunto() {
		return id_assunto;
	}

	public void setId_assunto(Integer id_assunto) {
		this.id_assunto = id_assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_assunto == null) ? 0 : id_assunto.hashCode());
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
		Assunto other = (Assunto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_assunto == null) {
			if (other.id_assunto != null)
				return false;
		} else if (!id_assunto.equals(other.id_assunto))
			return false;
		return true;
	}

}
