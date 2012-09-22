package testes;

import java.util.List;

import controller.Grupo;
import controller.Pergunta;
import dao.PerguntaDAO;

public class Lacaio {

	public static void main(String[] args) {
		PerguntaDAO pDAO = new PerguntaDAO();
		pDAO.id_pergunta = 19;

		if (pDAO.carregar()) {
			System.out.println("ID: " + pDAO.id_pergunta);
			System.out.println("ID usuario: " + pDAO.id_usuario);
			System.out.println("descricao: " + pDAO.descricao);
			System.out.println("tipo_pergunta: " + pDAO.tipo_pergunta);
			System.out.println("ENUNCIADO: " + pDAO.enunciado);
			System.out.println("COMENTARIO: " + pDAO.comentario);

			// editando
			pDAO.id_pergunta = pDAO.id_pergunta;
			pDAO.id_usuario = pDAO.id_usuario;
			pDAO.descricao = pDAO.descricao;
			pDAO.tipo_pergunta = pDAO.tipo_pergunta;
			pDAO.enunciado = "AAAAAAAAAAAAAAaa uhu!!";
			pDAO.comentario = null;

			if (pDAO.editar() > 0) {
				System.out.println();
				System.out.println("Editado:");
				System.out.println();
				System.out.println("ID: " + pDAO.id_pergunta);
				System.out.println("ID usuario: " + pDAO.id_usuario);
				System.out.println("descricao: " + pDAO.descricao);
				System.out.println("tipo_pergunta: " + pDAO.tipo_pergunta);
				System.out.println("ENUNCIADO: " + pDAO.enunciado);
				System.out.println("COMENTARIO: " + pDAO.comentario);
			} else {
				System.out.println("NAO ROLOU DE NOVO! =(((");
			}
		} else {
			System.out.println("NAO ROLOU =(");
		}
		System.exit(0);

		Grupo grupo = new Grupo();
		grupo.setId_grupo(100);
		grupo.setDescricao("C");
		boolean ok = grupo.editar();

		if (ok) {
			//for (Grupo grupo : listG) {
			System.out.println("ID: " + grupo.getId_grupo());
			System.out.println("DESCRICAO: " + grupo.getDescricao());
			System.out.println("TIPO: " + grupo.getTipo());
			//}
		}
	}

}
