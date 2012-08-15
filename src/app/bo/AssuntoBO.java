package app.bo;

import java.util.List;

import app.dao.iterface.IAssuntoDAO;
import app.dto.Assunto;
import app.util.FactoryDAO;

public class AssuntoBO {
	private IAssuntoDAO assuntoDAO;

	public AssuntoBO() {
		this.assuntoDAO = FactoryDAO.criarAssuntoDAO();
	}

	public boolean adicionar(Assunto assunto) {
		return this.assuntoDAO.adicionar(assunto) > 0;
	}

	public boolean editar(Assunto assunto) {
		return this.assuntoDAO.editar(assunto) > 0;
	}

	public boolean excluir(Assunto assunto) {
		return this.assuntoDAO.excluir(assunto) > 0;
	}

	public Assunto getById(int id) {
		return this.assuntoDAO.getById(id);
	}

	public List<Assunto> getAllBy(Assunto assunto) {
		return this.assuntoDAO.getAllBy(assunto);
	}
}
