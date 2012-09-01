package app.controller;

// TODO: VERIFIQUE O QUE PODE SER OTIMIZADO ANTES DE CONTINUAR DESENVOLVENDO OS OUTROS
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

	private String[] anoSemestrePrimaryKey = this.anoSemestreDAO
			.getPrimaryKey();
	private String[] anoSemestreCampos = this.anoSemestreDAO.getCampos();

	/**
	 * AnoSemestre
	 */
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
	 * Cria objeto baseado no HashMap de entrada
	 * 
	 * @param map
	 */
	AnoSemestre novoObjeto(Map<String, Object> map) {

		AnoSemestre as = new AnoSemestre();
		as.setId_anoSemestre((Integer) map.get(anoSemestrePrimaryKey[0]));
		as.setAno((Integer) map.get(anoSemestreCampos[0]));
		as.setSemestre((Integer) map.get(anoSemestreCampos[1]));

		return as;
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada
	 * 
	 * @param map
	 */
	void carregarObjeto(Map<String, Object> map) {

		this.id_anoSemestre = (Integer) map.get(anoSemestrePrimaryKey[0]);
		this.ano = (Integer) map.get(anoSemestreCampos[0]);
		this.semestre = (Integer) map.get(anoSemestreCampos[1]);
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

		// lista
		List<AnoSemestre> anoSemestres = new ArrayList<AnoSemestre>();

		// Iterando
		for (Map<String, Object> map : listMap) {
			// inserindo à lista
			anoSemestres.add(this.novoObjeto(map));
		}
		// retornando a lista
		return anoSemestres;
	}

	public Integer getId_anoSemestre() {
		return id_anoSemestre;
	}

	public void setId_anoSemestre(Integer id_anoSemestre) {
		this.id_anoSemestre = id_anoSemestre;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

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
