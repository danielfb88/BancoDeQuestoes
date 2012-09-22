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
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		anoSemestreDAO.ano = ano;
		anoSemestreDAO.semestre = semestre;
		boolean retornoOk = anoSemestreDAO.adicionar() > 0;
		anoSemestreDAO.reset();

		return retornoOk;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		anoSemestreDAO.id_anosemestre = id_anoSemestre;
		anoSemestreDAO.ano = ano;
		anoSemestreDAO.semestre = semestre;

		boolean retornoOk = anoSemestreDAO.carregar();
		
		this.id_anoSemestre = anoSemestreDAO.id_anosemestre;
		this.ano = anoSemestreDAO.ano;
		this.semestre = anoSemestreDAO.semestre;
		
		anoSemestreDAO.reset();
		return retornoOk;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		anoSemestreDAO.id_anosemestre = id_anoSemestre;
		anoSemestreDAO.ano = ano;
		anoSemestreDAO.semestre = semestre;
		
		boolean retornoOk = anoSemestreDAO.editar() > 0;
		anoSemestreDAO.reset();
		
		return retornoOk;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		anoSemestreDAO.id_anosemestre = id_anoSemestre;
				
		boolean retornoOk = anoSemestreDAO.excluir() > 0;
		anoSemestreDAO.reset();
		
		return retornoOk;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<AnoSemestre> listar() {
		List<AnoSemestre> listAnoSemestre = new ArrayList<AnoSemestre>();
		
		anoSemestreDAO.id_anosemestre = id_anoSemestre;
		anoSemestreDAO.ano = ano;
		anoSemestreDAO.semestre = semestre;
		
		@SuppressWarnings("unchecked")
		List<AnoSemestreDAO> listDAO = anoSemestreDAO.listar();
		for(AnoSemestreDAO asDAO : listDAO) {
			listAnoSemestre.add(new AnoSemestre(asDAO.id_anosemestre, asDAO.ano, asDAO.semestre));
		}
		
		anoSemestreDAO.reset();
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
