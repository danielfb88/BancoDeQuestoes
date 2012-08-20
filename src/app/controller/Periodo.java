package app.controller;

import java.util.List;

/**
 * Periodo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class Periodo {
	private Integer id_periodo;
	private String descricao;
	private Integer numero;

	public Periodo() {
		// TODO Auto-generated constructor stub
	}

	public Periodo(Integer id_periodo, String descricao, Integer numero) {
		super();
		this.id_periodo = id_periodo;
		this.descricao = descricao;
		this.numero = numero;
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

	public List<Periodo> listar() {

		return null;
	}

	public Integer getId_periodo() {
		return id_periodo;
	}

	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_periodo == null) ? 0 : id_periodo.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Periodo other = (Periodo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_periodo == null) {
			if (other.id_periodo != null)
				return false;
		} else if (!id_periodo.equals(other.id_periodo))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
