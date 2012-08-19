package app.testes;

import java.util.Iterator;
import java.util.List;

import app.controller.Grupo;

public class TesteModuloGrupo {

	public static void main(String[] args) {
		buscarTodos();
	}
	
	public static void inserir() {
		Grupo grupo = new Grupo();		
		grupo.setDescricao("OKKKK");
		grupo.setTipo(new Character('C'));
		grupo.adicionar();
		System.out.println("Inserido com suceso!");
	}
	
	public static void carregar() {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(83);
		grupo.carregar();
		System.out.print("A descrição é "+ grupo.getDescricao());
	}
	
	public static void alterar() {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(83);
		grupo.carregar();
		
		grupo.setDescricao(grupo.getDescricao()+" Alterado");
		grupo.editar();
		System.out.println("Alterado com suceso!");
	}
	
	public static void excluir() {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(83);
		grupo.excluir();
		System.out.println("Excluido com suceso!");
	}
	
	public static void buscarTodos() {
		Grupo grupo = new Grupo();
		grupo.setTipo('A');
		List<Grupo> grupos = grupo.listar();
		
		int count = 0;
		
		Iterator<Grupo> iterator = grupos.iterator();
		while (iterator.hasNext()) {
			Grupo grup = iterator.next();
			System.out.println("GRUPO "+ ++count +":");
			System.out.println("ID: " + grup.getId_grupo());
			System.out.println("Descricao: " + grup.getDescricao());
			System.out.println("Tipo: " + grup.getTipo());
			System.out.println();
		}
		
	}
	
	

}
