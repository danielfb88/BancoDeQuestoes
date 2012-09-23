package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.CoordenadorCursoDAO;

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

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	public void daoRecebeThis() {
		coordCursoDAO.id_coordenador_curso = this.id_coordenadorCurso;
		coordCursoDAO.id_usuario = this.coordenador.getId_usuario();
		coordCursoDAO.id_curso = this.curso.getId_curso();
		coordCursoDAO.data_entrada = this.dataEntrada;
		coordCursoDAO.data_saida = this.dataSaida;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	public void thisRecebeDao() {
		this.id_coordenadorCurso = coordCursoDAO.id_coordenador_curso;
		this.coordenador.setId_usuario(coordCursoDAO.id_usuario);
		this.curso.setId_curso(coordCursoDAO.id_curso);
		this.dataEntrada = coordCursoDAO.data_entrada;
		this.dataSaida = coordCursoDAO.data_saida;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		coordCursoDAO.limparAtributos();
		daoRecebeThis();

		return coordCursoDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		coordCursoDAO.limparAtributos();
		daoRecebeThis();

		if (coordCursoDAO.carregar()) {
			thisRecebeDao();

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
		coordCursoDAO.limparAtributos();
		daoRecebeThis();

		return coordCursoDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		coordCursoDAO.limparAtributos();
		daoRecebeThis();

		return coordCursoDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CoordenadorCurso> listar(boolean carregarRelacionamentos) {
		coordCursoDAO.limparAtributos();
		daoRecebeThis();

		List<CoordenadorCurso> listCoordCurso = new ArrayList<CoordenadorCurso>();
		List<CoordenadorCursoDAO> listCoordCursoDAO = coordCursoDAO.listar();

		for (CoordenadorCursoDAO coordCursoDAO : listCoordCursoDAO) {
			Usuario usuario = new Usuario();
			usuario.setId_usuario(coordCursoDAO.id_usuario);
			usuario.carregar(carregarRelacionamentos);

			Curso curso = new Curso();
			curso.setId_curso(coordCursoDAO.id_curso);
			curso.carregar();

			listCoordCurso.add(
					new CoordenadorCurso(
							coordCursoDAO.id_coordenador_curso, usuario, curso,
							coordCursoDAO.data_entrada, coordCursoDAO.data_saida));
		}

		return listCoordCurso;
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
