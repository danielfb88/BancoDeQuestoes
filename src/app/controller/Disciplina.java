package app.controller;
dsadsa
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.DisciplinaDAO;

/**
 * Disciplina
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Disciplina {
	private Integer id_disciplina;
	private Curso curso;
	private String descricao;
	private String sigla;

	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Disciplina() {
		this.curso = new Curso();
	}

	public Disciplina(Integer id_disciplina, Curso curso, String descricao, String sigla) {
		super();
		this.id_disciplina = id_disciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public boolean adicionar() {
		return this.disciplinaDAO.adicionar(curso.getId_curso(), descricao, sigla) > 0;
	}

	/**
	 * 
	 * @param carregarRelacionamentos
	 *            Define se os objetos que fazem relação com este devem ser
	 *            carregados
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> mapDisciplina = this.disciplinaDAO.buscarPorId(this.id_disciplina);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.disciplinaDAO.getPrimaryKey();
		String[] campos = this.disciplinaDAO.getCampos();

		if (mapDisciplina != null) {
			this.id_disciplina = (Integer) mapDisciplina.get(primaryKey[0]);
			this.curso.setId_curso((Integer) mapDisciplina.get(campos[0]));
			this.descricao = (String) mapDisciplina.get(campos[1]);
			this.sigla = (String) mapDisciplina.get(campos[2]);

			// carregando relacionamento
			if (carregarRelacionamentos)
				this.curso.carregar();

			return true;
		}
		return false;
	}

	public boolean editar() {
		return this.disciplinaDAO.editar(id_disciplina, curso.getId_curso(), descricao, sigla) > 0;
	}

	public boolean excluir() {
		return this.disciplinaDAO.excluir(id_disciplina) > 0;
	}

	/**
	 * 
	 * @param carregarRelacionamentos
	 *            Define se os objetos que fazem relação com este devem ser
	 *            carregados
	 * @return
	 */
	public List<Disciplina> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMapDisciplinas = this.disciplinaDAO.listarPor(curso.getId_curso(), descricao, sigla);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.disciplinaDAO.getPrimaryKey();
		String[] campos = this.disciplinaDAO.getCampos();

		// lista de disciplinas
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		// Iterando
		for (Map<String, Object> mapDisciplina : listMapDisciplinas) {
			Disciplina disciplina = new Disciplina();
			
			// preenchendo o objeto disciplina
			disciplina.setId_disciplina((Integer) mapDisciplina.get(primaryKey[0]));
			disciplina.getCurso().setId_curso(
					(Integer) mapDisciplina.get(campos[0]));
			disciplina.setDescricao((String) mapDisciplina.get(campos[1]));
			disciplina.setSigla((String) mapDisciplina.get(campos[2]));

			// carregando relacionamento
			if (carregarRelacionamentos)
				disciplina.getCurso().carregar();

			disciplinas.add(disciplina);
		}
		// retornando a lista
		return disciplinas;
	}

	public boolean inserirAssunto(Assunto assunto) {

		return false;
	}

	public boolean removerAssunto(Assunto assunto) {

		return false;
	}

	public List<Assunto> listarAssuntos() {

		return null;
	}

	public List<Pergunta> listarPerguntas() {

		return null;
	}

	public Integer getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(Integer id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_disciplina == null) ? 0 : id_disciplina.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Disciplina other = (Disciplina) obj;
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
		if (id_disciplina == null) {
			if (other.id_disciplina != null)
				return false;
		} else if (!id_disciplina.equals(other.id_disciplina))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

}
