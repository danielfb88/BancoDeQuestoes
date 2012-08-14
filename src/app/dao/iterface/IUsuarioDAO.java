package app.dao.iterface;

import java.util.List;

import app.dto.Usuario;

public interface IUsuarioDAO {
	public int adicionar(Usuario usuario);

	public int editar(Usuario usuario);

	public int excluir(Usuario usuario);

	public Usuario getById(int id);

	public List<Usuario> getAllBy(Usuario usuario);
}
