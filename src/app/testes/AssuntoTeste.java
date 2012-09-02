package app.testes;

import java.util.List;
import java.util.Map;

import app.dao.AssuntoDAO;
import app.dao.Rel_AssuntoPerguntaDAO;
import app.dao.UsuarioDAO;

public class AssuntoTeste {

	public static void main(String[] args) {

		// editar();
		// buscarPerguntaPorAssunto(1);
		buscarAssuntoPorPergunta(2);
	}

	public static void adicionar() {
		// OK
		Rel_AssuntoPerguntaDAO rel_ap = new Rel_AssuntoPerguntaDAO();
		if (rel_ap.adicionar(1, 8) > 0)
			System.out.println("Adicionado");
		else
			System.out.println("Não Adicionado");
	}

	public static void editar() {
		Rel_AssuntoPerguntaDAO rel_ap = new Rel_AssuntoPerguntaDAO();
		if (rel_ap.editar(2, 1, 2) > 0)
			System.out.println("Editado");
		else
			System.out.println("Não Editado");
	}

	public static void buscarPerguntaPorAssunto(Integer id_assunto) {
		Rel_AssuntoPerguntaDAO rel_ap = new Rel_AssuntoPerguntaDAO();
		List<Map<String, Object>> listMap = rel_ap
				.listarPerguntasPorAssunto(id_assunto);

		for (Map<String, Object> map : listMap) {
			System.out.println("Id pergunta: " + map.get("id_pergunta"));
			System.out.println("Descricao: " + map.get("descricao"));
			System.out.println("*********");
		}
	}

	public static void buscarAssuntoPorPergunta(Integer id_pergunta) {
		Rel_AssuntoPerguntaDAO rel_ap = new Rel_AssuntoPerguntaDAO();
		List<Map<String, Object>> listMap = rel_ap
				.listarAssuntosPorPergunta(id_pergunta);

		for (Map<String, Object> map : listMap) {
			System.out.println("Id pergunta: " + map.get("id_assunto"));
			System.out.println("Descricao: " + map.get("descricao"));
			System.out.println("*********");
		}
	}
}
