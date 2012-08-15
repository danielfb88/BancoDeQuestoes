package app.bo;

import java.util.List;

import app.dao.iterface.IDisciplinaDAO;
import app.dto.Disciplina;
import app.util.FactoryDAO;

public class DisciplinaBO {
	private IDisciplinaDAO disciplinaDAO;

	public DisciplinaBO() {
		this.disciplinaDAO = FactoryDAO.criarDisciplinaDAO();
	}

	public boolean adicionar(Disciplina disciplina) {
		return this.disciplinaDAO.adicionar(disciplina) > 0;
	}

	public boolean editar(Disciplina disciplina) {
		return this.disciplinaDAO.editar(disciplina) > 0;
	}

	public boolean excluir(Disciplina disciplina) {
		return this.disciplinaDAO.excluir(disciplina) > 0;
	}

	public Disciplina getById(int id) {
		return this.disciplinaDAO.getById(id);
	}

	public List<Disciplina> getAllBy(Disciplina disciplina) {
		return this.disciplinaDAO.getAllBy(disciplina);
	}
}
