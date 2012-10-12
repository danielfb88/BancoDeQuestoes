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
 * Periodo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */

@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {
	private static final long serialVersionUID = -4687682981054029782L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_periodo")
	private Integer id_periodo;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	public Periodo() {

	}

	/**
	 * Periodo
	 * 
	 * @param id_periodo
	 * @param descricao
	 * @param numero
	 */
	public Periodo(Integer id_periodo, String descricao, Integer numero) {
		super();
		this.id_periodo = id_periodo;
		this.descricao = descricao;
		this.numero = numero;
	}

	public boolean adicionar() {
		return DaoFactory.getPeriodoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getPeriodoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getPeriodoDAO().excluir(this);
	}

	public List<Periodo> listar() {
		return DaoFactory.getPeriodoDAO().listar(this);
	}

	public List<Periodo> listarTodos() {
		return DaoFactory.getPeriodoDAO().listarTodos();
	}

	/**
	 * @return the id_periodo
	 */
	public Integer getId_periodo() {
		return id_periodo;
	}

	/**
	 * @param id_periodo
	 *            the id_periodo to set
	 */
	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
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
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_periodo == null) ? 0 : id_periodo.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Periodo other = (Periodo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_periodo == null) {
			if (other.id_periodo != null)
				return false;
		} else if (!id_periodo.equals(other.id_periodo))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
