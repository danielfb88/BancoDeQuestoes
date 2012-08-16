package app.bo;

import java.util.List;

import app.dao.iterface.IPerguntaDAO;
import app.dto.Pergunta;
import app.util.FactoryDAO;

public class PerguntaBO {
	private IPerguntaDAO perguntaDAO;

	public PerguntaBO() {
		this.perguntaDAO = FactoryDAO.criarPerguntaDAO();
	}

	public boolean adicionar(Pergunta pergunta) {
		return this.perguntaDAO.adicionar(pergunta) > 0;
	}

	public boolean editar(Pergunta pergunta) {
		return this.perguntaDAO.editar(pergunta) > 0;
	}

	public boolean excluir(Pergunta pergunta) {
		return this.perguntaDAO.excluir(pergunta) > 0;
	}

	public Pergunta getById(int id) {
		return this.perguntaDAO.getById(id);
	}

	public List<Pergunta> getAllBy(Pergunta pergunta) {
		return this.perguntaDAO.getAllBy(pergunta);
	}

}
