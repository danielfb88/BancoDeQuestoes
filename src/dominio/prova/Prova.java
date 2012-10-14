package dominio.prova;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.DaoFactory;
import dominio.curso.AnoSemestre;
import dominio.curso.GradePeriodo;

/**
 * Prova
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */

@Entity
@Table(name = "prova")
public class Prova implements Serializable {
	private static final long serialVersionUID = 8473281736062061901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prova")
	private Integer id_prova;

	@ManyToOne
	@JoinColumn(name = "id_grade_periodo")
	private GradePeriodo gradePeriodo;

	@ManyToOne
	@JoinColumn(name = "id_anosemestre")
	private AnoSemestre anoSemestre;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "data_prova", nullable = false)
	private Date data_prova;

	public Prova() {

	}

	public Prova(Integer id_prova, GradePeriodo gradePeriodo, AnoSemestre anoSemestre, String descricao, Date data_prova) {
		super();
		this.id_prova = id_prova;
		this.gradePeriodo = gradePeriodo;
		this.anoSemestre = anoSemestre;
		this.descricao = descricao;
		this.data_prova = data_prova;
	}

	public boolean adicionar() {
		return DaoFactory.getProvaDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getProvaDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getProvaDAO().excluir(this);
	}

	public List<Prova> listar() {
		return DaoFactory.getProvaDAO().listar(this);
	}

	public List<Prova> listarTodos() {
		return DaoFactory.getProvaDAO().listarTodos();
	}

	public Integer getId_prova() {
		return id_prova;
	}

	public void setId_prova(Integer id_prova) {
		this.id_prova = id_prova;
	}

	public GradePeriodo getGradePeriodo() {
		return gradePeriodo;
	}

	public void setGradePeriodo(GradePeriodo gradePeriodo) {
		this.gradePeriodo = gradePeriodo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_prova() {
		return data_prova;
	}

	public void setData_prova(Date data_prova) {
		this.data_prova = data_prova;
	}

	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoSemestre == null) ? 0 : anoSemestre.hashCode());
		result = prime * result + ((data_prova == null) ? 0 : data_prova.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((gradePeriodo == null) ? 0 : gradePeriodo.hashCode());
		result = prime * result + ((id_prova == null) ? 0 : id_prova.hashCode());
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
		Prova other = (Prova) obj;
		if (anoSemestre == null) {
			if (other.anoSemestre != null)
				return false;
		} else if (!anoSemestre.equals(other.anoSemestre))
			return false;
		if (data_prova == null) {
			if (other.data_prova != null)
				return false;
		} else if (!data_prova.equals(other.data_prova))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (gradePeriodo == null) {
			if (other.gradePeriodo != null)
				return false;
		} else if (!gradePeriodo.equals(other.gradePeriodo))
			return false;
		if (id_prova == null) {
			if (other.id_prova != null)
				return false;
		} else if (!id_prova.equals(other.id_prova))
			return false;
		return true;
	}
}
