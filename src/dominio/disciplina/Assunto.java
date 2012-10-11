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
import javax.persistence.Table;

import dao.DaoFactory;

/**
 * Assunto
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */

@Entity
@Table(name = "assunto")
public class Assunto implements Serializable {
	private static final long serialVersionUID = -5493946407130259208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_assunto")
	private Integer id_assunto;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "disciplina_assunto",
			joinColumns = { @JoinColumn(name = "id_assunto") },
			inverseJoinColumns = { @JoinColumn(name = "id_disciplina") })
	private List<DisciplinaAssunto> disciplinasAssuntos;

	public Assunto() {

	}

	public boolean adicionar() {
		return DaoFactory.getAssuntoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getAssuntoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getAssuntoDAO().excluir(this);
	}

	public List<Assunto> listar() {
		return DaoFactory.getAssuntoDAO().listar(this);
	}

	public List<Assunto> listarTodos() {
		return DaoFactory.getAssuntoDAO().listarTodos();
	}

	/**
	 * @return the id_assunto
	 */
	public Integer getId_assunto() {
		return id_assunto;
	}

	/**
	 * @param id_assunto
	 *            the id_assunto to set
	 */
	public void setId_assunto(Integer id_assunto) {
		this.id_assunto = id_assunto;
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
