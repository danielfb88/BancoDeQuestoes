package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.GrupoDAO;

/**
 * Grupo
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 30-08-2012
 * 
 */
public class Grupo {
	private Integer id_grupo;
	private String descricao;
	private Character tipo;

	private GrupoDAO grupoDAO = new GrupoDAO();

	public Grupo() {

	}

	/**
	 * Grupo
	 * 
	 * @param id_grupo
	 * @param descricao
	 * @param tipo
	 */
	public Grupo(Integer id_grupo, String descricao, Character tipo) {
		super();
		this.id_grupo = id_grupo;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	/**
	 * Constroi e carrega o objeto com um Map que possua suas chaves iguais aos
	 * nomes das colunas do banco, referente a este objeto
	 * 
	 * @param map
	 */
	public Grupo(Map<String, Object> map) {
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

		this.id_grupo = (Integer) map.get("id_grupo");
		this.descricao = (String) map.get("descricao");
		this.tipo = map.get("tipo").toString().charAt(0);
	}

	/**
	 * Adicionar
	 * 
	 * @return
	 */
	public boolean adicionar() {
		return this.grupoDAO.adicionar(descricao, tipo.toString()) > 0;
	}

	/**
	 * Carregar
	 * 
	 * @return
	 */
	public boolean carregar() {
		Map<String, Object> map = this.grupoDAO.buscarPorId(this.id_grupo);

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
		return this.grupoDAO.editar(id_grupo, descricao, tipo.toString()) > 0;
	}

	/**
	 * Excluir
	 * 
	 * @return
	 */
	public boolean excluir() {
		return this.grupoDAO.excluir(id_grupo) > 0;
	}

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Grupo> listar() {
		// buscando a lista de Mapas recuperados pelos parametros
		List<Map<String, Object>> listMap = this.grupoDAO.listarPor(descricao,
				tipo.toString());

		List<Grupo> listGrupo = new ArrayList<Grupo>();

		for (Map<String, Object> map : listMap) {
			listGrupo.add(new Grupo(map));
		}

		return listGrupo;
	}

	/**
	 * Adicionar Usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean adicionarUsuario(Usuario usuario) {
		usuario.setGrupo(this);
		return usuario.adicionar();
	}

	/**
	 * Exclui Usuario deste grupo
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean excluirUsuario(Usuario usuario) {
		// Apenas remove se o usuario for deste grupo
		if (usuario.getGrupo().getId_grupo() == this.id_grupo)
			return usuario.excluir();

		return false;
	}

	/**
	 * Retorna Usuarios deste grupo
	 * 
	 * @return
	 */
	public List<Usuario> listarUsuarios(boolean carregarRelacionamentos) {
		Usuario usuario = new Usuario();
		usuario.setGrupo(this);
		return usuario.listar(carregarRelacionamentos);
	}

	/**
	 * @return the id_grupo
	 */
	public Integer getId_grupo() {
		return id_grupo;
	}

	/**
	 * @param id_grupo
	 *            the id_grupo to set
	 */
	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
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
	 * @return the tipo
	 */
	public Character getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((id_grupo == null) ? 0 : id_grupo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Grupo other = (Grupo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id_grupo == null) {
			if (other.id_grupo != null)
				return false;
		} else if (!id_grupo.equals(other.id_grupo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
