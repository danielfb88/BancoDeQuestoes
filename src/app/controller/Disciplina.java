package app.controller;
a
import java.util.List;

/**
 * Disciplina
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Disciplina {
	private Integer id_disciplina;
	private Curso curso;
	private String descricao;
	private String sigla;

	public Disciplina() {
		// TODO Auto-generated constructor stub
	}

	public Disciplina(Integer id_disciplina, Curso curso, String descricao,
			String sigla) {
		super();
		this.id_disciplina = id_disciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.sigla = sigla;
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

	public List<Disciplina> listar() {

		return null;
	}
	
	public boolean inserirAssunto(Assunto assunto) {
		
		return false;
	}
	
	public boolean removerAssunto(Assunto assunto) {
		
		return false;
	}
	
	public List<Assunto> listarAssuntos() {
		
		return null;
	}
	
	public List<Pergunta> listarPerguntas() {
		
		return null;
	}

	public Integer getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(Integer id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_disciplina == null) ? 0 : id_disciplina.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_disciplina == null) {
			if (other.id_disciplina != null)
				return false;
		} else if (!id_disciplina.equals(other.id_disciplina))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

}
