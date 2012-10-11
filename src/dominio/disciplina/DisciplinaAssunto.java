package dominio.disciplina;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.DaoFactory;
import dominio.prova.Pergunta;

@Entity
@Table(name = "disciplina_assunto")
public class DisciplinaAssunto implements Serializable {
	private static final long serialVersionUID = 8642487833602402891L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina_assunto")
	private Integer id_disciplina_assunto;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "id_assunto")
	private Assunto assunto;

	@ManyToMany
	@JoinTable(
			name = "disciplina_assunto__pergunta",
			joinColumns = { @JoinColumn(name = "id_disciplina_assunto") },
			inverseJoinColumns = { @JoinColumn(name = "id_pergunta") })
	private List<Pergunta> perguntas;

	public DisciplinaAssunto(Integer id_disciplina_assunto, Disciplina disciplina, Assunto assunto, List<Pergunta> perguntas) {
		super();
		this.id_disciplina_assunto = id_disciplina_assunto;
		this.disciplina = disciplina;
		this.assunto = assunto;
		this.perguntas = perguntas;
	}

	public boolean adicionar() {
		return DaoFactory.getDiscipinaAssuntoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getDiscipinaAssuntoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getDiscipinaAssuntoDAO().excluir(this);
	}

	public List<DisciplinaAssunto> listar() {
		return DaoFactory.getDiscipinaAssuntoDAO().listar(this);
	}

	public List<DisciplinaAssunto> listarTodos() {
		return DaoFactory.getDiscipinaAssuntoDAO().listarTodos();
	}

	public Integer getId_disciplina_assunto() {
		return id_disciplina_assunto;
	}

	public void setId_disciplina_assunto(Integer id_disciplina_assunto) {
		this.id_disciplina_assunto = id_disciplina_assunto;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((id_disciplina_assunto == null) ? 0 : id_disciplina_assunto.hashCode());
		result = prime * result + ((perguntas == null) ? 0 : perguntas.hashCode());
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
		DisciplinaAssunto other = (DisciplinaAssunto) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (id_disciplina_assunto == null) {
			if (other.id_disciplina_assunto != null)
				return false;
		} else if (!id_disciplina_assunto.equals(other.id_disciplina_assunto))
			return false;
		if (perguntas == null) {
			if (other.perguntas != null)
				return false;
		} else if (!perguntas.equals(other.perguntas))
			return false;
		return true;
	}
}
