package dominio.usuario;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GrupoHibernateTest extends Grupo {

	@Test
	public void testAdicionar() {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Teste Hibernate 1");
		grupo.setTipo('A');

		assertTrue(grupo.adicionar());
	}

	@Test
	public void testBuscarPorFiltros() {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(115);

		Grupo resultado = grupo.buscarPorId();

		assertNotNull(resultado.getId_grupo());
		System.out.println("Id do Grupo: " + resultado.getId_grupo());
	}

	@Test
	public void testAtualizar() {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Teste Hibernate 1");
		Grupo resultado = grupo.listar().get(0);
		
		resultado.setTipo('C');
		assertTrue(resultado.editar());
	}

	@Test
	public void testExcluir() {
		Grupo grupo = new Grupo();
		grupo.setDescricao("Teste Hibernate 1");
		Grupo resultado = grupo.listar().get(0);
		
		assertTrue(resultado.excluir());
	}


}
