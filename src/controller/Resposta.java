package controller;

import java.util.ArrayList;
import java.util.List;

import dao.RespostaDAO;

/**
 * Resposta
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 01-09-2012
 * 
 */
public class Resposta {
	private Integer id_resposta;
	private Pergunta pergunta = new Pergunta();
	private String descricao;
	private Boolean correta;
	private String observacao;

	private RespostaDAO respostaDAO = new RespostaDAO();

	public Resposta() {

	}

	/**
	 * Resposta
	 * 
	 * @param id_resposta
	 * @param pergunta
	 * @param descricao
	 * @param correta
	 * @param observacao
	 */
	public Resposta(Integer id_resposta, Pergunta pergunta, String descricao,
			Boolean correta, String observacao) {
		super();
		this.id_resposta = id_resposta;
		this.pergunta = pergunta;
		this.descricao = descricao;
		this.correta = correta;
		this.observacao = observacao;
	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	public void preencherDAOComValoresDoObjeto() {
		respostaDAO.id_resposta = this.id_resposta;
		respostaDAO.id_pergunta = this.getPergunta().getId_pergunta();
		respostaDAO.descricao = this.descricao;
		respostaDAO.correta = (this.correta) ? 1 : 0;
		respostaDAO.observacao = this.observacao;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	public void preencherObjetoComValoresDoDAO() {
		this.id_resposta = respostaDAO.id_resposta;
		this.getPergunta().setId_pergunta(respostaDAO.id_pergunta);
		this.descricao = respostaDAO.descricao;
		this.correta = (respostaDAO.correta == 1) ? true : false;
		this.observacao = respostaDAO.observacao;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		respostaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return respostaDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		respostaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		if (respostaDAO.carregar()) {
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
		respostaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return respostaDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		respostaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return respostaDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Resposta> listar(boolean carregarRelacionamentos) {
		respostaDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		List<Resposta> listResposta = new ArrayList<Resposta>();
		List<RespostaDAO> listRespostaDAO = (List<RespostaDAO>) respostaDAO.listar();

		for (RespostaDAO rDAO : listRespostaDAO) {
			Pergunta pergunta = new Pergunta();
			pergunta.setId_pergunta(rDAO.id_pergunta);
			pergunta.carregar(carregarRelacionamentos);

			listResposta.add(new Resposta(rDAO.id_resposta, pergunta, rDAO.descricao,
					((rDAO.correta == 1) ? true : false), rDAO.observacao));
		}

		return listResposta;
	}

	/**
	 * @return the id_resposta
	 */
	public Integer getId_resposta() {
		return id_resposta;
	}

	/**
	 * @param id_resposta
	 *            the id_resposta to set
	 */
	public void setId_resposta(Integer id_resposta) {
		this.id_resposta = id_resposta;
	}

	/**
	 * @return the pergunta
	 */
	public Pergunta getPergunta() {
		return pergunta;
	}

	/**
	 * @param pergunta
	 *            the pergunta to set
	 */
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
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
	 * @return the correta
	 */
	public Boolean getCorreta() {
		return correta;
	}

	/**
	 * @param correta
	 *            the correta to set
	 */
	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao
	 *            the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correta == null) ? 0 : correta.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_resposta == null) ? 0 : id_resposta.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((pergunta == null) ? 0 : pergunta.hashCode());
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
		Resposta other = (Resposta) obj;
		if (correta == null) {
			if (other.correta != null)
				return false;
		} else if (!correta.equals(other.correta))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_resposta == null) {
			if (other.id_resposta != null)
				return false;
		} else if (!id_resposta.equals(other.id_resposta))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pergunta == null) {
			if (other.pergunta != null)
				return false;
		} else if (!pergunta.equals(other.pergunta))
			return false;
		return true;
	}

}
