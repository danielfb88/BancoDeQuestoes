package testes;

import java.util.List;

import dominio.usuario.Grupo;

public class Lacaio {

	public static void main(String[] args) {
		listar();
	}

	private static void adicionar() {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Grupo Hibernate 6");
		grupo.setTipo('A');

		if (grupo.adicionar())
			System.out.println("Adicionado com sucesso");
		else
			System.out.println("Não adicionado");

	}

	private static void listar() {
		Grupo grupo = new Grupo();
//		grupo.setId_grupo(116);
		grupo.setTipo('C');
//		grupo.setDescricao("Hibernate");
		List<Grupo> list = grupo.listar();

		for (Grupo g : list) {
			System.out.println("ID: " + g.getId_grupo());
			System.out.println("DESCRICAO: " + g.getDescricao());
			System.out.println("TIPO: " + g.getTipo());
		}
	}

}
