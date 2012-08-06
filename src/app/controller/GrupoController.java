package app.controller;

import java.util.List;
import app.dao.postgresdialect.GrupoDAO;
import app.dto.Grupo;

public class GrupoController {
	private GrupoDAO grupoDAO = new GrupoDAO();

	public GrupoController() {

	}

	public int adicionar(Grupo grupo) {
		return this.grupoDAO.adicionar(grupo);
	}

	public int alterar(Grupo grupo) {
		return this.grupoDAO.alterar(grupo);
	}

	public int excluir(Grupo grupo) {
		return this.grupoDAO.excluir(grupo);
	}

	public Grupo getById(int id) {
		return this.grupoDAO.getById(id);
	}

	public List<Grupo> getAllBy(Grupo grupo) {
		return this.grupoDAO.getAllBy(grupo);
	}

}
