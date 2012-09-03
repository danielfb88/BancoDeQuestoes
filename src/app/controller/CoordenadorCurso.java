package app.controller;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import app.dao.CoordenadorCursoDAO;

/**
 * CoordenadorCurso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */
public class CoordenadorCurso {
	private Integer id_coordenadorCurso;
	private Usuario coordenador = new Usuario();
	private Curso curso = new Curso();
	private Date dataEntrada;
	private Date dataSaida;

	private CoordenadorCursoDAO coordCursoDAO = new CoordenadorCursoDAO();

	public CoordenadorCurso() {

	}

	/**
	 * CoordenadorCurso
	 * 
	 * @param id_coordenadorCurso
	 * @param coordenador
	 * @param curso
	 * @param dataEntrada
	 * @param dataSaida
	 */
	public CoordenadorCurso(Integer id_coordenadorCurso, Usuario coordenador,
			Curso curso, Date dataEntrada, Date dataSaida) {
		super();
		this.id_coordenadorCurso = id_coordenadorCurso;
		this.coordenador = coordenador;
		this.curso = curso;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	/***
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 * @param carregarRelacionamentos
	 */
	public CoordenadorCurso(Map<String, Object> map,
			boolean carregarRelacionamentos) {
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

		this.id_coordenadorCurso = (Integer) map.get("id_coordenador_curso");
		this.coordenador.setId_usuario((Integer) map.get("id_usuario"));
		this.curso.setId_curso((Integer) map.get("id_curso"));
		this.dataEntrada = (Date) map.get("data_entrada");
		this.dataSaida = (Date) map.get("data_saida");

		if (carregarRelacionamentos) {
			this.curso.carregar();
			this.coordenador.carregar(carregarRelacionamentos);
		}
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.coordCursoDAO.adicionar(coordenador.getId_usuario(),
				curso.getId_curso(), dataEntrada, dataSaida) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public boolean carregar(boolean carregarRelacionamentos) {
		Map<String, Object> map = this.coordCursoDAO
				.buscarPorId(this.id_coordenadorCurso);

		if (map != null) {
			this.carregarObjeto(map, carregarRelacionamentos);

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
		return this.coordCursoDAO.editar(id_coordenadorCurso,
				coordenador.getId_usuario(), curso.getId_curso(), dataEntrada,
				dataSaida) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.coordCursoDAO.excluir(id_coordenadorCurso) > 0;
	}

	/**
	 * Listar
	 * 
	 * @param carregarRelacionamentos
	 * @return
	 */
	public List<CoordenadorCurso> listar(boolean carregarRelacionamentos) {
		// buscando a lista de Mapa recuperando pelos parametros
		List<Map<String, Object>> listMap = this.coordCursoDAO.listarPor(
				coordenador.getId_usuario(), curso.getId_curso(), dataEntrada,
				dataSaida);

		List<CoordenadorCurso> listCoordenadorCurso = new ArrayList<CoordenadorCurso>();

		for (Map<String, Object> map : listMap) {
			listCoordenadorCurso.add(new CoordenadorCurso(map,
					carregarRelacionamentos));
		}

		return listCoordenadorCurso;
	}

	/**
	 * @return the id_coordenadorCurso
	 */
	public Integer getId_coordenadorCurso() {
		return id_coordenadorCurso;
	}

	/**
	 * @param id_coordenadorCurso
	 *            the id_coordenadorCurso to set
	 */
	public void setId_coordenadorCurso(Integer id_coordenadorCurso) {
		this.id_coordenadorCurso = id_coordenadorCurso;
	}

	/**
	 * @return the coordenador
	 */
	public Usuario getCoordenador() {
		return coordenador;
	}

	/**
	 * @param coordenador
	 *            the coordenador to set
	 */
	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
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
	 * @return the dataEntrada
	 */
	public Date getDataEntrada() {
		return dataEntrada;
	}

	/**
	 * @param dataEntrada
	 *            the dataEntrada to set
	 */
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	/**
	 * @return the dataSaida
	 */
	public Date getDataSaida() {
		return dataSaida;
	}

	/**
	 * @param dataSaida
	 *            the dataSaida to set
	 */
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordenador == null) ? 0 : coordenador.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result
				+ ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result
				+ ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime
				* result
				+ ((id_coordenadorCurso == null) ? 0 : id_coordenadorCurso
						.hashCode());
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
		CoordenadorCurso other = (CoordenadorCurso) obj;
		if (coordenador == null) {
			if (other.coordenador != null)
				return false;
		} else if (!coordenador.equals(other.coordenador))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (id_coordenadorCurso == null) {
			if (other.id_coordenadorCurso != null)
				return false;
		} else if (!id_coordenadorCurso.equals(other.id_coordenadorCurso))
			return false;
		return true;
	}

}
