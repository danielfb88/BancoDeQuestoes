package dominio;

import java.util.ArrayList;
import java.util.List;

import dao.jdbc.GrupoDAO;

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
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		grupoDAO.id_grupo = this.id_grupo;
		grupoDAO.descricao = this.descricao;
		grupoDAO.tipo = this.tipo;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		this.id_grupo = grupoDAO.id_grupo;
		this.descricao = grupoDAO.descricao;
		this.tipo = grupoDAO.tipo;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		grupoDAO.limparAtributos();
		validarDadosParaEntrada();

		return grupoDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		grupoDAO.limparAtributos();
		validarDadosParaEntrada();

		if (grupoDAO.carregar()) {
			validarDadosParaSaida();

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		grupoDAO.limparAtributos();
		validarDadosParaEntrada();

		return grupoDAO.editar() > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		grupoDAO.limparAtributos();
		validarDadosParaEntrada();

		return grupoDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		grupoDAO.limparAtributos();
		validarDadosParaEntrada();

		List<Grupo> listGrupo = new ArrayList<Grupo>();
		List<GrupoDAO> listGrupoDAO = (List<GrupoDAO>) grupoDAO.listar();

		for (GrupoDAO gDAO : listGrupoDAO) {
			listGrupo.add(new Grupo(gDAO.id_grupo, gDAO.descricao, gDAO.tipo));
		}

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
