package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 */
	public Curso(Map<String, Object> map) {
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

		this.id_curso = (Integer) map.get("id_curso");
		this.descricao = (String) map.get("descricao");
		this.sigla = (String) map.get("sigla");
		this.tipo_graduacao = (String) map.get("tipo_graduacao");
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.cursoDAO.adicionar(descricao, sigla, tipo_graduacao) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> map = this.cursoDAO.buscarPorId(this.id_curso);

		if (map != null) {
			this.carregarObjeto(map);

			return true;
		}
		return false;
	}

	/**
	 * Carregar por Id
	 * 
	 * @param id
	 * @return
	 */
	public boolean carregarPorId(int id) {
		this.id_curso = id;
		return this.carregar();
	}

	/**
	 * Editar
	 * 
	 * @return
	 */
	public boolean editar() {
		return this.cursoDAO.editar(id_curso, descricao, sigla, tipo_graduacao) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.cursoDAO.excluir(id_curso) > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Curso> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMap = this.cursoDAO.listarPor(descricao, sigla, tipo_graduacao);

		List<Curso> listCurso = new ArrayList<Curso>();

		for (Map<String, Object> map : listMap) {
			listCurso.add(new Curso(map));
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
