package controller;

import java.util.ArrayList;
import java.util.List;

import dao.AssuntoDAO;
import dao.PerguntaDAO;
import dao.Rel_AssuntoPerguntaDAO;

/**
 * Assunto
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Assunto {
	private Integer id_assunto;
	private String descricao;

	private AssuntoDAO assuntoDAO = new AssuntoDAO();
	private PerguntaDAO perguntaDAO = new PerguntaDAO();
	private Rel_AssuntoPerguntaDAO rel_assuntoPerguntaDAO = new Rel_AssuntoPerguntaDAO();

	public Assunto() {

	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	private void preencherDAOComValoresDoObjeto() {
		assuntoDAO.id_assunto = this.id_assunto;
		assuntoDAO.descricao = this.descricao;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	private void preencherObjetoComValoresDoDao() {
		this.id_assunto = assuntoDAO.id_assunto;
		this.descricao = assuntoDAO.descricao;
	}

	/**
	 * Assunto
	 * 
	 * @param id_assunto
	 * @param descricao
	 */
	public Assunto(Integer id_assunto, String descricao) {
		super();
		this.id_assunto = id_assunto;
		this.descricao = descricao;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		assuntoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return assuntoDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		assuntoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		if (assuntoDAO.carregar()) {
			preencherObjetoComValoresDoDao();

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
		assuntoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return assuntoDAO.editar() > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		assuntoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return assuntoDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Assunto> listar() {
		assuntoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		List<Assunto> listAssunto = new ArrayList<Assunto>();
		List<AssuntoDAO> listAssuntoDAO = (List<AssuntoDAO>) assuntoDAO.listar();

		for (AssuntoDAO aDAO : listAssuntoDAO) {
			listAssunto.add(new Assunto(aDAO.id_assunto, aDAO.descricao));
		}

		return listAssunto;
	}

	/**
	 * Inserir Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean inserirPergunta(Pergunta pergunta) {
		rel_assuntoPerguntaDAO.limparAtributos();

		rel_assuntoPerguntaDAO.id_assunto = this.id_assunto;
		rel_assuntoPerguntaDAO.id_pergunta = pergunta.getId_pergunta();

		return rel_assuntoPerguntaDAO.adicionar() > 0;
	}

	/**
	 * Remover Pergunta
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean removerPergunta(Pergunta pergunta) {
		rel_assuntoPerguntaDAO.limparAtributos();

		rel_assuntoPerguntaDAO.id_assunto = this.id_assunto;
		rel_assuntoPerguntaDAO.id_pergunta = pergunta.getId_pergunta();
		rel_assuntoPerguntaDAO.carregar();

		return rel_assuntoPerguntaDAO.excluir() > 0;
	}

	/**
	 * Listar Perguntas
	 * 
	 * @return
	 */
	public List<Pergunta> listarPerguntas(boolean carregarRelacionamentos) {
		perguntaDAO.limparAtributos();

		List<PerguntaDAO> listPerguntaDAO = perguntaDAO.listarPerguntasPorAssunto(this.id_assunto);
		List<Pergunta> listPergunta = new ArrayList<Pergunta>();

		for (PerguntaDAO pDAO : listPerguntaDAO) {
			Pergunta pergunta = new Pergunta();
			pergunta.setId_pergunta(pDAO.id_pergunta);

			if (carregarRelacionamentos) {
				pergunta.carregar(carregarRelacionamentos);
			}

			listPergunta.add(pergunta);
		}

		return listPergunta;
	}

	/**
	 * @return the id_assunto
	 */
	public Integer getId_assunto() {
		return id_assunto;
	}

	/**
	 * @param id_assunto
	 *            the id_assunto to set
	 */
	public void setId_assunto(Integer id_assunto) {
		this.id_assunto = id_assunto;
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
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_assunto == null) ? 0 : id_assunto.hashCode());
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
		Assunto other = (Assunto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_assunto == null) {
			if (other.id_assunto != null)
				return false;
		} else if (!id_assunto.equals(other.id_assunto))
			return false;
		return true;
	}

}
