package app.dao.iterface;

import java.util.List;

import app.dto.Disciplina;

public interface IDisciplinaDAO {
	public int adicionar(Disciplina disciplina);

	public int editar(Disciplina disciplina);

	public int excluir(Disciplina disciplina);

	public Disciplina getById(int id);

	public List<Disciplina> getAllBy(Disciplina disciplina);
}
