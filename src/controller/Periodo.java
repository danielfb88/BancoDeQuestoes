package controller;

import java.util.ArrayList;
import java.util.List;

import dao.jdbc.PeriodoDAO;

/**
 * Periodo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */
public class Periodo {
	private Integer id_periodo;
	private String descricao;
	private Integer numero;

	private PeriodoDAO periodoDAO = new PeriodoDAO();

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

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		periodoDAO.id_periodo = this.id_periodo;
		periodoDAO.descricao = this.descricao;
		periodoDAO.numero = this.numero;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		this.id_periodo = periodoDAO.id_periodo;
		this.descricao = periodoDAO.descricao;
		this.numero = periodoDAO.numero;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		periodoDAO.limparAtributos();
		validarDadosParaEntrada();

		return periodoDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		periodoDAO.limparAtributos();
		validarDadosParaEntrada();

		if (periodoDAO.carregar()) {
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
		periodoDAO.limparAtributos();
		validarDadosParaEntrada();

		return periodoDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		periodoDAO.limparAtributos();
		validarDadosParaEntrada();

		return periodoDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Periodo> listar() {
		periodoDAO.limparAtributos();
		validarDadosParaEntrada();

		List<Periodo> listPeriodo = new ArrayList<Periodo>();
		List<PeriodoDAO> listPeriodoDAO = (List<PeriodoDAO>) periodoDAO.listar();

		for (PeriodoDAO pDAO : listPeriodoDAO) {
			listPeriodo.add(new Periodo(pDAO.id_periodo, pDAO.descricao, pDAO.numero));
		}

		return listPeriodo;
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
