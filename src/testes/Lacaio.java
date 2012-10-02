package testes;

import java.util.List;

import dao.jdbc.AssuntoDAO;
import dao.jdbc.UsuarioDAO;

public class Lacaio {

	public static void main(String[] args) {
		AssuntoDAO aDAO = new AssuntoDAO();
		aDAO.orderByParam = 2;
		@SuppressWarnings("unchecked")
		List<AssuntoDAO> listAssuntoDAO = (List<AssuntoDAO>) aDAO.listar();

		for (AssuntoDAO assuntoDAO : listAssuntoDAO) {
			System.out.println("ID ASSUNTO: " + assuntoDAO.id_assunto);
			System.out.println("DESCRICAO: " + assuntoDAO.descricao);
			System.out.println();
		}

	}

}
