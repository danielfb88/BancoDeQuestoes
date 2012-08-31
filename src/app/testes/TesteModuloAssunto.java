package app.testes;

import java.util.List;

import app.controller.Assunto;

public class TesteModuloAssunto {
	private static Assunto assunto;

	public static void main(String[] args) {
		//listar();
		buscarPorID(6);
	}

	public static void adicionar() {
		assunto = new Assunto();
		assunto.setDescricao("Controle de Concorrência");
		if (assunto.adicionar())
			System.out.println("Adicionado com sucesso");
		else
			System.out.println("Não foi possível adicionar");
	}

	public static void editar() {
		assunto = new Assunto();
		assunto.setId_assunto(4);
		assunto.setDescricao("Formas Normais");

		if (assunto.editar())
			System.out.println("Editado com sucesso");
		else
			System.out.println("Não foi possivel editar");
	}

	public static void excluir() {
		assunto = new Assunto();
		assunto.setId_assunto(4);
		if (assunto.excluir())
			System.out.println("Excluido com sucesso");
		else
			System.out.println("Erro");
	}

	public static void buscarPorID(Integer id) {
		assunto = new Assunto();
		assunto.setId_assunto(id);
		if (assunto.carregar()) {
			System.out.println("***");
			System.out.println("ID: " + assunto.getId_assunto());
			System.out.println("Descricao: " + assunto.getDescricao());
			System.out.println("***");
		} else {
			System.out.println("Nao encontrado");
		}
	}

	public static void listar() {
		assunto = new Assunto();
		assunto.setDescricao("C");
		List<Assunto> assuntos = assunto.listar();

		if (assuntos != null) {
			for(Assunto assunto : assuntos) {
				System.out.println("***");
				System.out.println("ID: " + assunto.getId_assunto());
				System.out.println("Descricao: " + assunto.getDescricao());
				System.out.println("***");
			}
			
		} else
			System.out.println("Nao encontrado");
	}

}
