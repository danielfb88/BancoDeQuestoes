package dominio.curso;

import java.sql.Date;
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
import dominio.usuario.Usuario;

/**
 * CoordenadorCurso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */

@Entity
@Table(name = "coordenador_curso")
public class CoordenadorCurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coordenador_curso")
	private Integer id_coordenadorCurso;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario coordenador;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@Column(name = "data_entrada", nullable = false)
	private Date dataEntrada;

	@Column(name = "data_saida", nullable = false)
	private Date dataSaida;

	public CoordenadorCurso() {

	}

	/**
	 * CoordenadorCurso
	 * 
	 * @param id_coordenadorCurso
	 * @param coordenador
	 * @param curso
	 * @param dataEntrada
	 * @param dataSaida
	 */
	public CoordenadorCurso(Integer id_coordenadorCurso, Usuario coordenador, Curso curso,
			Date dataEntrada, Date dataSaida) {
		super();
		this.id_coordenadorCurso = id_coordenadorCurso;
		this.coordenador = coordenador;
		this.curso = curso;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public boolean adicionar() {
		return DaoFactory.getCoordenadorCursoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getCoordenadorCursoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getCoordenadorCursoDAO().excluir(this);
	}

	public List<CoordenadorCurso> listar() {
		return DaoFactory.getCoordenadorCursoDAO().listar(this);
	}

	public List<CoordenadorCurso> listarTodos() {
		return DaoFactory.getCoordenadorCursoDAO().listarTodos();
	}

	/**
	 * @return the id_coordenadorCurso
	 */
	public Integer getId_coordenadorCurso() {
		return id_coordenadorCurso;
	}

	/**
	 * @param id_coordenadorCurso
	 *            the id_coordenadorCurso to set
	 */
	public void setId_coordenadorCurso(Integer id_coordenadorCurso) {
		this.id_coordenadorCurso = id_coordenadorCurso;
	}

	/**
	 * @return the coordenador
	 */
	public Usuario getCoordenador() {
		return coordenador;
	}

	/**
	 * @param coordenador
	 *            the coordenador to set
	 */
	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso
	 *            the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * @return the dataEntrada
	 */
	public Date getDataEntrada() {
		return dataEntrada;
	}

	/**
	 * @param dataEntrada
	 *            the dataEntrada to set
	 */
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	/**
	 * @return the dataSaida
	 */
	public Date getDataSaida() {
		return dataSaida;
	}

	/**
	 * @param dataSaida
	 *            the dataSaida to set
	 */
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordenador == null) ? 0 : coordenador.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result
				+ ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result
				+ ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime
				* result
				+ ((id_coordenadorCurso == null) ? 0 : id_coordenadorCurso
						.hashCode());
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
		CoordenadorCurso other = (CoordenadorCurso) obj;
		if (coordenador == null) {
			if (other.coordenador != null)
				return false;
		} else if (!coordenador.equals(other.coordenador))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (id_coordenadorCurso == null) {
			if (other.id_coordenadorCurso != null)
				return false;
		} else if (!id_coordenadorCurso.equals(other.id_coordenadorCurso))
			return false;
		return true;
	}

}
