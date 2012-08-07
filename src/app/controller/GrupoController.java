package app.controller;

import java.util.List;
import app.dao.postgresdialect.GrupoDAO;
import app.dto.Grupo;

public class GrupoController {
	private GrupoDAO grupoDAO = new GrupoDAO();

	public GrupoController() {

	}

	public boolean adicionar(Grupo grupo) {
		return this.grupoDAO.adicionar(grupo) > 0;
	}

	public boolean alterar(Grupo grupo) {
		return this.grupoDAO.alterar(grupo) > 0;
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
