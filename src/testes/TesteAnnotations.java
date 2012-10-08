package testes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import dao.hibernate.GrupoDAO;
import dao.hibernate.UsuarioDAO;
import dominio.usuario.Grupo;
import dominio.usuario.Usuario;

public class TesteAnnotations {

	public static void main(String[] args) {
		//		testeAbst();
		//		testeObterValoresNaoNulos();
		listar();
		System.exit(0);

		Usuario usuario = new Usuario();
		Class classe = usuario.getClass();
		Field[] fields = classe.getDeclaredFields();

		for (Field field : fields) {
			Annotation[] anotations = field.getDeclaredAnnotations();
			List<Column> list = new ArrayList<Column>();
			for (Annotation anot : anotations) {
				list.add((Column) anot);
			}

			System.out.println();
			for (Annotation annotation : anotations) {
				System.out.println(annotation.toString());
			}
			System.out.println("Field: " + field.getName());
		}
		System.exit(0);

		Annotation[] anotation = classe.getAnnotations();

		for (int i = 0; i < anotation.length; i++) {
			System.out.println(anotation[0].toString());
		}
	}

	private static void testeAbst() {
		UsuarioDAO uDAO = new UsuarioDAO();
		uDAO.carregarNomeDosCamposDaClasseETabela(new Grupo());

		for (String campo : list) {
			System.out.println(campo);
		}
	}

	private static void testeObterValoresNaoNulos() {
		try {

			Grupo grupo = new Grupo();
			grupo.setId_grupo(1);
			grupo.setDescricao("Teste Descricao");
			grupo.setTipo('A');

			GrupoDAO gDAO = new GrupoDAO();

			System.out.println("Campos da Class: ");

			gDAO.carregarNomeDosCamposDaClasseETabela(grupo);

			for (String campo : gDAO.getCamposDaTabela()) {
				System.out.println(campo);
			}
			System.out.println();
			System.out.println();

			System.out.println("Valores da Class: ");
			List<Object> listNotNull = gDAO.obterValoresDosCamposDaClasse(grupo);

			for (Object obj : listNotNull) {
				System.out.println(obj.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void listar() {
		Grupo grupo = new Grupo();
		//grupo.setId_grupo(1);
		grupo.setDescricao("teste");
		grupo.setTipo('A');

		GrupoDAO gDAO = new GrupoDAO();
		List<Grupo> list = gDAO.listar(grupo);

		for (Grupo g : list) {
			System.out.println("ID: " + g.getId_grupo());
			System.out.println("DESCRICAO: " + g.getDescricao());
			System.out.println("TIPO: " + g.getTipo());
			System.out.println();
		}
	}

}
