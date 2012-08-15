package app.bo;

import java.util.List;

import app.dao.iterface.ICursoDAO;
import app.dto.Curso;
import app.util.FactoryDAO;

public class CursoBO {
	private ICursoDAO cursoDAO;

	public CursoBO() {
		this.cursoDAO = FactoryDAO.criarCursoDAO();
	}

	public boolean adicionar(Curso curso) {
		return this.cursoDAO.adicionar(curso) > 0;
	}

	public boolean editar(Curso curso) {
		return this.cursoDAO.editar(curso) > 0;
	}

	public boolean excluir(Curso curso) {
		return this.cursoDAO.excluir(curso) > 0;
	}

	public Curso getById(int id) {
		return this.cursoDAO.getById(id);
	}

	public List<Curso> getAllBy(Curso grupo) {
		return this.cursoDAO.getAllBy(grupo);
	}
}
