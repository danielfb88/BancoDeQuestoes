package dominio.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.DaoFactory;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 6069773659343194922L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo")
	private Integer id_grupo;

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@Column(name = "tipo", nullable = false)
	private Character tipo;

	public Grupo() {

	}

	/**
	 * 
	 * @param id_grupo
	 * @param descricao
	 * @param tipo
	 */
	public Grupo(Integer id_grupo, String descricao, Character tipo) {
		super();
		this.id_grupo = id_grupo;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public boolean adicionar() {
		return DaoFactory.getGrupoDAO().adicionar(this);
	}

	public boolean editar() {
		return DaoFactory.getGrupoDAO().editar(this);
	}

	public boolean excluir() {
		return DaoFactory.getGrupoDAO().excluir(this);
	}

	public Grupo buscarPorId() {
		return DaoFactory.getGrupoDAO().listar(id_grupo, null, null).get(0);
	}

	public List<Grupo> listar() {
		return DaoFactory.getGrupoDAO().listar(null, descricao, tipo);
	}

	public List<Grupo> listarTodos() {
		return DaoFactory.getGrupoDAO().listarTodos();
	}

	/**
	 * @return the id_grupo
	 */
	public Integer getId_grupo() {
		return id_grupo;
	}

	/**
	 * @param id_grupo
	 *            the id_grupo to set
	 */
	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
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
	 * @return the tipo
	 */
	public Character getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_grupo == null) ? 0 : id_grupo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_grupo == null) {
			if (other.id_grupo != null)
				return false;
		} else if (!id_grupo.equals(other.id_grupo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
