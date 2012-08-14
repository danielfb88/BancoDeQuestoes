package app.dao.iterface;

import java.util.List;

import app.dto.Grupo;

public interface IGrupoDAO {
	public int adicionar(Grupo grupo);

	public int editar(Grupo grupo);

	public int excluir(Grupo grupo);

	public Grupo getById(int id);

	public List<Grupo> getAllBy(Grupo grupo);
}
