package controller;

import java.util.ArrayList;
import java.util.List;

import dao.GrupoDAO;

/**
 * Grupo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Grupo {
	private Integer id_grupo;
	private String descricao;
	private Character tipo;

	private GrupoDAO grupoDAO = new GrupoDAO();

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

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		grupoDAO.id_grupo = id_grupo;
		grupoDAO.descricao = descricao;
		grupoDAO.tipo = tipo;

		boolean retornoOk = grupoDAO.adicionar() > 0;
		grupoDAO.reset();

		return retornoOk;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;

		boolean retornoOk = grupoDAO.carregar();

		this.id_grupo = grupoDAO.id_grupo;
		this.descricao = grupoDAO.descricao;
		this.tipo = grupoDAO.tipo;

		grupoDAO.reset();
		return retornoOk;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;

		boolean retornoOk = grupoDAO.editar() > 0;
		grupoDAO.reset();

		return retornoOk;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		grupoDAO.id_grupo = this.id_grupo;
		boolean retornoOk = grupoDAO.excluir() > 0;
		grupoDAO.reset();
		return retornoOk;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		List<Grupo> listGrupo = new ArrayList<Grupo>();

		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;

		List<GrupoDAO> listDAO = grupoDAO.listar();
		for (GrupoDAO g : listDAO) {
			listGrupo.add(new Grupo(g.id_grupo, g.descricao, g.tipo));
		}

		grupoDAO.reset();
		return listGrupo;
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
