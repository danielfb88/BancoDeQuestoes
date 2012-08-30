package app.controller;

import java.util.List;
import java.util.Map;

import app.dao.GradeDAO;

/**
 * Grade
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Grade {
	private Integer id_grade;
	private Curso curso;
	private AnoSemestre anoSemestre_inicial;
	private AnoSemestre anoSemestre_final;
	private String descricao;

	private GradeDAO gradeDAO = new GradeDAO();

	public Grade() {
		curso = new Curso();
		anoSemestre_inicial = new AnoSemestre();
		anoSemestre_final = new AnoSemestre();
	}

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

	public boolean adicionar() {
		return this.gradeDAO.adicionar(curso.getId_curso(),
				anoSemestre_inicial.getId_anoSemestre(),
				anoSemestre_final.getId_anoSemestre(), descricao) > 0;
	}

	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> mapGrade = this.gradeDAO.buscarPorId(this.id_grade);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.gradeDAO.getPrimaryKey();
		String[] campos = this.gradeDAO.getCampos();

		if (mapGrade != null) {
			this.id_grade = (Integer) mapGrade.get(primaryKey[0]);
			this.curso.setId_curso((Integer) mapGrade.get(campos[0]));
			this.anoSemestre_inicial.setId_anoSemestre((Integer) mapGrade
					.get(campos[1]));
			this.anoSemestre_final.setId_anoSemestre((Integer) mapGrade
					.get(campos[2]));
			this.descricao = (String) mapGrade.get(campos[2]);

			// carregando relacionamento
			if (carregarRelacionamentos) {
				this.curso.carregar();
				this.anoSemestre_inicial.carregar();
				this.anoSemestre_final.carregar();
			}

			return true;
		}
		return false;
	}

	public boolean editar() {

		return false;
	}

	public boolean excluir() {

		return false;
	}

	public List<Curso> listar() {

		return null;
	}

	public boolean inserirPeriodo(Periodo periodo) {

		return false;
	}

	public boolean removerPeriodo(Periodo periodo) {

		return false;
	}

	public List<Periodo> listarPeriodos() {

		return null;
	}

	public boolean inserirDisciplina(Disciplina disciplina, Periodo periodo) {

		return false;
	}

	public boolean removerDisciplina(Disciplina disciplina, Periodo periodo) {

		return false;
	}

	public List<Disciplina> listarDisciplinas(Periodo periodo) {

		return null;
	}

	public List<Disciplina> listarTodasAsDisciplinas() {

		return null;
	}

	public boolean adicionarProva(Prova prova, Periodo periodo) {

		return false;
	}

	public boolean excluirProva(Prova prova, Periodo periodo) {

		return false;
	}

	public List<Prova> listarProvas(Periodo periodo) {

		return null;
	}

	public List<Prova> listarTodasAsProvas() {

		return null;
	}

	public Integer getId_grade() {
		return id_grade;
	}

	public void setId_grade(Integer id_grade) {
		this.id_grade = id_grade;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public AnoSemestre getAnoSemestre_inicial() {
		return anoSemestre_inicial;
	}

	public void setAnoSemestre_inicial(AnoSemestre anoSemestre_inicial) {
		this.anoSemestre_inicial = anoSemestre_inicial;
	}

	public AnoSemestre getAnoSemestre_final() {
		return anoSemestre_final;
	}

	public void setAnoSemestre_final(AnoSemestre anoSemestre_final) {
		this.anoSemestre_final = anoSemestre_final;
	}

	public String getDescricao() {
		return descricao;
	}

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
