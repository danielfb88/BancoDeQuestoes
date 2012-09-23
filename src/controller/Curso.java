package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CursoDAO;

/**
 * Curso
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 02-09-2012
 * 
 */
public class Curso {
	private Integer id_curso;
	private String descricao;
	private String sigla;
	private String tipo_graduacao;

	private CursoDAO cursoDAO = new CursoDAO();

	public Curso() {

	}

	/**
	 * Curso
	 * 
	 * @param id_curso
	 * @param descricao
	 * @param sigla
	 * @param tipo_graduacao
	 */
	public Curso(Integer id_curso, String descricao, String sigla, String tipo_graduacao) {
		super();
		this.id_curso = id_curso;
		this.descricao = descricao;
		this.sigla = sigla;
		this.tipo_graduacao = tipo_graduacao;
	}

	/**
	 * Os atributos da propriedade DAO receberão os valores contidos nos
	 * atributos do objeto (this)
	 */
	public void preencherDAOComValoresDoObjeto() {
		cursoDAO.id_curso = this.id_curso;
		cursoDAO.descricao = this.descricao;
		cursoDAO.sigla = this.sigla;
		cursoDAO.tipo_graduacao = this.tipo_graduacao;
	}

	/**
	 * Os atributos do objeto (this) receberão os valores das propriedades da
	 * classe DAO
	 */
	public void preencherObjetoComValoresDoDAO() {
		this.id_curso = cursoDAO.id_curso;
		this.descricao = cursoDAO.descricao;
		this.sigla = cursoDAO.sigla;
		this.tipo_graduacao = cursoDAO.tipo_graduacao;
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		cursoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return cursoDAO.adicionar() > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		cursoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		if (cursoDAO.carregar()) {
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
		cursoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return cursoDAO.editar() > 0;

	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		cursoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		return cursoDAO.excluir() > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Curso> listar() {
		cursoDAO.limparAtributos();
		preencherDAOComValoresDoObjeto();

		List<Curso> listCurso = new ArrayList<Curso>();
		List<CursoDAO> listCursoDAO = (List<CursoDAO>) cursoDAO.listar();

		for (CursoDAO cDAO : listCursoDAO) {
			listCurso.add(new Curso(cDAO.id_curso, cDAO.descricao, cDAO.sigla, cDAO.tipo_graduacao));
		}

		return listCurso;
	}

	/**
	 * @return the id_curso
	 */
	public Integer getId_curso() {
		return id_curso;
	}

	/**
	 * @param id_curso
	 *            the id_curso to set
	 */
	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the tipo_graduacao
	 */
	public String getTipo_graduacao() {
		return tipo_graduacao;
	}

	/**
	 * @param tipo_graduacao
	 *            the tipo_graduacao to set
	 */
	public void setTipo_graduacao(String tipo_graduacao) {
		this.tipo_graduacao = tipo_graduacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_curso == null) ? 0 : id_curso.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result
				+ ((tipo_graduacao == null) ? 0 : tipo_graduacao.hashCode());
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
		Curso other = (Curso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_curso == null) {
			if (other.id_curso != null)
				return false;
		} else if (!id_curso.equals(other.id_curso))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (tipo_graduacao == null) {
			if (other.tipo_graduacao != null)
				return false;
		} else if (!tipo_graduacao.equals(other.tipo_graduacao))
			return false;
		return true;
	}
}
