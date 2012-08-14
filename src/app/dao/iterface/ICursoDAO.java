package app.dao.iterface;

import java.util.List;

import app.dto.Curso;

public interface ICursoDAO {
	public int adicionar(Curso curso);

	public int editar(Curso curso);

	public int excluir(Curso curso);

	public Curso getById(int id);

	public List<Curso> getAllBy(Curso curso);
}
