package app.controller;
dsada
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

	/**
	 * Grupo
	 */
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
		Map<String, Object> mapGrupo = this.grupoDAO.buscarPorId(this.id_grupo);

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.grupoDAO.getPrimaryKey();
		String[] campos = this.grupoDAO.getCampos();

		if (mapGrupo != null) {
			this.id_grupo = (Integer) mapGrupo.get(primaryKey[0]);
			this.descricao = (String) mapGrupo.get(campos[0]);
			this.tipo = mapGrupo.get(campos[1]).toString().charAt(0);

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
		List<Map<String, Object>> listMapGrupo = this.grupoDAO.listarPor(
				descricao, tipo.toString());

		// Pegando o nome da primarykey e dos campos da tabela
		String[] primaryKey = this.grupoDAO.getPrimaryKey();
		String[] campos = this.grupoDAO.getCampos();

		// lista
		List<Grupo> grupos = new ArrayList<Grupo>();

		// Iterando
		for (Map<String, Object> mapGrupo : listMapGrupo) {
			// preenchendo o objeto
			Grupo grupo = new Grupo();
			grupo.setId_grupo((Integer) mapGrupo.get(primaryKey[0]));
			grupo.setDescricao((String) mapGrupo.get(campos[0]));
			grupo.setTipo(mapGrupo.get(campos[1]).toString().charAt(0));

			// inserindo à lista
			grupos.add(grupo);
		}
		// retornando a lista
		return grupos;
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

	public Integer getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getTipo() {
		return tipo;
	}

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
