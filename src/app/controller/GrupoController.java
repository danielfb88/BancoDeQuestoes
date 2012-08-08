package app.controller;

import java.util.List;

import app.dao.iterface.IGrupoDAO;
import app.dto.Grupo;
import app.util.FactoryDAO;

public class GrupoController {
	private IGrupoDAO grupoDAO;

	public GrupoController() {
		this.grupoDAO = FactoryDAO.criarGrupoDAO();
	}

	public boolean adicionar(Grupo grupo) {
		return this.grupoDAO.adicionar(grupo) > 0;
	}

	public boolean editar(Grupo grupo) {
		return this.grupoDAO.editar(grupo) > 0;
	}

	public boolean excluir(Grupo grupo) {
		return this.grupoDAO.excluir(grupo) > 0;
	}

	public Grupo getById(int id) {
		return this.grupoDAO.getById(id);
	}

	public List<Grupo> getAllBy(Grupo grupo) {
		return this.grupoDAO.getAllBy(grupo);
	}

}
