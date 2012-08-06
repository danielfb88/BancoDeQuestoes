package app.testes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import app.dao.postgresDialect.GrupoDAO;
import app.dto.Grupo;

public class Teste {

	public static void main(String[] args) {
		getAllBy();
	}
	
	public static void inserir() {
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = new Grupo();		
		grupo.setDescricao("HOOOO");
		grupo.setTipo(new Character('C'));
		grupoDAO.alterar(grupo);
		System.out.println("Inserido com suceso!");
	}
	
	public static void alterar() {
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = new Grupo();
		grupo.setId_grupo(5);
		grupo.setDescricao("HOOOO_alterado!");
		grupo.setTipo(new Character('A'));
		grupoDAO.alterar(grupo);
		System.out.println("Alterado com suceso!");
	}
	
	public static void excluir() {
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = new Grupo();
		grupo.setId_grupo(22);
		grupo.setDescricao("HOOOO_alterado!");
		grupo.setTipo(new Character('A'));
		grupoDAO.excluir(grupo);
		System.out.println("Excluido com suceso!");
	}
	
	public static void getById(int id) {
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = grupoDAO.getById(id);
		System.out.println("GRUPO:");
		System.out.println("ID: " + grupo.getId_grupo());
		System.out.println("Descricao: " + grupo.getDescricao());
		System.out.println("Tipo: " + grupo.getTipo());
	}
	
	public static void getAllBy() {
		int count = 0;
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = new Grupo();
		grupo.setDescricao("H");
		List<Grupo> grupos = grupoDAO.getAllBy(grupo);
		
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
