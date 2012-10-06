package dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.hibernate.HibernateAbstractDAO;
import dominio.usuario.Grupo;

public class GrupoDAO extends HibernateAbstractDAO {

	/**
	 * Buscar Por Id
	 * 
	 * @param id
	 * @return
	 */
	public Grupo buscarPorId(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_grupo", id);

		return (Grupo) listarPor(Grupo.class, map).get(0);
	}

	/**
	 * Listar Por
	 * 
	 * @return
	 */
	public List<Grupo> listarPor(String descricao, Character tipo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("descricao", descricao);
		map.put("tipo", tipo);

		List<Object> listObj = listarPor(Grupo.class, map);
		List<Grupo> listGrupo = new ArrayList<Grupo>();

		for (Object obj : listObj) {
			listGrupo.add((Grupo) obj);
		}

		return listGrupo;
	}

	/**
	 * Listar Todos os Registros
	 * 
	 * @return
	 */
	public List<Grupo> listarTodos() {
		List<Object> listObj = listarTodos(Grupo.class);
		List<Grupo> listGrupo = new ArrayList<Grupo>();

		for (Object obj : listObj) {
			listGrupo.add((Grupo) obj);
		}

		return listGrupo;
	}
}
