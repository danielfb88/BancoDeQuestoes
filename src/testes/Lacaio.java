package testes;

import java.util.List;

import dominio.usuario.Grupo;

public class Lacaio {

	public static void main(String[] args) {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Grupo Hibernate 2");
		grupo.setTipo('A');

		List<Grupo> list = grupo.listar();

		for (Grupo g : list) {
			System.out.println("ID: " + g.getId_grupo());
			System.out.println("DESCRICAO: " + g.getDescricao());
			System.out.println("TIPO: " + g.getTipo());
		}

	}

}
