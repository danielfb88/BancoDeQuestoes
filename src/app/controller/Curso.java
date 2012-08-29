package app.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.CursoDAO;

/**
 * Curso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Curso {
	private Integer id_curso;
	private String descricao;
	private String sigla;
	private String tipo_graduacao;

	private CursoDAO cursoDAO = new CursoDAO();

	public Curso() {

	}

	public Curso(Integer id_curso, String descricao, String sigla,
			String tipo_graduacao) {
		super();
		this.id_curso = id_curso;
		this.descricao = descricao;
		this.sigla = sigla;
		this.tipo_graduacao = tipo_graduacao;
	}

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		return this.cursoDAO.adicionar(descricao, sigla, tipo_graduacao) > 0;
	}

	public boolean carregar() {
		Map<String, Object> mapCurso = this.cursoDAO.buscarPorId(this.id_curso);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.cursoDAO.getPrimaryKey();
		String[] campos = this.cursoDAO.getCampos();

		if (mapCurso != null) {
			this.id_curso = (Integer) mapCurso.get(primaryKey[0]);
			this.descricao = (String) mapCurso.get(campos[0]);
			this.sigla = (String) mapCurso.get(campos[1]);
			this.tipo_graduacao = (String) mapCurso.get(campos[2]);

			return true;
		}
		return false;
	}

	public boolean editar() {
		return this.cursoDAO.editar(id_curso, descricao, sigla, tipo_graduacao) > 0;
	}

	public boolean excluir() {
		return this.cursoDAO.excluir(id_curso) > 0;

	}

	public List<Curso> listar() {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMapCursos = this.cursoDAO.listarPor(
				descricao, sigla, tipo_graduacao);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.cursoDAO.getPrimaryKey();
		String[] campos = this.cursoDAO.getCampos();

		// lista de cursos
		List<Curso> cursos = new ArrayList<Curso>();

		// Iterando
		for (Map<String, Object> mapCurso : listMapCursos) {
			// preenchendo o objeto curso
			Curso curso = new Curso();
			curso.setId_curso((Integer) mapCurso.get(primaryKey[0]));
			curso.setDescricao((String) mapCurso.get(campos[0]));
			curso.setSigla((String) mapCurso.get(campos[1]));
			curso.setTipo_graduacao((String) mapCurso.get(campos[2]));
			// inserindo à lista
			cursos.add(curso);
		}
		// retornando a lista
		return cursos;
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
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
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
