package app.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.util.conexao.DAOUtil;
import app.util.exceptions.AbstractDAOException;
import app.util.exceptions.TipoParametroNaoEspecificadoException;

/**
 * Classe abstrata que efetua as operações básicas de uma DAO: - Adicionar,
 * Editar, Excluir, Buscar e Listar.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * 
 */
public abstract class AbstractDAO {

	/**
	 * Nome da tabela
	 */
	protected String nomeDaTabela;

	/**
	 * Nome do(s) campo(s) primaryKey
	 */
	protected String[] primaryKey;

	/**
	 * Nome do(s) campo(s) da tabela
	 */
	protected String[] campos;

	/**
	 * Prepara os dados contra injection
	 * 
	 * @param ps
	 * @param parametros
	 * @return
	 */
	protected void prepareStatement(PreparedStatement ps,
			List<Object> parametros, Boolean is_consulta) {
		try {
			for (int i = 0; i < parametros.size(); i++) {
				int indexPS = i + 1;
				switch (parametros.get(i).getClass().getName()) {
				case "java.lang.String":
					if (is_consulta)
						ps.setString(indexPS, "%" + (String) parametros.get(i)
								+ "%");
					else
						ps.setString(indexPS, (String) parametros.get(i));
					break;
				case "java.lang.Integer":
					ps.setInt(indexPS, (Integer) parametros.get(i));
					break;
				case "java.lang.Double":
					ps.setDouble(indexPS, (Double) parametros.get(i));
					break;
				case "java.lang.Boolean":
					ps.setBoolean(indexPS, (Boolean) parametros.get(i));
					break;
				case "java.lang.Date":
					ps.setDate(indexPS, (Date) parametros.get(i));
					break;
				default:
					throw new TipoParametroNaoEspecificadoException();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (TipoParametroNaoEspecificadoException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Verifica se o nome da tabela foi informado na subclasse
	 */
	private void verificaNomeTabela() {
		if (this.nomeDaTabela == null || this.nomeDaTabela.isEmpty()) {
			try {
				throw new AbstractDAOException(
						"Nome da tabela não informado na sub-classe");
			} catch (AbstractDAOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	/**
	 * Verifica Primary Keys
	 */
	private void verificaPK() {
		try {
			if (this.primaryKey == null || this.primaryKey.length == 0) {
				throw new AbstractDAOException(
						"Nome da coluna de ID não informado na sub-classe");
			}
		} catch (AbstractDAOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Verifica se a string de entrada é igual ao campo de alguma primary key
	 * 
	 * @param campo
	 * @return
	 */
	private boolean is_campoIgualPrimaryKey(String campo) {
		boolean igualAlgumaPK = false;
		for (int npk = 0; npk < this.primaryKey.length; npk++) {
			if (campo.equals(this.primaryKey[npk]))
				igualAlgumaPK = true;
		}
		return igualAlgumaPK;
	}

	/**
	 * Monta pedaço da query onde é inserido os IDs. Ex:
	 * 
	 * UPDATE tabela SET campo1 = 'valor1', campo2 = 'valor2' WHERE id = 5; A
	 * parte da query "id = 5" é o que será retornado por este método.
	 * 
	 * @return String da parte da query relacionada aos IDs
	 */
	private String montaParteQueryID(Map<Object, Object> campoValor) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < this.primaryKey.length; i++) {
			builder.append(this.primaryKey[i] + " = "
					+ campoValor.get(this.primaryKey[i]));
			if (i != this.primaryKey.length - 1)
				builder.append(", ");
		}
		return builder.toString();
	}

	/**
	 * Efetua um:
	 * 
	 * "INSERT INTO 'tabela' (campos) VALUES (valores) WHERE (filtros)"
	 * 
	 * utilizando o(s) Ids informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 * 
	 * @param campoValor
	 *            HashMap com o nome do campo e o valor relacionado.
	 * @return Retorno do executeUpdate
	 */
	protected int _adicionar(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();

		int linhasAfetadas = 0;
		int i = 0;

		ArrayList<Object> ordem = new ArrayList<Object>();
		StringBuilder builder = new StringBuilder();

		try {
			builder.append("INSERT INTO ");
			builder.append(this.nomeDaTabela + " ");
			builder.append("(");

			// Iterando os objetos para pegar a chave
			Set<Map.Entry<Object, Object>> set = campoValor.entrySet();
			Iterator<Map.Entry<Object, Object>> it = set.iterator();

			while (it.hasNext()) {
				Map.Entry<Object, Object> me = (Map.Entry<Object, Object>) it
						.next();

				// inserindo os valores em um arraylist
				ordem.add(me.getValue());

				// inserindo a virgula depois do primeiro elemento
				if (i++ != 0)
					builder.append(",");
				builder.append((String) me.getKey());
			}

			builder.append(") ");
			builder.append("VALUES ");
			builder.append("(");

			i--;
			for (; i >= 0; i--) {
				builder.append("?");
				if (i != 0)
					builder.append(",");
			}

			builder.append(")");
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, ordem, false);

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linhasAfetadas;
	}

	/**
	 * Efetua um:
	 * 
	 * "UPDATE 'tabela' SET 'campos/valores' WHERE filros"
	 * 
	 * utilizando o(s) Ids informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 * 
	 * @param campoValor
	 * @return Retorno do executeUpdate
	 */
	protected int _editar(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		int linhasAfetadas = 0;
		int i = 0;

		/*
		 * O tamanho do array a ser criado será o tamanho do hashmap -1
		 * excluindo a primary key
		 */
		ArrayList<Object> ordem = new ArrayList<Object>();
		StringBuilder builder = new StringBuilder();

		try {
			builder.append("UPDATE ");
			builder.append(this.nomeDaTabela + " ");
			builder.append("SET ");

			// Iterando os objetos para pegar a chave
			Set<Map.Entry<Object, Object>> set = campoValor.entrySet();
			Iterator<Map.Entry<Object, Object>> it = set.iterator();

			while (it.hasNext()) {
				Map.Entry<Object, Object> me = (Map.Entry<Object, Object>) it
						.next();

				// verificando se é igual a alguma PK
				// ignorando a(s) primary key(s)
				if (!this.is_campoIgualPrimaryKey((String) me.getKey())) {
					// inserindo a virgula depois do primeiro elemento
					if (i++ != 0)
						builder.append(", ");

					builder.append((String) me.getKey() + " = ?");

					// inserindo os valores em um array
					ordem.add(me.getValue());
				}
			}

			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(this.montaParteQueryID(campoValor));
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, ordem, false);

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linhasAfetadas;
	}

	/**
	 * Efetua um:
	 * 
	 * "DELETE FROM 'tabela' WHERE filtros"
	 * 
	 * utilizando o(s) Ids informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 * 
	 * @param campoValor
	 * @return Retorno do executeUpdate
	 */
	private int _excluir(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		int linhasAfetadas = 0;
		StringBuilder builder = new StringBuilder();

		try {
			builder.append("DELETE FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(this.montaParteQueryID(campoValor));
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linhasAfetadas;
	}

	/**
	 * Efetua uma busca por ID
	 * 
	 * "SELECT * FROM WHERE primaryKey(s)"
	 * 
	 * utilizando o(s) Id(s) informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 * 
	 * @param campoValor
	 *            HashMap com o nome da primary key e o valor do id
	 * @return HashMap com os valores vindos do ResultSet
	 */
	private Map<String, Object> __buscarPorId(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		ResultSet resultSet = null;
		StringBuilder builder = new StringBuilder();
		Map<String, Object> campoValorRetorno = new HashMap<String, Object>(
				this.campos.length);

		try {
			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(this.montaParteQueryID(campoValor));
			builder.append(";");

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				return null;
			}

			// inserindo primary keys no hashMap
			for (int i = 0; i < this.primaryKey.length; i++) {
				campoValorRetorno.put(this.primaryKey[i],
						resultSet.getObject(this.primaryKey[i]));

			}

			// inserindo Campos restates no hashMap
			for (int i = 0; i < this.campos.length; i++) {
				campoValorRetorno.put(campos[i],
						resultSet.getObject(this.campos[i]));
			}

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return campoValorRetorno;
	}

	/**
	 * Retorna uma Lista de Map de cada registro
	 * 
	 * @param campoValor
	 * @return
	 */
	protected List<Map<String, Object>> _listarPor(
			Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		ResultSet resultSet = null;
		StringBuilder builder = new StringBuilder();
		List<Map<String, Object>> camposValoresRetornados = new ArrayList<Map<String, Object>>();
		List<Object> ordem = new ArrayList<Object>();

		try {
			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE true");

			for (int i = 0; i < this.campos.length; i++) {
				// inserindo os valores em um array
				if (campoValor.get(campos[i]) != null) {
					ordem.add(campoValor.get(campos[i]));

					builder.append(" AND ");
					// se for string use o LIKE
					if (campoValor.get(campos[i]).getClass().getName()
							.equals("java.lang.String")) {
						builder.append(campos[i] + " LIKE ?");
					} else {
						builder.append(campos[i] + " = ?");
					}
				}
			}

			builder.append(";");

			//System.out.println(builder.toString());
			// System.exit(0);

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, ordem, true);

			resultSet = preparedStatement.executeQuery();

			// Iterando os valores retornados no resultSet
			while (resultSet.next()) {
				Map<String, Object> campoValorRetorno = new HashMap<String, Object>(
						this.campos.length);

				// inserindo primary keys no hashMap
				for (int i = 0; i < this.primaryKey.length; i++) {
					campoValorRetorno.put(this.primaryKey[i],
							resultSet.getObject(this.primaryKey[i]));

				}

				// inserindo Campos restates no hashMap
				for (int i = 0; i < this.campos.length; i++) {
					campoValorRetorno.put(campos[i],
							resultSet.getObject(this.campos[i]));
				}

				// inserindo o hashMap no arrayList
				camposValoresRetornados.add(campoValorRetorno);
			}

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return camposValoresRetornados;
	}

	/**
	 * Método de exclusão que recebe um ou vários parâmetros PrimaryKey para
	 * serem inseridos ao filtro. A ordem dos parâmetros é a mesma do array
	 * informado na subclasse.
	 * 
	 * @param id
	 *            Primary Key
	 * @return
	 */
	public int excluir(Integer... primaryKey) {
		Map<Object, Object> campoValor = new HashMap<Object, Object>(
				primaryKey.length);

		// mapeando id - valor
		for (int i = 0; i < primaryKey.length; i++) {
			campoValor.put(this.primaryKey[i], primaryKey[i]);
		}

		return this._excluir(campoValor);
	}

	/**
	 * Método parcial de busca que recebe um ou vários parâmetros PrimaryKey e
	 * retornam um HashMap para ser concluído na subclasse.
	 * 
	 * @param id
	 *            Primary Key
	 * @return
	 */
	protected Map<String, Object> _buscarPorId(Integer... primaryKey) {
		Map<Object, Object> campoValor = new HashMap<Object, Object>(
				primaryKey.length);

		// mapeando id - valor
		for (int i = 0; i < primaryKey.length; i++) {
			campoValor.put(this.primaryKey[i], primaryKey[i]);
		}

		return this.__buscarPorId(campoValor);
	}

}
