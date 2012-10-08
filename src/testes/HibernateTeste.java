package testes;

import java.util.List;

import dominio.usuario.Grupo;
import dominio.usuario.Usuario;

public class HibernateTeste {

	public static void main(String[] args) {
		testeRecuperar();
	}

	private static void testeRecuperar() {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Coord");
		Grupo g = grupo.listar().get(0);

		Usuario usuario = new Usuario();
		List<Usuario> listUsuario = usuario.listarTodos();
		//usuario.setSenha("123");
		//		usuario.setGrupo(g);
		//		List<Usuario> listUsuario = usuario.listar();

		for (Usuario u : listUsuario) {
			System.out.println("ID: " + u.getId_usuario());
			System.out.println("NOME: " + u.getNome());
			System.out.println("GRUPO: " + u.getGrupo().getDescricao());
			System.out.println("LOGIN: " + u.getLogin());
			System.out.println("SENHA: " + u.getSenha());
			System.out.println();
		}
	}
}
