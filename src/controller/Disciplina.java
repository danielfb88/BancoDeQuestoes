package controller;

import java.util.ArrayList;
import java.util.List;

import dao.AssuntoDAO;
import dao.DisciplinaDAO;
import dao.PerguntaDAO;
import dao.Rel_DisciplinaAssuntoDAO;

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
	private AssuntoDAO assuntoDAO = new AssuntoDAO();
	private PerguntaDAO perguntaDAO = new PerguntaDAO();
	private Rel_DisciplinaAssuntoDAO rel_disciplinaAssuntoDAO = new Rel_DisciplinaAssuntoDAO();

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
	public Disciplina(Integer id_disciplina, Curso curso, String descricao, String sigla) {
		super();
		this.id_disciplina = id_disciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	public void preencherDAOComValoresDoObjeto() {
		disciplinaDAO.id_disciplina = this.id_disciplina;
		disciplinaDAO.id_curso = this.getCurso().getId_curso();
		disciplinaDAO.descricao = this.descricao;
		disciplinaDAO.sigla = this.sigla;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	public void preencherObjetoComValoresDoDAO() {
		this.id_disciplina = disciplinaDAO.id_disciplina;
		this.getCurso().setId_curso(disciplinaDAO.id_curso);
		this.descricao = disciplinaDAO.descricao;
		this.sigla = disciplinaDAO.sigla;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		disciplinaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return disciplinaDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		disciplinaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		if (disciplinaDAO.carregar()) {
			preencherObjetoComValoresDoDAO();

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
		disciplinaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return disciplinaDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		disciplinaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return disciplinaDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Disciplina> listar(boolean carregarRelacionamentos) {
		disciplinaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		List<Disciplina> listDisciplina = new ArrayList<Disciplina>();
		List<DisciplinaDAO> listDisciplinaDAO = (List<DisciplinaDAO>) disciplinaDAO.listar();

		for (DisciplinaDAO dDAO : listDisciplinaDAO) {
			Curso curso = new Curso();
			curso.setId_curso(dDAO.id_curso);
			curso.carregar();

			listDisciplina.add(new Disciplina(dDAO.id_disciplina, curso, dDAO.descricao, dDAO.sigla));
		}

		return listDisciplina;
	}

	/**
	 * Inserir Assunto
	 * 
	 * @param assunto
	 * @return
	 */
	public boolean inserirAssunto(Assunto assunto) {
		rel_disciplinaAssuntoDAO.limparAtributos();

		rel_disciplinaAssuntoDAO.id_disciplina = this.id_disciplina;
		rel_disciplinaAssuntoDAO.id_assunto = assunto.getId_assunto();

		return rel_disciplinaAssuntoDAO.adicionar() > 0;
	}

	/**
	 * Remover Assunto
	 * 
	 * @param assunto
	 * @return
	 */
	public boolean removerAssunto(Assunto assunto) {
		rel_disciplinaAssuntoDAO.limparAtributos();

		rel_disciplinaAssuntoDAO.id_disciplina = this.id_disciplina;
		rel_disciplinaAssuntoDAO.id_assunto = assunto.getId_assunto();
		rel_disciplinaAssuntoDAO.carregar();

		return rel_disciplinaAssuntoDAO.excluir() > 0;
	}

	/**
	 * Listar Assuntos
	 * 
	 * @return
	 */
	public List<Assunto> listarAssuntos() {
		assuntoDAO.limparAtributos();

		List<AssuntoDAO> listAssuntoDAO = assuntoDAO.listarAssuntosPorDisciplina(this.id_disciplina);
		List<Assunto> listAssunto = new ArrayList<Assunto>();

		for (AssuntoDAO aDAO : listAssuntoDAO) {
			listAssunto.add(new Assunto(aDAO.id_assunto, aDAO.descricao));
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
		perguntaDAO.limparAtributos();

		List<PerguntaDAO> listPerguntaDAO = perguntaDAO.listarPerguntasPorDisciplina(this.id_disciplina);
		List<Pergunta> listPergunta = new ArrayList<Pergunta>();

		for (PerguntaDAO pDAO : listPerguntaDAO) {
			Usuario usuario = new Usuario();
			usuario.setId_usuario(pDAO.id_usuario);
			usuario.carregar(carregarRelacionamentos);

			listPergunta.add(new Pergunta(pDAO.id_pergunta, usuario, pDAO.descricao, pDAO.tipo_pergunta, pDAO.nivel_pergunta, pDAO.enunciado, pDAO.comentario));
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
