package app.testes;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import app.controller.Curso;
import app.dao.CursoDAO;

public class Lacaio {

	public Lacaio() {

	}

	public static void main(String[] args) {
		Date x = new Date();
		System.out.println(x); // resultado em minha máquina: Fri Jun 05
								// 22:43:25 BRT 2009

		String teste = "teste";
		Integer numero = 2;
		Boolean boo = true;
		System.out.println(boo.getClass().getName());

		CursoDAO cursoDAO = new CursoDAO();

		List<Curso> cursos = cursoDAO.listarPor(null, null, "TEC");

		Iterator<Curso> it = cursos.iterator();
		while (it.hasNext()) {
			Curso curso = it.next();
			System.out.println("******");
			System.out.println("ID: " + curso.getId_curso());
			System.out.println("Descricao: " + curso.getDescricao());
			System.out.println("Sigla: " + curso.getSigla());
			System.out.println("Tipo_graduacao: " + curso.getTipo_graduacao());
			System.out.println("*****");
		}

		System.exit(0);
		// cursoDAO.adicionar("Daniel", "DAN", "BAC");

		// int n = cursoDAO.excluir(7,8);
		// System.exit(0);

		// int n = cursoDAO.editar(12, "descricao alterado", "ALT", "BAC");

		Curso curso = cursoDAO.buscarPorId(16);
		if (curso != null) {
			System.out.println("id: " + curso.getId_curso());
			System.out.println("descricao:  " + curso.getDescricao());
			System.out.println("sigla: " + curso.getSigla());
			System.out.println("tipo_graduacao: " + curso.getTipo_graduacao());
		} else {
			System.out.println("Curso nao encontrado");
		}

	}

}
