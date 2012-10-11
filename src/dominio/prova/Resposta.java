package dominio.prova;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Resposta
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 01-09-2012
 * 
 */

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {
	private static final long serialVersionUID = 5478724949801220287L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resposta")
	private Integer id_resposta;

	@ManyToOne
	@JoinColumn(name = "id_pergunta", nullable = false)
	private Pergunta pergunta;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "correta")
	private Integer correta;

	@Column(name = "observacao")
	private String observacao;

	public Resposta() {

	}

	public Resposta(Integer id_resposta, Pergunta pergunta, String descricao, Integer correta, String observacao) {
		super();
		this.id_resposta = id_resposta;
		this.pergunta = pergunta;
		this.descricao = descricao;
		this.correta = correta;
		this.observacao = observacao;
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

	public Integer getCorreta() {
		return correta;
	}

	public void setCorreta(Integer correta) {
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id_resposta == null) ? 0 : id_resposta.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pergunta == null) ? 0 : pergunta.hashCode());
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
