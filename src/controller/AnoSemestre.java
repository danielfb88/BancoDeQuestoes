package controller;

import java.util.ArrayList;
import java.util.List;

import dao.AnoSemestreDAO;

/**
 * AnoSemestre
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class AnoSemestre {
	private Integer id_anoSemestre;
	private Integer ano;
	private Integer semestre;

	private AnoSemestreDAO anoSemestreDAO = new AnoSemestreDAO();

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

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		anoSemestreDAO.id_anosemestre = this.id_anoSemestre;
		anoSemestreDAO.ano = this.ano;
		anoSemestreDAO.semestre = this.semestre;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		this.id_anoSemestre = anoSemestreDAO.id_anosemestre;
		this.ano = anoSemestreDAO.ano;
		this.semestre = anoSemestreDAO.semestre;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		anoSemestreDAO.limparAtributos();
		validarDadosParaEntrada();

		return anoSemestreDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		anoSemestreDAO.limparAtributos();
		validarDadosParaEntrada();

		if (anoSemestreDAO.carregar()) {
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
		anoSemestreDAO.limparAtributos();
		validarDadosParaEntrada();

		return anoSemestreDAO.editar() > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		anoSemestreDAO.limparAtributos();
		validarDadosParaEntrada();

		return anoSemestreDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AnoSemestre> listar() {
		anoSemestreDAO.limparAtributos();
		validarDadosParaEntrada();

		List<AnoSemestre> listAnoSemestre = new ArrayList<AnoSemestre>();
		List<AnoSemestreDAO> listAnoSemestreDAO = (List<AnoSemestreDAO>) anoSemestreDAO.listar();

		for (AnoSemestreDAO asDAO : listAnoSemestreDAO) {
			listAnoSemestre.add(new AnoSemestre(asDAO.id_anosemestre, asDAO.ano, asDAO.semestre));
		}

		return listAnoSemestre;
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
