package app.testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import app.controller.AnoSemestre;
import app.controller.Assunto;
import app.controller.Curso;
import app.controller.Grupo;
import app.controller.Periodo;
import app.dao.CursoDAO;
import app.dao.Rel_AssuntoPerguntaDAO;
import app.util.ConnectionFactory;

public class Lacaio {

	public static void main(String[] args) {
		// id();

		Curso curso = new Curso();
		curso.carregarPorId(28);
		
		System.out.println(curso.getId_curso());
		System.out.println(curso.getDescricao());
		System.out.println(curso.getSigla());
	}

	public static void id() {
		Rel_AssuntoPerguntaDAO rel_ap = new Rel_AssuntoPerguntaDAO();
		System.out.println(rel_ap.getValuePrimaryKey(1, 12));
	}

	private static void adicionarPeriodo() {
		List<Periodo> listPeriodo = new ArrayList<Periodo>();

		listPeriodo.add(new Periodo(null, "Primeiro", 1));
		listPeriodo.add(new Periodo(null, "Segunda", 2));
		listPeriodo.add(new Periodo(null, "Terceiro", 3));
		listPeriodo.add(new Periodo(null, "Quarto", 4));
		listPeriodo.add(new Periodo(null, "Quinto", 5));
		listPeriodo.add(new Periodo(null, "Sexto", 6));
		listPeriodo.add(new Periodo(null, "Sétimo", 7));
		listPeriodo.add(new Periodo(null, "Oitavo", 8));

		/*
		ConnectionFactory.getInstance().iniciarTransacao();
		for (Periodo periodo : listPeriodo) {
			if (periodo.adicionar())
				System.out.println(periodo.getDescricao() + ", "
						+ periodo.getNumero() + " Adicionado com sucesso");
			else
				System.out.println(periodo.getDescricao() + ", "
						+ periodo.getNumero() + " Não foi possivel adicionar");
		}
		ConnectionFactory.getInstance().commit();
		ConnectionFactory.getInstance().finalizarTransacao();
		*/
	}

	private static void adicionarAnoSemestre() {
		List<AnoSemestre> listAS = new ArrayList<AnoSemestre>();

		listAS.add(new AnoSemestre(null, 2010, 1));
		listAS.add(new AnoSemestre(null, 2010, 2));
		listAS.add(new AnoSemestre(null, 2011, 1));
		listAS.add(new AnoSemestre(null, 2011, 2));
		listAS.add(new AnoSemestre(null, 2012, 1));
		listAS.add(new AnoSemestre(null, 2012, 2));

		for (AnoSemestre as : listAS) {
			if (as.adicionar())
				System.out.println(as.getAno() + ", " + as.getSemestre()
						+ " Adicionado com sucesso");
			else
				System.out.println(as.getAno() + ", " + as.getSemestre()
						+ " Não foi possivel adicionar");
		}
	}

	private static void adicionarGrupo() {
		List<Grupo> listGrupo = new ArrayList<Grupo>();

		listGrupo.add(new Grupo(null, "Admin", "A"));
		listGrupo.add(new Grupo(null, "Coord", "C"));

		for (Grupo grupo : listGrupo) {
			if (grupo.adicionar())
				System.out.println(grupo.getDescricao()
						+ "Adicionado com sucesso");
			else
				System.out.println(grupo.getDescricao()
						+ "Não foi possivel adicionar");
		}
	}

	private static void adicionarCursos() {
		List<Curso> listCurso = new ArrayList<Curso>();

		listCurso.add(new Curso(null, "Sistemas de Informação", "SI", "BAC"));
		listCurso.add(new Curso(null, "Psicologia", "PS", "BAC"));
		listCurso.add(new Curso(null, "Ciência da Computação", "CC", "BAC"));
		listCurso.add(new Curso(null, "Engenharia", "ENG", "BAC"));
		listCurso.add(new Curso(null, "Geologia", "GEO", "BAC"));
		listCurso.add(new Curso(null, "Física", "FIS", "BAC"));
		listCurso.add(new Curso(null, "Desenvolvimento de Software", "DS",
				"TEC"));
		listCurso.add(new Curso(null, "Redes", "RD", "TEC"));

		for (Curso curso : listCurso) {
			if (curso.adicionar())
				System.out.println("Curso: " + curso.getDescricao()
						+ "Adicionado com sucesso");
			else
				System.out.println("Curso: " + curso.getDescricao()
						+ "Não foi pssível adicionar o curso "
						+ curso.getDescricao());
		}
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
