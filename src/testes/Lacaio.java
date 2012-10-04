package testes;

import dao.hibernate.GrupoDAO;
import dominio.usuario.Grupo;

public class Lacaio {

	public static void main(String[] args) {
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = new Grupo();
		grupo.setDescricao("Grupo Hibernate 1");
		grupo.setTipo('A');

		if (grupoDAO.salvar(grupo))
			System.out.println("Adicionado com sucesso");
		else
			System.out.println("Não foi possivel adicionar");

	}

}
