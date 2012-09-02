package app.testes;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import app.controller.Assunto;
import app.controller.Curso;
import app.dao.CursoDAO;

public class Lacaio {

	public Lacaio() {

	}

	public static void main(String[] args) {
		Assunto a = new Assunto();
		a.setId_assunto(6);
		a.carregar();

		System.out.println("****");
		System.out.println(a.getId_assunto());
		System.out.println(a.getDescricao());
		System.out.println("****");

	}

	private static void adicionar() {
		Curso curso = new Curso();
		curso.setDescricao("Psicologia");
		curso.setSigla("PSI");
		curso.setTipo_graduacao("BAC");
		if (curso.adicionar()) {
			System.out.println("Adicionado com sucesso");
		} else {
			System.out.println("Não foi possivel adicionar");
		}
	}

	private static void editar() {
		Curso curso = new Curso();
		curso.setId_curso(20);
		curso.setDescricao("Psicologia Alterado");
		curso.setSigla("BUG");
		curso.setTipo_graduacao("TEC");
		if (curso.editar()) {
			System.out.println("Editar com sucesso");
		} else {
			System.out.println("Não foi possivel editar");
		}
	}

	private static void buscar(Integer id) {
		Curso curso = new Curso();
		curso.setId_curso(id);
		if (curso.carregar()) {
			System.out.println("******");
			System.out.println("ID: " + curso.getId_curso());
			System.out.println("Descricao: " + curso.getDescricao());
			System.out.println("Sigla: " + curso.getSigla());
			System.out.println("Tipo_graduacao: " + curso.getTipo_graduacao());
			System.out.println("*****");
		} else {
			System.out.println("Nao encontrado");
		}
	}

	private static void listar() {
		Curso curso = new Curso();
		// curso.setSigla("B");
		curso.setDescricao("Da");
		curso.setTipo_graduacao("BAC");
		List<Curso> cursos = curso.listar();

		Iterator<Curso> it = cursos.iterator();
		while (it.hasNext()) {
			Curso c = it.next();
			System.out.println("******");
			System.out.println("ID: " + c.getId_curso());
			System.out.println("Descricao: " + c.getDescricao());
			System.out.println("Sigla: " + c.getSigla());
			System.out.println("Tipo_graduacao: " + c.getTipo_graduacao());
			System.out.println("*****");
		}
	}

	private static void excluir(Integer id) {
		Curso curso = new Curso();
		curso.setId_curso(id);
		if (curso.excluir())
			System.out.println("Excluido com sucesos");
		else
			System.out.println("nao foi possivel excluir");

	}

}
