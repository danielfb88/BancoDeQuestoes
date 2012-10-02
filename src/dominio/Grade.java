package dominio;

import java.util.ArrayList;
import java.util.List;

import dao.jdbc.DisciplinaDAO;
import dao.jdbc.GradeDAO;
import dao.jdbc.PeriodoDAO;
import dao.jdbc.Rel_GradePeriodoDAO;
import dao.jdbc.Rel_GradePeriodo_DisciplinaDAO;

/**
 * Grade
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-09-2012
 * 
 */
public class Grade {
	private Integer id_grade;
	private Curso curso = new Curso();
	private AnoSemestre anoSemestre_inicial = new AnoSemestre();
	private AnoSemestre anoSemestre_final = new AnoSemestre();
	private String descricao;

	private GradeDAO gradeDAO = new GradeDAO();
	private PeriodoDAO periodoDAO = new PeriodoDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private Rel_GradePeriodoDAO rel_gradePeriodoDAO = new Rel_GradePeriodoDAO();
	private Rel_GradePeriodo_DisciplinaDAO rel_gradePeriodo_disciplinaDAO = new Rel_GradePeriodo_DisciplinaDAO();

	public Grade() {

	}

	/**
	 * Grade
	 * 
	 * @param id_grade
	 * @param curso
	 * @param anoSemestre_inicial
	 * @param anoSemestre_final
	 * @param descricao
	 */
	public Grade(Integer id_grade, Curso curso, AnoSemestre anoSemestre_inicial, AnoSemestre anoSemestre_final,
			String descricao) {
		super();
		this.id_grade = id_grade;
		this.curso = curso;
		this.anoSemestre_inicial = anoSemestre_inicial;
		this.anoSemestre_final = anoSemestre_final;
		this.descricao = descricao;
	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void validarDadosParaEntrada() {
		gradeDAO.id_grade = this.id_grade;
		gradeDAO.id_curso = this.getCurso().getId_curso();
		gradeDAO.id_anosemestre_inicial = this.getAnoSemestre_inicial().getId_anoSemestre();
		gradeDAO.id_anosemestre_final = this.getAnoSemestre_final().getId_anoSemestre();
		gradeDAO.descricao = this.descricao;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void validarDadosParaSaida() {
		this.id_grade = gradeDAO.id_grade;
		this.getCurso().setId_curso(gradeDAO.id_curso);
		this.getAnoSemestre_inicial().setId_anoSemestre(gradeDAO.id_anosemestre_inicial);
		this.getAnoSemestre_final().setId_anoSemestre(gradeDAO.id_anosemestre_final);
		this.descricao = gradeDAO.descricao;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		gradeDAO.limparAtributos();
		validarDadosParaEntrada();

		return gradeDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		gradeDAO.limparAtributos();
		validarDadosParaEntrada();

		if (gradeDAO.carregar()) {
			validarDadosParaSaida();

			if (carregarRelacionamentos) {
				this.curso.carregar();
				this.anoSemestre_inicial.carregar();
				this.anoSemestre_final.carregar();
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
		gradeDAO.limparAtributos();
		validarDadosParaEntrada();

		return gradeDAO.editar() > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		gradeDAO.limparAtributos();
		validarDadosParaEntrada();

		return gradeDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Grade> listar(boolean carregarRelacionamentos) {
		gradeDAO.limparAtributos();
		validarDadosParaEntrada();

		List<GradeDAO> listGradeDAO = (List<GradeDAO>) gradeDAO.listar();
		List<Grade> listGrade = new ArrayList<Grade>();

		for (GradeDAO gDAO : listGradeDAO) {
			Curso curso = new Curso();
			curso.setId_curso(gDAO.id_curso);

			AnoSemestre anoSemestreInicial = new AnoSemestre();
			anoSemestreInicial.setId_anoSemestre(gDAO.id_anosemestre_inicial);

			AnoSemestre anoSemestreFinal = new AnoSemestre();
			anoSemestreFinal.setId_anoSemestre(gDAO.id_anosemestre_final);

			if (carregarRelacionamentos) {
				curso.carregar();
				anoSemestreInicial.carregar();
				anoSemestreFinal.carregar();
			}

			listGrade.add(new Grade(gDAO.id_grade, curso, anoSemestreInicial, anoSemestreFinal, gDAO.descricao));
		}

		return listGrade;
	}

	/**
	 * Inserir Periodo
	 * 
	 * @param periodo
	 * @return
	 */
	public boolean inserirPeriodo(Periodo periodo) {
		rel_gradePeriodoDAO.limparAtributos();

		rel_gradePeriodoDAO.id_grade = this.id_grade;
		rel_gradePeriodoDAO.id_periodo = periodo.getId_periodo();

		return rel_gradePeriodoDAO.adicionar() > 0;
	}

	/**
	 * Remover Periodo
	 * 
	 * @param periodo
	 * @return
	 */
	public boolean removerPeriodo(Periodo periodo) {
		rel_gradePeriodoDAO.limparAtributos();

		rel_gradePeriodoDAO.id_grade = this.id_grade;
		rel_gradePeriodoDAO.id_periodo = periodo.getId_periodo();
		rel_gradePeriodoDAO.carregar();

		return rel_gradePeriodoDAO.excluir() > 0;
	}

	/**
	 * Listar Periodos
	 * 
	 * @param id_grade
	 * @return
	 */
	public List<Periodo> listarPeriodos() {
		periodoDAO.limparAtributos();

		List<PeriodoDAO> listPeriodoDAO = periodoDAO.listarPeriodosPorGrade(id_grade);
		List<Periodo> listPeriodo = new ArrayList<Periodo>();

		for (PeriodoDAO pDAO : listPeriodoDAO) {
			listPeriodo.add(new Periodo(pDAO.id_periodo, pDAO.descricao, pDAO.numero));
		}

		return listPeriodo;
	}

	/**
	 * Inserir Disciplina
	 * 
	 * @param disciplina
	 *            Disciplina a ser inserida
	 * @param periodo
	 *            Periodo
	 * @return
	 */
	public boolean inserirDisciplina(Periodo periodo, Disciplina disciplina) {
		rel_gradePeriodoDAO.limparAtributos();
		rel_gradePeriodo_disciplinaDAO.limparAtributos();

		// pegando o id_grade_periodo
		rel_gradePeriodoDAO.id_grade = this.id_grade;
		rel_gradePeriodoDAO.id_periodo = periodo.getId_periodo();

		// se encontrar use o id_grade_periodo para compor o campo e inserir
		if (rel_gradePeriodoDAO.carregar()) {
			rel_gradePeriodo_disciplinaDAO.id_grade_periodo = rel_gradePeriodoDAO.id_grade_periodo;
			rel_gradePeriodo_disciplinaDAO.id_disciplina = disciplina.getId_disciplina();

			return rel_gradePeriodo_disciplinaDAO.adicionar() > 0;
		}

		return false;
	}

	/**
	 * Remover Disciplina
	 * 
	 * @param disciplina
	 * @param periodo
	 * @return
	 */
	public boolean removerDisciplina(Periodo periodo, Disciplina disciplina) {
		disciplinaDAO.limparAtributos();
		return disciplinaDAO.removerDisciplina(this.id_grade, periodo.getId_periodo(), disciplina.getId_disciplina());
	}

	/**
	 * Listar Disciplinas por Periodo
	 * 
	 * @param periodo
	 * @return
	 */
	public List<Disciplina> listarDisciplinas(Periodo periodo, boolean carregarRelacionamentos) {
		disciplinaDAO.limparAtributos();

		List<DisciplinaDAO> listDisciplinaDAO = disciplinaDAO.listarDisciplinasPorGradePeriodo(
				this.id_grade, periodo.getId_periodo());
		List<Disciplina> listDisciplina = new ArrayList<Disciplina>();

		for (DisciplinaDAO dDAO : listDisciplinaDAO) {
			Curso curso = new Curso();
			curso.setId_curso(dDAO.id_curso);

			if (carregarRelacionamentos) {
				curso.carregar();
			}

			listDisciplina.add(new Disciplina(dDAO.id_disciplina, curso, dDAO.descricao, dDAO.sigla));
		}

		return listDisciplina;
	}

	/**
	 * Listar Todas as Disciplinas
	 * 
	 * @return
	 */
	public List<Disciplina> listarTodasAsDisciplinas(boolean carregarRelacionamentos) {
		disciplinaDAO.limparAtributos();

		List<DisciplinaDAO> listDisciplinaDAO = disciplinaDAO.listarDisciplinasPorGrade(this.id_grade);
		List<Disciplina> listDisciplina = new ArrayList<Disciplina>();

		for (DisciplinaDAO dDAO : listDisciplinaDAO) {
			Curso curso = new Curso();
			curso.setId_curso(dDAO.id_curso);

			if (carregarRelacionamentos) {
				curso.carregar();
			}

			listDisciplina.add(new Disciplina(dDAO.id_disciplina, curso, dDAO.descricao, dDAO.sigla));
		}

		return listDisciplina;
	}

	/**
	 * @return the id_grade
	 */
	public Integer getId_grade() {
		return id_grade;
	}

	/**
	 * @param id_grade
	 *            the id_grade to set
	 */
	public void setId_grade(Integer id_grade) {
		this.id_grade = id_grade;
	}

	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso
	 *            the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * @return the anoSemestre_inicial
	 */
	public AnoSemestre getAnoSemestre_inicial() {
		return anoSemestre_inicial;
	}

	/**
	 * @param anoSemestre_inicial
	 *            the anoSemestre_inicial to set
	 */
	public void setAnoSemestre_inicial(AnoSemestre anoSemestre_inicial) {
		this.anoSemestre_inicial = anoSemestre_inicial;
	}

	/**
	 * @return the anoSemestre_final
	 */
	public AnoSemestre getAnoSemestre_final() {
		return anoSemestre_final;
	}

	/**
	 * @param anoSemestre_final
	 *            the anoSemestre_final to set
	 */
	public void setAnoSemestre_final(AnoSemestre anoSemestre_final) {
		this.anoSemestre_final = anoSemestre_final;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((anoSemestre_final == null) ? 0 : anoSemestre_final
						.hashCode());
		result = prime
				* result
				+ ((anoSemestre_inicial == null) ? 0 : anoSemestre_inicial
						.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_grade == null) ? 0 : id_grade.hashCode());
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
		Grade other = (Grade) obj;
		if (anoSemestre_final == null) {
			if (other.anoSemestre_final != null)
				return false;
		} else if (!anoSemestre_final.equals(other.anoSemestre_final))
			return false;
		if (anoSemestre_inicial == null) {
			if (other.anoSemestre_inicial != null)
				return false;
		} else if (!anoSemestre_inicial.equals(other.anoSemestre_inicial))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_grade == null) {
			if (other.id_grade != null)
				return false;
		} else if (!id_grade.equals(other.id_grade))
			return false;
		return true;
	}

}
