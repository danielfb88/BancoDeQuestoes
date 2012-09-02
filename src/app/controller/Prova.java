package app.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import app.dao.ProvaDAO;
import app.dao.Rel_GradePeriodoDAO;
import app.dao.Rel_GradePeriodo_DisciplinaDAO;

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
		/* TODO: Tratar esta data. É provavel que dê erro aqui */
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

	// TODO: CONTINUAR...
	public boolean carregar() {

		return false;
	}

	public boolean editar() {

		return false;
	}

	public boolean excluir() {

		return false;
	}

	public List<Prova> listar() {

		return null;
	}

	public Integer getId_prova() {
		return id_prova;
	}

	public void setId_prova(Integer id_prova) {
		this.id_prova = id_prova;
	}

	public Integer getId_gradePeriodo() {
		return id_gradePeriodo;
	}

	public void setId_gradePeriodo(Integer id_gradePeriodo) {
		this.id_gradePeriodo = id_gradePeriodo;
	}

	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataProva() {
		return dataProva;
	}

	public void setDataProva(Date dataProva) {
		this.dataProva = dataProva;
	}

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
		result = prime * result
				+ ((id_gradePeriodo == null) ? 0 : id_gradePeriodo.hashCode());
		result = prime * result
				+ ((id_prova == null) ? 0 : id_prova.hashCode());
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
		if (id_gradePeriodo == null) {
			if (other.id_gradePeriodo != null)
				return false;
		} else if (!id_gradePeriodo.equals(other.id_gradePeriodo))
			return false;
		if (id_prova == null) {
			if (other.id_prova != null)
				return false;
		} else if (!id_prova.equals(other.id_prova))
			return false;
		return true;
	}

}
