package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.AnoSemestreDAO;

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
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * @param map
	 */
	public AnoSemestre(Map<String, Object> map) {
		this.carregarObjeto(map);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 */
	private void carregarObjeto(Map<String, Object> map) {

		this.id_anoSemestre = (Integer) map.get("id_anosemestre");
		this.ano = (Integer) map.get("ano");
		this.semestre = (Integer) map.get("semestre");
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.anoSemestreDAO.adicionar(ano, semestre) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> map = this.anoSemestreDAO
				.buscarPorId(this.id_anoSemestre);

		if (map != null) {
			this.carregarObjeto(map);

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
		return this.anoSemestreDAO.editar(id_anoSemestre, ano, id_anoSemestre) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.anoSemestreDAO.editar(id_anoSemestre, ano, id_anoSemestre) > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<AnoSemestre> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMap = this.anoSemestreDAO.listarPor(
				this.ano, this.semestre);

		List<AnoSemestre> listAnoSemestre = new ArrayList<AnoSemestre>();

		for (Map<String, Object> map : listMap) {
			listAnoSemestre.add(new AnoSemestre(map));
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
