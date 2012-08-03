package app.dao;
import app.dto.Usuario;

public interface IUsuarioDAO {
	public void salvar(Usuario usuario);
	public void atualizar(Usuario usuario);
	public void deletar(Usuario usuario);
	
}
