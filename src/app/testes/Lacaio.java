package app.testes;

import java.util.Date;

import app.controller.Curso;
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
		
		cursoDAO.listarPor(null, null, "sig", "tip");
		//cursoDAO.adicionar("Daniel", "DAN", "BAC");
		
		//int n = cursoDAO.excluir(7,8);
		//System.exit(0);
		
		//int n = cursoDAO.editar(12, "descricao alterado", "ALT", "BAC");
		
		Curso curso = cursoDAO.buscarPorId(16);		
		if(curso != null) {
			System.out.println("id: "+curso.getId_curso());
			System.out.println("descricao:  "+curso.getDescricao());
			System.out.println("sigla: "+curso.getSigla());
			System.out.println("tipo_graduacao: "+curso.getTipo_graduacao());
		} else {
			System.out.println("Curso nao encontrado");
		}
		
	}

}
