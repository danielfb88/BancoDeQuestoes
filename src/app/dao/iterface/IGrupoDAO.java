package app.dao.iterface;

import app.dto.Grupo;

public interface IGrupoDAO {
	public int adicionar(Grupo grupo);

	public int alterar(Grupo grupo);

	public int excluir(Grupo grupo);
}
