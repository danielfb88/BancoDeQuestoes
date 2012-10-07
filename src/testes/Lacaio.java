package testes;

import java.util.List;

import dominio.usuario.Usuario;

public class Lacaio {

	public static void main(String[] args) {
		listar();
	}

	private static void adicionar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Daniel");

		if (usuario.adicionar())
			System.out.println("Adicionado com sucesso");
		else
			System.out.println("Não adicionado");

	}

	private static void listar() {
		Usuario usuario = new Usuario();
		//		usuario.setId_usuario(116);
		usuario.setNome("Daniel");
		//		usuario.setDescricao("Hibernate");
		List<Usuario> list = usuario.listar();

		for (Usuario u : list) {
			System.out.println("ID: " + u.getId_usuario());
			System.out.println("NOME: " + u.getNome());
			System.out.println("GRUPO: " + u.getGrupo().getDescricao());
			System.out.println("LOGIN: " + u.getLogin());
			System.out.println("SENHA: " + u.getSenha());
		}
	}

}
