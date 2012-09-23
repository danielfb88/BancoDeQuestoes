package testes;

import java.util.List;

import controller.Grupo;
import controller.Usuario;

public class Lacaio {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		
		/*
		// adicionando
		usuario.setNome("Kratos");
		usuario.setLogin("ktos");
		usuario.setSenha("12345");
		
		Grupo grupo = new Grupo();
		grupo.setDescricao("Grupo do Kratos");
		grupo.setTipo('A');
		grupo.adicionar();
		grupo.carregar();
		usuario.setGrupo(grupo);
		
		if(usuario.adicionar())
			System.out.println("Usuario Adicionado");
		else
			System.out.println("Usuario NAO Adicionado");
		*/
		
		
		List<Usuario> listUsuario = usuario.listar(true);

		for (Usuario u : listUsuario) {
			System.out.println("ID usuario: " + u.getId_usuario());
			System.out.println("GRUPO: " + u.getGrupo().getDescricao());
			System.out.println("NOME: " + u.getNome());
			System.out.println("LOGIN: " + u.getLogin());
			System.out.println("SENHA: " + u.getSenha());
			System.out.println();
		}

	}

}
