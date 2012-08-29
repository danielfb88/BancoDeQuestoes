package app.controller;
dadasdsa
import java.util.List;

/**
 * Resposta
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Resposta {
	private Integer id_resposta;
	private Pergunta pergunta;
	private String descricao;
	private Boolean correta;
	private String observacao;

	public Resposta() {
		// TODO Auto-generated constructor stub
	}

	public Resposta(Integer id_resposta, Pergunta pergunta, String descricao,
			Boolean correta, String observacao) {
		super();
		this.id_resposta = id_resposta;
		this.pergunta = pergunta;
		this.descricao = descricao;
		this.correta = correta;
		this.observacao = observacao;
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

	public List<Resposta> listar() {

		return null;
	}

	public Integer getId_resposta() {
		return id_resposta;
	}

	public void setId_resposta(Integer id_resposta) {
		this.id_resposta = id_resposta;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	public String getObservacao() {
		return observacao;
	}

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
