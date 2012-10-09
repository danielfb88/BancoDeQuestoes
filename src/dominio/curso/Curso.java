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
 * Curso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */

@Entity
@Table(name = "curso")
public class Curso implements Serializable {
	private static final long serialVersionUID = 5248503231307083896L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer id_curso;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@Column(name = "sigla", length = 5, nullable = false)
	private String sigla;

	@Column(name = "tipo_graduacao", length = 3, nullable = false)
	private String tipo_graduacao;

	public Curso() {

	}

	/**
	 * Curso
	 * 
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 */
	public Curso(Integer id_curso, String descricao, String sigla, String tipo_graduacao) {
		super();
		this.id_curso = id_curso;
		this.descricao = descricao;
		this.sigla = sigla;
		this.tipo_graduacao = tipo_graduacao;
	}

	public boolean adicionar() {
		return DaoFactory.getCursoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getCursoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getCursoDAO().excluir(this);
	}

	public List<Curso> listar() {
		return DaoFactory.getCursoDAO().listar(this);
	}

	public List<Curso> listarTodos() {
		return DaoFactory.getCursoDAO().listarTodos();
	}

	/**
	 * @return the id_curso
	 */
	public Integer getId_curso() {
		return id_curso;
	}

	/**
	 * @param id_curso
	 *            the id_curso to set
	 */
	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the tipo_graduacao
	 */
	public String getTipo_graduacao() {
		return tipo_graduacao;
	}

	/**
	 * @param tipo_graduacao
	 *            the tipo_graduacao to set
	 */
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
