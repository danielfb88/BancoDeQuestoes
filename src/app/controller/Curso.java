package app.controller;

import java.sql.Date;
import java.util.List;

public class Curso {
	private Integer id_curso;
	private String sigla;
	private String tipo_graduacao;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(Integer id_curso, String sigla, String tipo_graduacao) {
		super();
		this.id_curso = id_curso;
		this.sigla = sigla;
		this.tipo_graduacao = tipo_graduacao;
	}

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTipo_graduacao() {
		return tipo_graduacao;
	}

	public void setTipo_graduacao(String tipo_graduacao) {
		this.tipo_graduacao = tipo_graduacao;
	}

	public boolean adicionar() {

		return false;
	}

	public boolean carregar() {

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

	public boolean definirCoordenadorAtual(Usuario usuario) {

		return false;
	}

	public boolean excluirCoordenadorAtual() {

		return false;
	}

	public Usuario buscarCoordenadorAtual() {

		return null;
	}

	public List<Usuario> listarTodosOsCoordenadores() {

		return null;
	}

	public List<Usuario> listarCoordenadoresEntre(Date dataInicio, Date dataFim) {

		return null;
	}

	public boolean inserirDisciplina(Disciplina disciplina) {

		return false;
	}

	public boolean removerDisciplina(Disciplina disciplina) {

		return false;
	}

	public List<Disciplina> listarDisciplinas() {

		return null;
	}

	public boolean definirGradeAtual(Grade grade) {

		return false;
	}

	public boolean excluirGradeAtual() {

		return false;
	}

	public Grade buscarGradeAtual() {

		return null;
	}

	public List<Grade> listarTodasAsGrades() {

		return null;
	}

	public List<Grade> listarGradesEntre(AnoSemestre anoSemestreInicio,
			AnoSemestre anoSemestreFim) {

		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_curso == null) ? 0 : id_curso.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result
				+ ((tipo_graduacao == null) ? 0 : tipo_graduacao.hashCode());
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
		Curso other = (Curso) obj;
		if (id_curso == null) {
			if (other.id_curso != null)
				return false;
		} else if (!id_curso.equals(other.id_curso))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (tipo_graduacao == null) {
			if (other.tipo_graduacao != null)
				return false;
		} else if (!tipo_graduacao.equals(other.tipo_graduacao))
			return false;
		return true;
	}

}
