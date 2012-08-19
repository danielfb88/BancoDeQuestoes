package app.testes;

import java.util.Iterator;
import java.util.List;

import app.bo.UsuarioBO;
import app.controller.GrupoBO;
import app.controller.Usuario;
import app.dao.UsuarioDAO;

public class TesteModuloUsuario {
	public static UsuarioDAO usuarioDAO = new UsuarioDAO();
	public static Usuario usuario = new Usuario();

	public static void main(String[] args) {
		// editarPorLogin("teste");
		getAllBy();
	}

	public static void adicionar() {
		GrupoBO grupoBO = new GrupoBO();
		UsuarioBO usuarioBO = new UsuarioBO();

		usuario.setNome("Daniel");
		usuario.setLogin("teste");
		usuario.setSenha("123456");
		usuario.setGrupo(grupoBO.getById(27));

		usuarioBO.adicionar(usuario);
		System.out.println("Usuario inserido.");
	}

	public static void editarPorLogin(String login) {
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario = new Usuario();
		usuario.setLogin(login);

		List<Usuario> lista = usuarioBO.getAllBy(usuario);

		usuario = lista.iterator().next();
		System.out.println(usuario.getNome());
		usuario.setNome("Editado");
		usuarioDAO.editar(usuario);

		System.out.println("Editado com sucesso!");
	}

	public static void excluir() {

	}

	public static void getByID(Integer id) {

	}

	public static void getAllBy() {
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario = new Usuario();
		usuario.setLogin("dan");

		List<Usuario> lista = usuarioBO.getAllBy(usuario);

		Iterator<Usuario> iterator = lista.iterator();
		while (iterator.hasNext()) {
			Usuario usr = iterator.next();
			System.out.println(usr.getLogin());
		}
	}
}
