package app.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import app.dao.CoordenadorCursoDAO;
// TODO: ACredito que esta classe não seja necessária. Apenas a DAO.
/**
 * CoordenadorCurso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 19-08-2012
 * 
 */
public class CoordenadorCurso {
	private Integer id_coordenadorCurso;
	private Usuario coordenador;
	private Curso curso;
	private Date dataEntrada;
	private Date dataSaida;
	
	private CoordenadorCursoDAO coordCursoDAO = new CoordenadorCursoDAO();

	public CoordenadorCurso() {
		this.coordenador = new Usuario();
		this.curso = new Curso();
	}

	public CoordenadorCurso(Integer id_coordenadorCurso, Usuario coordenador,
			Curso curso, Date dataEntrada, Date dataSaida) {
		super();
		this.id_coordenadorCurso = id_coordenadorCurso;
		this.coordenador = coordenador;
		this.curso = curso;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public Integer getId_coordenadorCurso() {
		return id_coordenadorCurso;
	}

	public void setId_coordenadorCurso(Integer id_coordenadorCurso) {
		this.id_coordenadorCurso = id_coordenadorCurso;
	}

	public Usuario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public boolean adicionar() {
		return this.coordCursoDAO.adicionar(coordenador.getId_usuario(),
				curso.getId_curso(), dataEntrada, dataSaida) > 0;
	}

	public boolean carregar() {
		Map<String, Object> mapCC = this.coordenadorCursoDAO.buscarPorId(this.id_coordenadorCurso);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.coordenadorCursoDAO.getPrimaryKey();
		String[] campos = this.coordenadorCursoDAO.getCampos();

		if (mapCC != null) {
			this.id_coordenadorCurso = (Integer) mapCC.get(primaryKey[0]);
			this.coordenador = (String) mapCC.get(campos[0]);
			this.sigla = (String) mapCC.get(campos[1]);
			this.tipo_graduacao = (String) mapCC.get(campos[2]);

			return true;
		}
		return false;
	}

	public boolean editar() {

		return false;
	}

	public boolean excluir() {

		return false;
	}

	public List<CoordenadorCurso> listar() {

		return null;
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
