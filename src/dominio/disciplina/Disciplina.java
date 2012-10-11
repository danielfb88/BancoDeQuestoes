package dominio.disciplina;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dominio.curso.Curso;

/**
 * Disciplina
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-09-2012
 * 
 */

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = -8669694122536688649L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina")
	private Integer id_disciplina;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso = new Curso();

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "sigla")
	private String sigla;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "disciplina_assunto",
			joinColumns = { @JoinColumn(name = "id_disciplina") },
			inverseJoinColumns = { @JoinColumn(name = "id_assunto") })
	private List<DisciplinaAssunto> disciplinasAssuntos;

	public Disciplina() {

	}

	public Disciplina(Integer id_disciplina, Curso curso, String descricao, String sigla,
			List<DisciplinaAssunto> disciplinasAssuntos) {
		super();
		this.id_disciplina = id_disciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.sigla = sigla;
		this.disciplinasAssuntos = disciplinasAssuntos;
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

	public List<DisciplinaAssunto> getDisciplinasAssuntos() {
		return disciplinasAssuntos;
	}

	public void setDisciplinasAssuntos(List<DisciplinaAssunto> disciplinasAssuntos) {
		this.disciplinasAssuntos = disciplinasAssuntos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((disciplinasAssuntos == null) ? 0 : disciplinasAssuntos.hashCode());
		result = prime * result + ((id_disciplina == null) ? 0 : id_disciplina.hashCode());
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
		if (disciplinasAssuntos == null) {
			if (other.disciplinasAssuntos != null)
				return false;
		} else if (!disciplinasAssuntos.equals(other.disciplinasAssuntos))
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
