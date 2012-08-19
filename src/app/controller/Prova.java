package app.controller;

import java.sql.Date;
import java.util.List;

public class Prova {
	private Integer id_prova;
	private Integer id_gradePeriodo;
	private AnoSemestre anoSemestre;
	private String descricao;
	private Date dataProva;

	public Prova() {
		// TODO Auto-generated constructor stub
	}

	public Prova(Integer id_prova, Integer id_gradePeriodo,
			AnoSemestre anoSemestre, String descricao, Date dataProva) {
		super();
		this.id_prova = id_prova;
		this.id_gradePeriodo = id_gradePeriodo;
		this.anoSemestre = anoSemestre;
		this.descricao = descricao;
		this.dataProva = dataProva;
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

	public List<Prova> listar() {

		return null;
	}

	public Integer getId_prova() {
		return id_prova;
	}

	public void setId_prova(Integer id_prova) {
		this.id_prova = id_prova;
	}

	public Integer getId_gradePeriodo() {
		return id_gradePeriodo;
	}

	public void setId_gradePeriodo(Integer id_gradePeriodo) {
		this.id_gradePeriodo = id_gradePeriodo;
	}

	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataProva() {
		return dataProva;
	}

	public void setDataProva(Date dataProva) {
		this.dataProva = dataProva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoSemestre == null) ? 0 : anoSemestre.hashCode());
		result = prime * result
				+ ((dataProva == null) ? 0 : dataProva.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_gradePeriodo == null) ? 0 : id_gradePeriodo.hashCode());
		result = prime * result
				+ ((id_prova == null) ? 0 : id_prova.hashCode());
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
		Prova other = (Prova) obj;
		if (anoSemestre == null) {
			if (other.anoSemestre != null)
				return false;
		} else if (!anoSemestre.equals(other.anoSemestre))
			return false;
		if (dataProva == null) {
			if (other.dataProva != null)
				return false;
		} else if (!dataProva.equals(other.dataProva))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_gradePeriodo == null) {
			if (other.id_gradePeriodo != null)
				return false;
		} else if (!id_gradePeriodo.equals(other.id_gradePeriodo))
			return false;
		if (id_prova == null) {
			if (other.id_prova != null)
				return false;
		} else if (!id_prova.equals(other.id_prova))
			return false;
		return true;
	}

}
