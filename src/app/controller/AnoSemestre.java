package app.controller;
dadas
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

	/**
	 * AnoSemestre
	 */
	public AnoSemestre() {

	}

	/**
	 * AnoSemestre
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
	 * @return
	 */
	public boolean adicionar() {
		return this.anoSemestreDAO.adicionar(ano, semestre) > 0;
	}

	/**
	 * Carregar
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> mapAnoSemestre = this.anoSemestreDAO
				.buscarPorId(this.id_anoSemestre);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.anoSemestreDAO.getPrimaryKey();
		String[] campos = this.anoSemestreDAO.getCampos();

		if (mapAnoSemestre != null) {
			this.id_anoSemestre = (Integer) mapAnoSemestre.get(primaryKey[0]);
			this.ano = (Integer) mapAnoSemestre.get(campos[0]);
			this.semestre = (Integer) mapAnoSemestre.get(campos[1]);

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * @return
	 */
	public boolean editar() {
		return this.anoSemestreDAO.editar(id_anoSemestre, ano, id_anoSemestre) > 0;
	}

	/**
	 * Excluir
	 * @return
	 */
	public boolean excluir() {
		return this.anoSemestreDAO.editar(id_anoSemestre, ano, id_anoSemestre) > 0;
	}

	/**
	 * Listar
	 * @return
	 */
	public List<AnoSemestre> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMapAnoSemestre = this.anoSemestreDAO
				.listarPor(this.ano, this.semestre);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.anoSemestreDAO.getPrimaryKey();
		String[] campos = this.anoSemestreDAO.getCampos();

		// lista
		List<AnoSemestre> anoSemestres = new ArrayList<AnoSemestre>();

		// Iterando
		for (Map<String, Object> mapAnoSemestre : listMapAnoSemestre) {
			// preenchendo o objeto
			AnoSemestre anoSemestre = new AnoSemestre();
			anoSemestre.setId_anoSemestre((Integer) mapAnoSemestre
					.get(primaryKey[0]));
			anoSemestre.setAno((Integer) mapAnoSemestre.get(campos[0]));
			anoSemestre.setSemestre((Integer) mapAnoSemestre.get(campos[1]));

			// inserindo à lista
			anoSemestres.add(anoSemestre);
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
