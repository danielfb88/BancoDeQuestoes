package app.testes;

import java.util.Date;

import app.dao.CursoDAO;

public class Lacaio {

	public Lacaio() {
		
	}
	
	public static void main(String[] args) {
		Date x=new Date();
		System.out.println(x); //resultado em minha máquina: Fri Jun 05 22:43:25 BRT 2009
		
		String teste = "teste";
		Integer numero = 2;
		Boolean boo = true;
		System.out.println(boo.getClass().getName());
		
		
		
		CursoDAO cursoDAO = new CursoDAO();
		int n = cursoDAO.adicionar("e agora?", "TE", "BAC");
		System.out.println(n);
	}

}
