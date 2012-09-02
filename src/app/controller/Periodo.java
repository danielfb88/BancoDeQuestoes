package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.PeriodoDAO;

/**
 * Periodo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */
public class Periodo {
	private Integer id_periodo;
	private String descricao;
	private Integer numero;

	private PeriodoDAO periodoDAO = new PeriodoDAO();

	public Periodo() {

	}

	/**
	 * Periodo
	 * 
	 * @param id_periodo
	 * @param descricao
	 * @param numero
	 */
	public Periodo(Integer id_periodo, String descricao, Integer numero) {
		super();
		this.id_periodo = id_periodo;
		this.descricao = descricao;
		this.numero = numero;
	}

	/**
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 */
	public Periodo(Map<String, Object> map) {
		this.carregarObjeto(map);
	}

	/**
	 * Carrega objeto baseado no HashMap de Entrada. As chaves do Map devem ser
	 * iguais ao nome dos campos da tabela.
	 * 
	 * @param map
	 *            Map espelhando a tabela correspondente deste objeto
	 */
	private void carregarObjeto(Map<String, Object> map) {

		this.id_periodo = (Integer) map.get("id_periodo");
		this.descricao = (String) map.get("descricao");
		this.numero = (Integer) map.get("numero");
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.periodoDAO.adicionar(descricao, numero) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> map = this.periodoDAO.buscarPorId(this.id_periodo);

		if (map != null) {
			this.carregarObjeto(map);

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
		return this.periodoDAO.editar(id_periodo, descricao, numero) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.periodoDAO.excluir(id_periodo) > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Periodo> listar() {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.periodoDAO.listarPor(
				descricao, numero);

		List<Periodo> listPeriodo = new ArrayList<Periodo>();

		for (Map<String, Object> map : listMap) {
			listPeriodo.add(new Periodo(map));
		}

		return listPeriodo;
	}

	/**
	 * @return the id_periodo
	 */
	public Integer getId_periodo() {
		return id_periodo;
	}

	/**
	 * @param id_periodo
	 *            the id_periodo to set
	 */
	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
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
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
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
