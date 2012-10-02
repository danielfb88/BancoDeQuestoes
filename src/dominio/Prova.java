package dominio;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.jdbc.ProvaDAO;
import dao.jdbc.Rel_GradePeriodoDAO;

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
	private Rel_GradePeriodoDAO rel_gradePeriodoDAO = new Rel_GradePeriodoDAO();

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
	 * Obter Id_grade_periodo
	 * 
	 * @param id_grade
	 * @param id_periodo
	 * @return
	 */
	private Integer obterIdGradePeriodo(Integer id_grade, Integer id_periodo) {
		if (id_grade == null || id_periodo == null)
			return null;

		rel_gradePeriodoDAO.limparAtributos();
		rel_gradePeriodoDAO.id_grade = id_grade;
		rel_gradePeriodoDAO.id_periodo = id_periodo;

		rel_gradePeriodoDAO.carregar();
		return rel_gradePeriodoDAO.id_grade_periodo;
	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		provaDAO.id_prova = this.id_prova;
		provaDAO.id_grade_periodo = obterIdGradePeriodo(grade.getId_grade(), periodo.getId_periodo());
		provaDAO.id_anosemestre = this.anoSemestre.getId_anoSemestre();
		provaDAO.descricao = this.descricao;

		// util.date para sql.date
		provaDAO.data_prova = (this.dataProva != null) ? new java.sql.Date(this.dataProva.getTime()) : null;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		// obtendo o id_grade e id_periodo
		rel_gradePeriodoDAO.limparAtributos();
		rel_gradePeriodoDAO.id_grade_periodo = provaDAO.id_grade_periodo;
		rel_gradePeriodoDAO.carregar();

		this.id_prova = provaDAO.id_prova;
		this.grade.setId_grade(rel_gradePeriodoDAO.id_grade);
		this.periodo.setId_periodo(rel_gradePeriodoDAO.id_periodo);
		this.anoSemestre.setId_anoSemestre(provaDAO.id_anosemestre);
		this.descricao = provaDAO.descricao;
		this.dataProva = provaDAO.data_prova;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		provaDAO.limparAtributos();
		validarDadosParaEntrada();

		return provaDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		provaDAO.limparAtributos();
		validarDadosParaEntrada();

		if (provaDAO.carregar()) {
			validarDadosParaSaida();

			if (carregarRelacionamentos) {
				this.grade.carregar(carregarRelacionamentos);
				this.periodo.carregar();
				this.anoSemestre.carregar();
			}

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
		provaDAO.limparAtributos();
		validarDadosParaEntrada();

		return provaDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		provaDAO.limparAtributos();
		validarDadosParaEntrada();

		return provaDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Prova> listar(boolean carregarRelacionamentos) {
		provaDAO.limparAtributos();
		validarDadosParaEntrada();

		List<Prova> listProva = new ArrayList<Prova>();
		List<ProvaDAO> listProvaDAO = (List<ProvaDAO>) provaDAO.listar();

		for (ProvaDAO pDAO : listProvaDAO) {
			// obtendo o id_grade e id_periodo
			rel_gradePeriodoDAO.limparAtributos();
			rel_gradePeriodoDAO.id_grade_periodo = pDAO.id_grade_periodo;
			rel_gradePeriodoDAO.carregar();

			Grade grade = new Grade();
			grade.setId_grade(rel_gradePeriodoDAO.id_grade);

			Periodo periodo = new Periodo();
			periodo.setId_periodo(rel_gradePeriodoDAO.id_periodo);

			AnoSemestre anoSemestre = new AnoSemestre();
			anoSemestre.setId_anoSemestre(pDAO.id_anosemestre);

			if (carregarRelacionamentos) {
				grade.carregar(carregarRelacionamentos);
				periodo.carregar();
				anoSemestre.carregar();
			}

			listProva.add(new Prova(pDAO.id_prova, grade, periodo, anoSemestre, pDAO.descricao, pDAO.data_prova));
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

	public void setDataProva(String dataProva) {
		if (dataProva != null) {
			try {
				DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				this.dataProva = new Date(fmt.parse(dataProva).getTime());

			} catch (ParseException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		} else {
			dataProva = null;
		}
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
