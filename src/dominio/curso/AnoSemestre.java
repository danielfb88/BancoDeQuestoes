package dominio.curso;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.DaoFactory;

/**
 * AnoSemestre
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */

@Entity
@Table(name = "anosemestre")
public class AnoSemestre implements Serializable {
	private static final long serialVersionUID = 2164827965855120352L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anosemestre")
	private Integer id_anoSemestre;

	@Column(name = "ano", nullable = false)
	private Integer ano;

	@Column(name = "semestre", nullable = false)
	private Integer semestre;

	public AnoSemestre() {

	}

	/**
	 * AnoSemestre
	 * 
	 * @param id_anoSemestre
	 * @param ano
	 * @param semestre
	 */
	public AnoSemestre(Integer id_anoSemestre, Integer ano, Integer semestre) {
		super();
		this.id_anoSemestre = id_anoSemestre;
		this.ano = ano;
		this.semestre = semestre;
	}

	public boolean adicionar() {
		return DaoFactory.getAnoSemestreDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getAnoSemestreDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getAnoSemestreDAO().excluir(this);
	}

	public List<AnoSemestre> listar() {
		return DaoFactory.getAnoSemestreDAO().listar(this);
	}

	public List<AnoSemestre> listarTodos() {
		return DaoFactory.getAnoSemestreDAO().listarTodos();
	}

	/**
	 * @return the id_anoSemestre
	 */
	public Integer getId_anoSemestre() {
		return id_anoSemestre;
	}

	/**
	 * @param id_anoSemestre
	 *            the id_anoSemestre to set
	 */
	public void setId_anoSemestre(Integer id_anoSemestre) {
		this.id_anoSemestre = id_anoSemestre;
	}

	/**
	 * @return the ano
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * @param ano
	 *            the ano to set
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * @return the semestre
	 */
	public Integer getSemestre() {
		return semestre;
	}

	/**
	 * @param semestre
	 *            the semestre to set
	 */
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((id_anoSemestre == null) ? 0 : id_anoSemestre.hashCode());
		result = prime * result
				+ ((semestre == null) ? 0 : semestre.hashCode());
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
		AnoSemestre other = (AnoSemestre) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (id_anoSemestre == null) {
			if (other.id_anoSemestre != null)
				return false;
		} else if (!id_anoSemestre.equals(other.id_anoSemestre))
			return false;
		if (semestre == null) {
			if (other.semestre != null)
				return false;
		} else if (!semestre.equals(other.semestre))
			return false;
		return true;
	}

}
