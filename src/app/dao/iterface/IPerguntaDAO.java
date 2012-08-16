package app.dao.iterface;

import java.util.List;

import app.dto.Pergunta;

public interface IPerguntaDAO {
	public int adicionar(Pergunta pergunta);

	public int editar(Pergunta pergunta);

	public int excluir(Pergunta pergunta);

	public Pergunta getById(int id);

	public List<Pergunta> getAllBy(Pergunta pergunta);
}
