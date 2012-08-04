package app.util;

/** TODO: PENSAR NA POSSIBILIDADE DE TRAZER TODOS OS METODOS DE CRUD PARA CÁ. 
 VOU PRA AREMBAS. FUI
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * Classe abstrata com métodos comuns a todos os DAOs.
 *  
 * @author Daniel Bonfim
 *
 */
public abstract class DAO {

	/**
	 * VAI ENTRAR UM HASHMAP E VC IRÁ MONTAR A SQL E DEVOLVER.
	 * 
	 * @param hs
	 */
	public void getBy(HashMap hs) {

	}

	/**
	 * VAI ENTRAR UM HASHMAP E VC IRÁ MONTAR A SQL E DEVOLVER.
	 * 
	 * @return Grupo
	 */
	public void getAllBy(HashMap hs) {
		StringBuilder builder = new StringBuilder();
		// analize a possibilidade de usar um hashMap par aesta solução
		Map<Object, String> mp = new HashMap<Object, String>();

		builder.append("SELECT * FROM grupo WHERE true ");

		// Verificando se id informado
		/**
		 * if (id > 0) { builder.append("AND id_grupo = ? "); } // Verificando
		 * descrição informada if (!descricao.equals("")) {
		 * builder.append("AND descricao = ? "); } // Verificando tipo informado
		 * if (!tipo.toString().equals("")) { builder.append("AND tipo = ? "); }
		 * 
		 * 
		 * // PreparedStatement preparedStatment = //
		 * DAOUtil.getInstance().getPreparedStatement(builder.toString());
		 * 
		 * /*
		 * 
		 * 
		 * 
		 * preparedStatment.setInt(1, codigo); ResultSet rs =
		 * preparedStatment.executeQuery(); if (!rs.next()) { rs.close();
		 * preparedStatment.close(); return null; }
		 * 
		 * dtoProduto produto = new dtoProduto();
		 * produto.setCodigo(rs.getInt("CODIGO"));
		 * produto.setNome(rs.getString("NOME"));
		 * produto.setEstoque(rs.getInt("ESTOQUE"));
		 * produto.setPreco(rs.getFloat("PRECO")); rs.close();
		 * preparedStatment.close();
		 * 
		 * return produto;
		 */
	}

}
