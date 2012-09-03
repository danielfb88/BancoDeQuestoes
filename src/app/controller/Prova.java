package app.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.ProvaDAO;
import app.dao.Rel_GradePeriodoDAO;

/**
 * Prova
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */
public class Prova {
	private Integer id_prova;
	private Grade grade = new Grade();
	private Periodo periodo = new Periodo();
	private AnoSemestre anoSemestre = new AnoSemestre();
	private String descricao;
	private Date dataProva;

	private ProvaDAO provaDAO = new ProvaDAO();
	private Rel_GradePeriodoDAO rel_gp = new Rel_GradePeriodoDAO();

	public Prova() {

	}

	/**
	 * Prova
	 * 
	 * @param id_prova
	 * @param grade
	 * @param periodo
	 * @param anoSemestre
	 * @param descricao
	 * @param dataProva
	 */
	public Prova(Integer id_prova, Grade grade, Periodo periodo,
			AnoSemestre anoSemestre, String descricao, Date dataProva) {
		super();
		this.id_prova = id_prova;
		this.grade = grade;
		this.periodo = periodo;
		this.anoSemestre = anoSemestre;
		this.descricao = descricao;
		this.dataProva = dataProva;
	}

	/**
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public Prova(Map<String, Object> map, boolean carregarRelacionamentos) {
		this.carregarObjeto(map, carregarRelacionamentos);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 *            Map espelhando a tabela correspondente deste objeto
	 * @param carregarRelacionamentos
	 */
	private void carregarObjeto(Map<String, Object> map,
			boolean carregarRelacionamentos) {

		// buscando ID grade e ID periodo
		Map<String, Object> mapGradePeriodo = this.rel_gp
				.buscarPorId((Integer) map.get("id_grade_periodo"));

		this.id_prova = (Integer) map.get("id_prova");
		this.grade.setId_grade((Integer) mapGradePeriodo.get("id_grade"));
		this.periodo.setId_periodo((Integer) map.get("id_periodo"));
		this.anoSemestre.setId_anoSemestre((Integer) map.get("id_anosemestre"));
		this.descricao = (String) map.get("descricao");
		this.dataProva = (Date) map.get("data_prova");

		if (carregarRelacionamentos) {
			this.grade.carregar(carregarRelacionamentos);
			this.periodo.carregar();
			this.anoSemestre.carregar();
		}
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() throws Exception {
		// primeiro busca o id_grade_periodo para inserir no DAO
		Map<String, Object> mapGradePeriodo = this.rel_gp.listarPor(
				this.grade.getId_grade(), this.periodo.getId_periodo()).get(0);

		if (mapGradePeriodo != null) {
			return this.provaDAO.adicionar(
					(Integer) mapGradePeriodo.get("id_grade_periodo"),
					this.anoSemestre.getId_anoSemestre(), descricao,
					this.dataProva) > 0;
		} else {
			throw new Exception("A relação GradePeriodo id_grade="
					+ this.grade.getId_grade() + " e id_periodo="
					+ this.periodo.getId_periodo() + " não existe.");
		}
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.provaDAO.buscarPorId(this.id_prova);

		if (map != null) {
			this.carregarObjeto(map, carregarRelacionamentos);

			return true;
		}
		return false;
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() throws Exception {
		// primeiro busca o id_grade_periodo para inserir no DAO
		Map<String, Object> mapGradePeriodo = this.rel_gp.listarPor(
				this.grade.getId_grade(), this.periodo.getId_periodo()).get(0);

		if (mapGradePeriodo != null) {
			return this.provaDAO.editar(id_prova,
					(Integer) mapGradePeriodo.get("id_grade_periodo"),
					this.anoSemestre.getId_anoSemestre(), descricao, dataProva) > 0;
		} else {
			throw new Exception("A relação GradePeriodo id_grade="
					+ this.grade.getId_grade() + " e id_periodo="
					+ this.periodo.getId_periodo() + " não existe.");
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.provaDAO.excluir(id_prova) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Prova> listar(boolean carregarRelacionamentos) {
		// primeiro busca o id_grade_periodo para inserir no DAO
		Map<String, Object> mapGradePeriodo = this.rel_gp.listarPor(
				this.grade.getId_grade(), this.periodo.getId_periodo()).get(0);

		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.provaDAO.listarPor(
				(Integer) mapGradePeriodo.get("id_grade_periodo"),
				anoSemestre.getId_anoSemestre(), descricao, dataProva);

		List<Prova> listProva = new ArrayList<Prova>();

		for (Map<String, Object> map : listMap) {
			listProva.add(new Prova(map, carregarRelacionamentos));
		}

		return listProva;
	}

	/**
	 * @return the id_prova
	 */
	public Integer getId_prova() {
		return id_prova;
	}

	/**
	 * @param id_prova
	 *            the id_prova to set
	 */
	public void setId_prova(Integer id_prova) {
		this.id_prova = id_prova;
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * @return the periodo
	 */
	public Periodo getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the anoSemestre
	 */
	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	/**
	 * @param anoSemestre
	 *            the anoSemestre to set
	 */
	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
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
	 * @return the dataProva
	 */
	public Date getDataProva() {
		return dataProva;
	}

	/**
	 * @param dataProva
	 *            the dataProva to set
	 */
	public void setDataProva(Date dataProva) {
		this.dataProva = dataProva;
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
				+ ((anoSemestre == null) ? 0 : anoSemestre.hashCode());
		result = prime * result
				+ ((dataProva == null) ? 0 : dataProva.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result
				+ ((id_prova == null) ? 0 : id_prova.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
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
		Prova other = (Prova) obj;
		if (anoSemestre == null) {
			if (other.anoSemestre != null)
				return false;
		} else if (!anoSemestre.equals(other.anoSemestre))
			return false;
		if (dataProva == null) {
			if (other.dataProva != null)
				return false;
		} else if (!dataProva.equals(other.dataProva))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id_prova == null) {
			if (other.id_prova != null)
				return false;
		} else if (!id_prova.equals(other.id_prova))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		return true;
	}

}
