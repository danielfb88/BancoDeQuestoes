package dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.usuario.Grupo;

import util.hibernate.HibernateAbstractDAO;

public class GrupoDAO extends HibernateAbstractDAO<Grupo> {

	/**
	 * Listar
	 * 
	 * @return
	 */
	public List<Grupo> listar(Integer id_grupo, String descricao, Character tipo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_grupo", id_grupo);
		map.put("descricao", descricao);
		map.put("tipo", tipo);

		return super.listar(Grupo.class, map);
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Grupo> listarTodos() {
		return super.listarTodos(Grupo.class);
	}

}
