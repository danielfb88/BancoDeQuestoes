package app.dao.iterface;

import app.dto.Usuario;

public interface IUsuarioDAO {
	public int adicionar(Usuario usuario);

	public int alterar(Usuario usuario);

	public int excluir(Usuario usuario);

}
