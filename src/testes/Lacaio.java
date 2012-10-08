package testes;

import java.util.List;

import dominio.usuario.Grupo;
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
		Grupo grupo = new Grupo();
		grupo.setId_grupo(84);
		Grupo g = grupo.listar().get(0);
				
		Usuario usuario = new Usuario();
		usuario.setGrupo(g);
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
