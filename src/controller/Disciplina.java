package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.DisciplinaDAO;
import dao.Rel_DisciplinaAssuntoDAO;
import dao.Rel_DisciplinaAssunto_PerguntaDAO;


/**
 * Disciplina
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 03-09-2012
 * 
 */
public class Disciplina {
	private Integer id_disciplina;
	private Curso curso = new Curso();
	private String descricao;
	private String sigla;

	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private Rel_DisciplinaAssuntoDAO rel_disciplinaAssunto = new Rel_DisciplinaAssuntoDAO();
	private Rel_DisciplinaAssunto_PerguntaDAO rel_disciplinaAssunto_pergunta = new Rel_DisciplinaAssunto_PerguntaDAO();

	public Disciplina() {

	}

	/**
	 * Disciplina
	 * 
	 * @param id_disciplina
	 * @param curso
	 * @param descricao
	 * @param sigla
	 */
	public Disciplina(Integer id_disciplina, Curso curso, String descricao,
			String sigla) {
		super();
		this.id_disciplina = id_disciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	/***
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public Disciplina(Map<String, Object> map, boolean carregarRelacionamentos) {
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

		this.id_disciplina = (Integer) map.get("id_disciplina");
		this.curso.setId_curso((Integer) map.get("id_curso"));
		this.descricao = (String) map.get("descricao");
		this.sigla = (String) map.get("sigla");

		if (carregarRelacionamentos)
			this.curso.carregar();
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.disciplinaDAO.adicionar(curso.getId_curso(), descricao,
				sigla) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.disciplinaDAO.buscarPorId(this.id_disciplina);

		if (map != null) {
			this.carregarObjeto(map, carregarRelacionamentos);

			return true;
		}
		return false;
	}

	/**
	 * Carregar por Id
	 * 
	 * @param id
	 * @return
	 */
	public boolean carregarPorId(int id, boolean carregarRelacionamentos) {
		this.id_disciplina = id;
		return this.carregar(carregarRelacionamentos);
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		return this.disciplinaDAO.editar(id_disciplina, curso.getId_curso(),
				descricao, sigla) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.disciplinaDAO.excluir(id_disciplina) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Disciplina> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.disciplinaDAO.listarPor(curso.getId_curso(), descricao, sigla);

		List<Disciplina> list = new ArrayList<Disciplina>();

		for (Map<String, Object> map : listMap) {
			list.add(new Disciplina(map, carregarRelacionamentos));
		}

		return list;
	}

	/**
	 * Inserir Assunto
	 * 
	 * @param assunto
	 * @return
	 */
	public boolean inserirAssunto(Assunto assunto) {
		return this.rel_disciplinaAssunto.adicionar(id_disciplina, assunto.getId_assunto()) > 0;
	}

	/**
	 * Remover Assunto
	 * 
	 * @param assunto
	 * @return
	 */
	public boolean removerAssunto(Assunto assunto) {
		// verificando se a relação existe
		Map<String, Object> map = this.rel_disciplinaAssunto
				.listarPor(id_disciplina, assunto.getId_assunto()).get(0);

		if (map != null) {
			int id = (Integer) map.get("id_disciplina_assunto");
			return this.rel_disciplinaAssunto.excluir(id) > 0;
		}

		return false;
	}

	/**
	 * Listar Assuntos
	 * 
	 * @return
	 */
	public List<Assunto> listarAssuntos() {
		List<Map<String, Object>> listMap = rel_disciplinaAssunto.listarAssuntosPorDisciplina(id_disciplina);

		List<Assunto> listAssunto = new ArrayList<Assunto>();

		for (Map<String, Object> map : listMap) {
			listAssunto.add(new Assunto(map));
		}
		return listAssunto;
	}

	/**
	 * Listar Perguntas
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<Pergunta> listarPerguntas(boolean carregarRelacionamentos) {
		List<Map<String, Object>> listMap = rel_disciplinaAssunto_pergunta
				.listarPerguntasPorDisciplina(id_disciplina);

		List<Pergunta> listPergunta = new ArrayList<Pergunta>();

		for (Map<String, Object> map : listMap) {
			listPergunta.add(new Pergunta(map, carregarRelacionamentos));
		}
		return listPergunta;
	}

	/**
	 * @return the id_disciplina
	 */
	public Integer getId_disciplina() {
		return id_disciplina;
	}

	/**
	 * @param id_disciplina
	 *            the id_disciplina to set
	 */
	public void setId_disciplina(Integer id_disciplina) {
		this.id_disciplina = id_disciplina;
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
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
