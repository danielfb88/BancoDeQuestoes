package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.jdbc.GrupoDAO;

public class GrupoDAOTest {

	@Test
	public void testAdicionarComIDExistente() {
		GrupoDAO gDAO = new GrupoDAO();
		gDAO.id_grupo = 101;
		gDAO.descricao = "teste junit";
		gDAO.tipo = 'A';
		assertEquals(1, gDAO.adicionar());
	}

}
