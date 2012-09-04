package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.GradeDAO;
import app.dao.Rel_GradePeriodoDAO;

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
	private Rel_GradePeriodoDAO rel_gradePeriodoDAO = new Rel_GradePeriodoDAO();

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
	public Grade(Integer id_grade, Curso curso,
			AnoSemestre anoSemestre_inicial, AnoSemestre anoSemestre_final,
			String descricao) {
		super();
		this.id_grade = id_grade;
		this.curso = curso;
		this.anoSemestre_inicial = anoSemestre_inicial;
		this.anoSemestre_final = anoSemestre_final;
		this.descricao = descricao;
	}

	/***
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public Grade(Map<String, Object> map, boolean carregarRelacionamentos) {
		this.carregarObjeto(map, carregarRelacionamentos);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 *            Map espelhando a tabela correspondente deste objeto
	 * @param map
	 * @param carregarRelacionamentos
	 */
	private void carregarObjeto(Map<String, Object> map,
			boolean carregarRelacionamentos) {

		this.id_grade = (Integer) map.get("id_grade");
		this.curso.setId_curso((Integer) map.get("id_curso"));
		this.anoSemestre_inicial.setId_anoSemestre((Integer) map
				.get("id_anosemestre_inicial"));
		this.anoSemestre_final.setId_anoSemestre((Integer) map
				.get("id_anosemestre_final"));
		this.descricao = (String) map.get("descricao");

		if (carregarRelacionamentos) {
			this.curso.carregar();
			this.anoSemestre_inicial.carregar();
			this.anoSemestre_final.carregar();
		}
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.gradeDAO.adicionar(curso.getId_curso(),
				anoSemestre_inicial.getId_anoSemestre(),
				anoSemestre_final.getId_anoSemestre(), descricao) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.gradeDAO.buscarPorId(this.id_grade);

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
	public boolean editar() {
		return this.gradeDAO.editar(id_grade, curso.getId_curso(),
				anoSemestre_inicial.getId_anoSemestre(),
				anoSemestre_final.getId_anoSemestre(), descricao) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.gradeDAO.excluir(this.id_grade) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Grade> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.gradeDAO.listarPor(
				curso.getId_curso(), anoSemestre_inicial.getId_anoSemestre(),
				anoSemestre_final.getId_anoSemestre(), descricao);

		List<Grade> listGrade = new ArrayList<Grade>();

		for (Map<String, Object> map : listMap) {
			listGrade.add(new Grade(map, carregarRelacionamentos));
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
		return this.rel_gradePeriodoDAO.adicionar(id_grade,
				periodo.getId_periodo()) > 0;
	}

	/**
	 * Remover Periodo
	 * 
	 * @param periodo
	 * @return
	 */
	public boolean removerPeriodo(Periodo periodo) {
		// verificando se a relação existe
		Map<String, Object> map = this.rel_gradePeriodoDAO.listarPor(id_grade,
				periodo.getId_periodo()).get(0);

		if (map != null) {
			int id = (Integer) map.get("id_grade_periodo");
			return this.rel_gradePeriodoDAO.excluir(id) > 0;
		}

		return false;
	}

	/**
	 * Listar Periodos
	 * 
	 * @return
	 */
	public List<Periodo> listarPeriodos() {
		// TODO: Criar Query
		return null;
	}

	/**
	 * Inserir Disciplina
	 * 
	 * @param disciplina
	 * @param periodo
	 * @return
	 */
	public boolean inserirDisciplina(Disciplina disciplina, Periodo periodo) {
		// TODO: DESENVOLVER
		return false;
	}

	/**
	 * Remover Disciplina
	 * 
	 * @param disciplina
	 * @param periodo
	 * @return
	 */
	public boolean removerDisciplina(Disciplina disciplina, Periodo periodo) {
		// TODO: DESENVOLVER
		return false;
	}

	/**
	 * Listar Disciplinas por Periodo
	 * 
	 * @param periodo
	 * @return
	 */
	public List<Disciplina> listarDisciplinas(Periodo periodo) {
		// TODO: CRIAR QUERY
		return null;
	}

	/**
	 * Listar Todas as Disciplinas
	 * 
	 * @return
	 */
	public List<Disciplina> listarTodasAsDisciplinas() {
		// TODO: CRIAR QUERY
		return null;
	}

	/**
	 * Adicionar Prova
	 * 
	 * @param prova
	 * @param periodo
	 * @return
	 */
	public boolean adicionarProva(Prova prova, Periodo periodo) {
		// TODO: DESENVOLVER
		return false;
	}

	/**
	 * Excluir Prova
	 * 
	 * @param prova
	 * @param periodo
	 * @return
	 */
	public boolean excluirProva(Prova prova, Periodo periodo) {
		// TODO: DESENVOLVER
		return false;
	}

	/**
	 * Listar Provas
	 * 
	 * @param periodo
	 * @return
	 */
	public List<Prova> listarProvas(Periodo periodo) {
		// TODO: CRIAR QUERY
		return null;
	}

	/**
	 * Listar Provas
	 * 
	 * @return
	 */
	public List<Prova> listarTodasAsProvas() {
		// TODO: CRIAR QUERY
		return null;
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
