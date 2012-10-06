package dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.hibernate.HibernateAbstractDAO;
import dominio.usuario.Grupo;

public class GrupoDAO extends HibernateAbstractDAO {

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

		return fazerCast(super.listar(Grupo.class, map));
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Grupo> listarTodos() {
		return fazerCast(super.listarTodos(Grupo.class));
	}

	/**
	 * Faz cast de uma lista de Object para uma lista do objeto correspondente.
	 * 
	 * @param listObj
	 * @return
	 */
	private List<Grupo> fazerCast(List<Object> listObj) {
		List<Grupo> list = new ArrayList<Grupo>();

		for (Object obj : listObj) {
			list.add((Grupo) obj);
		}

		return list;
	}
}
