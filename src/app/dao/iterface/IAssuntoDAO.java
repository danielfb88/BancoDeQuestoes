package app.dao.iterface;

import java.util.List;

import app.dto.Assunto;

public interface IAssuntoDAO {
	public int adicionar(Assunto assunto);

	public int editar(Assunto assunto);

	public int excluir(Assunto assunto);

	public Assunto getById(int id);

	public List<Assunto> getAllBy(Assunto assunto);
}
