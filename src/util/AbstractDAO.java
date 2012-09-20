package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Classe abstrata que efetua as operações básicas de uma DAO: - Adicionar,
 * Editar, Excluir, Buscar e Listar.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 10-08-2012
 * 
 *        Ultima atualização ---
 * 
 * @version 2.0
 * 
 */
public abstract class AbstractDAO {

	/**
	 * Objeto de Conexão
	 */
	private static Connection conn;

	/**
	 * Nome da tabela
	 */
	protected String nomeDaTabela;

	/**
	 * Nome do(s) campo(s) primaryKey
	 */
	protected String[] primaryKey;

	/**
	 * SubClasse
	 */
	@SuppressWarnings("rawtypes")
	private Class subClasse;

	/**
	 * Atributos da Subclasse
	 */
	private Field[] atributosDaSubClasse;

	/**
	 * Nome do(s) campo(s) definidos na sub-classe
	 */
	protected String[] campos;

	public AbstractDAO() {
		if (AbstractDAO.conn == null)
			AbstractDAO.conn = ConnectionFactory.getConnection();

		// Recebendo as configuraçõe da subclasse
		this.config();
		this.verificaNomeTabela();
		this.verificaPK();
		this.prepararParaReflexao();
	}

	/**
	 * A subclasse deve sobrecarregar este método para setar as suas
	 * configurações
	 */
	protected abstract void config();

	/**
	 * Verifica se o nome da tabela foi informado na subclasse
	 */
	private void verificaNomeTabela() {
		if (this.nomeDaTabela == null || this.nomeDaTabela.isEmpty()) {
			try {
				throw new Exception("Nome da tabela não informado na sub-classe");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	/**
	 * Verifica se o nome da(s) Primary Key(s) foi informado na subclasse
	 */
	private void verificaPK() {
		try {
			if (this.primaryKey == null || this.primaryKey.length == 0) {
				throw new Exception("Nome da coluna de ID não informado na sub-classe");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Prepara atributos para que possa ser realizada reflexão da subclasse
	 */
	private void prepararParaReflexao() {
		subClasse = this.getClass();
		atributosDaSubClasse = subClasse.getDeclaredFields();
	}

	/**
	 * Retorna o nome da Tabela
	 * 
	 * @return
	 */
	public String getNomeDaTabela() {
		return nomeDaTabela;
	}

	/**
	 * Retorna o nome das primary keys
	 * 
	 * @return
	 */
	public String[] getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Retorna array com nome dos atributos da subclasse
	 * 
	 * @return
	 */
	private String[] getNomeDosAtributosDaSubClasse() {
		String[] atributos = new String[atributosDaSubClasse.length];
		for (int i = 0; i < atributosDaSubClasse.length; i++) {
			atributos[i] = atributosDaSubClasse[i].getName();
		}
		return atributos;
	}

	/**
	 * Retorna lista com valores dos atributos da subclasse
	 * 
	 * @return
	 */
	private Object[] getValorDosAtributosDaSubClasse() {
		Object[] arrayValores = new Object[atributosDaSubClasse.length];

		try {
			for (int i = 0; i < atributosDaSubClasse.length; i++) {
				// get(objeto que possui o atributo)
				arrayValores[i] = atributosDaSubClasse[i].get(this);
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return arrayValores;
	}

	/**
	 * Seta os atributos como null, para fazer uma nova operação
	 */
	public void limparDAO() {
		try {
			for (int i = 0; i < atributosDaSubClasse.length; i++) {
				// set(objeto que possui o atributo, valor);
				atributosDaSubClasse[i].set(this, null);
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Verifica se todos os valores no array são nulos.
	 * 
	 * @param arrayObj
	 * @return
	 */
	private boolean is_todosValoresNulos(Object[] arrayObj) {
		for (int i = 0; i < arrayObj.length; i++) {
			if (arrayObj[i] != null)
				return false;
		}
		return true;
	}

	/**
	 * Prepara os dados contra SQL Injection.
	 * 
	 * @param ps
	 *            Objeto PreparedStatement com a query já inserida.
	 * @param parametros
	 *            Lista com os valores inseridos na mesma ordem dos caracteres
	 *            curinga na query do PreparedStatemet
	 */
	protected void prepareStatement(PreparedStatement ps, List<Object> parametros, Boolean useLike) {
		try {
			for (int i = 0; i < parametros.size(); i++) {
				int indexPS = i + 1;
				switch (parametros.get(i).getClass().getName()) {
					case "java.lang.String":
						if (useLike)
							ps.setString(indexPS, "%" + (String) parametros.get(i) + "%");
						else
							ps.setString(indexPS, (String) parametros.get(i));
						break;
					case "java.lang.Character":
						ps.setString(indexPS, parametros.get(i).toString());
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
						throw new Exception("Tipo do parâmetro não reconhecido");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Verifica se a string de entrada é igual ao nome do campo de alguma
	 * primary key
	 * 
	 * @param campo
	 * @return
	 */
	private boolean is_campoIgualPrimaryKey(String campo) {
		for (int npk = 0; npk < this.primaryKey.length; npk++) {
			if (campo.equalsIgnoreCase(this.primaryKey[npk])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Monta uma string com os valores da primeira lista relacinando-se com os
	 * valores da segunda lista através do sinal "=". Exemplo:
	 * "campoDaLista1 = valorDaLista1, campoDaLista2 = ValorDaLista2, campoDaLista3 = valorDaLista3"
	 */
	private String montaStringCampoEqualValor(List<String> listNomeCampo, List<Object> listValorCampo) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < listNomeCampo.size(); i++) {
			builder.append(listNomeCampo.get(i) + " = " + listValorCampo.get(i));
			if (i != listNomeCampo.size() - 1)
				builder.append(", ");
		}
		return builder.toString();
	}

	/**
	 * Efetua um:
	 * 
	 * "INSERT INTO 'tabela' (campos) VALUES (valores);"
	 * 
	 * utilizando o(s) Ids informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 */
	public int adicionar() {
		int linhasAfetadas = 0;
		StringBuilder builder = new StringBuilder();

		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		List<String> atributosNome_NotNull = new ArrayList<String>();
		List<Object> atributosValor_NotNull = new ArrayList<Object>();

		try {
			// Verificando se o array dos valores não é vazio
			if (is_todosValoresNulos(atributosValor))
				throw new Exception("Nenhum valor para os atributos do DAO foi preenchido.");

			builder.append("INSERT INTO ");
			builder.append(this.nomeDaTabela);
			builder.append(" (");

			int countVirgula = 0;

			for (int i = 0; i < atributosNome.length; i++) {
				// Verificando se o valor é diferente de nulo e vazio
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()) {

					// inserindo nomes e valores NAO NULOS em arraylist
					atributosNome_NotNull.add(atributosNome[i]);
					atributosValor_NotNull.add(atributosValor[i]);

					// inserindo a virgula depois do primeiro elemento
					if (countVirgula++ != 0)
						builder.append(",");
					builder.append(atributosNome[i]);
				}
			}

			builder.append(") ");
			builder.append("VALUES ");
			builder.append("(");

			countVirgula--;
			for (; countVirgula >= 0; countVirgula--) {
				builder.append("?");
				if (countVirgula != 0)
					builder.append(",");
			}

			builder.append(")");
			builder.append(";");

			// preparando o statement
			PreparedStatement ps = AbstractDAO.conn.prepareStatement(builder.toString());
			this.prepareStatement(ps, atributosValor_NotNull, false);

			// executando
			linhasAfetadas = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(0);
		}
		return linhasAfetadas;
	}

	// TODO: Continue. Siga o modelo do adicionar.
	/**
	 * Efetua um:
	 * 
	 * "UPDATE 'tabela' SET 'campos/valores' WHERE filtros"
	 * 
	 * utilizando o(s) Ids informados na subclasse e o HashMap enviado como
	 * parâmetro.
	 * 
	 * @param campoValor
	 *            Map com a primary key e os campos alterados
	 * @return Retorno do executeUpdate
	 */
	public int editar() {
		int linhasAfetadas = 0;
		StringBuilder builder = new StringBuilder();

		// todos os atributos
		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		// atributos NAO NULOS e NAO PRIMARY KEY
		List<String> atributosNome_NotNull_NotPK = new ArrayList<String>();
		List<Object> atributosValor_NotNull_NotPK = new ArrayList<Object>();

		// atributos PRIMARY KEYS NAO NULOS
		List<String> atributosNome_PK_NotNull = new ArrayList<String>();
		List<Object> atributosValor_PK_NotNull = new ArrayList<Object>();

		try {
			// Verificando se o array dos valores não é vazio
			if (is_todosValoresNulos(atributosValor))
				throw new Exception("Nenhum valor para Edição foi preenchido.");

			builder.append("UPDATE ");
			builder.append(this.nomeDaTabela);
			builder.append(" SET ");

			int countVirgula = 0;

			for (int i = 0; i < atributosNome.length; i++) {
				// Verificando se o valor é diferente de nulo e vazio
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()) {

					// atributos NAO PRIMARY KEYS
					if (!this.is_campoIgualPrimaryKey(atributosNome[i])) {

						// inserindo nomes e valores NAO NULOS e NAO PRIMARY KEY
						// em arraylist
						atributosNome_NotNull_NotPK.add(atributosNome[i]);
						atributosValor_NotNull_NotPK.add(atributosValor[i]);

						// inserindo a virgula depois do primeiro elemento
						if (countVirgula++ != 0)
							builder.append(", ");

						builder.append(atributosNome[i] + " = ?");

					} else {
						// atributos PRIMARY KEYS
						atributosNome_PK_NotNull.add(atributosNome[i]);
						atributosValor_PK_NotNull.add(atributosValor[i]);
					}
				}
			}

			// Verificando se o array dos valores das PK não é vazio
			if (is_todosValoresNulos(atributosValor_PK_NotNull.toArray()))
				throw new Exception("Nenhum valor para Primary Key foi preenchido.");

			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(montaStringCampoEqualValor(atributosNome_PK_NotNull, atributosValor_PK_NotNull));
			builder.append(";");

			// TODO: Continue.... vou..
			System.out.println(builder.toString());
			System.exit(0);

			// preparando o statement
			PreparedStatement preparedStatement = AbstractDAO.conn.prepareStatement(builder.toString());
			this.prepareStatement(preparedStatement,
					mergeArray(atributosValor_NotNull_NotPK.toArray(), atributosValor_PK_NotNull.toArray()), false);

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(0);
		}
		return linhasAfetadas;
	}

	/**
	 * Une vários arrays em um array list, preservando a ordem de inserção dos
	 * parâmetros
	 * 
	 * TODO: ANALIZE ESTES WARNINGS
	 * 
	 * @param arrayObj
	 * @return
	 */
	private ArrayList<Object> mergeArray(Object[]... arrayObj) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (int nArrays = 0; nArrays < arrayObj.length; nArrays++) {
			for (int i = 0; i < arrayObj[nArrays].length; i++) {
				list.add(arrayObj[nArrays][i]);
			}
		}
		return list;
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
	protected int _excluir(int... primaryKey) {
		Map<Object, Object> campoValor = new HashMap<Object, Object>(primaryKey.length);

		// mapeando id - valor
		for (int i = 0; i < primaryKey.length; i++) {
			campoValor.put(this.primaryKey[i], primaryKey[i]);
		}

		return this.__excluir(campoValor);
	}

	/**
	 * Recupera o valor da Primary Key (Quando unitária) inserindo as
	 * foreignkeys.
	 * 
	 * Este método deve ser usado APENAS em classes que representem tabelas de
	 * relacionamentos *muitos para muitos*.
	 * 
	 * A ordem dos parâmetros deve ser a mesma definida no array 'campos' do DAO
	 * 
	 * @param foreignKey
	 *            os ids do relacionamento
	 * @return
	 */
	public int getValuePrimaryKey(int... foreignKey) {
		Map<Object, Object> campoValor = new HashMap<Object, Object>(campos.length);

		for (int i = 0; i < campos.length; i++) {
			campoValor.put(this.campos[i], foreignKey[i]);
		}

		Map<String, Object> map = this._listarPor(campoValor).get(0);
		return (Integer) map.get(this.primaryKey[0]);
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
	 *            Map apenas com a(s) Primary key(s)
	 * @return Retorno do executeUpdate
	 */
	private int __excluir(Map<Object, Object> campoValor) {
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

			PreparedStatement preparedStatement = AbstractDAO.conn.prepareStatement(builder.toString());

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return linhasAfetadas;
	}

	/**
	 * Método de busca que recebe um ou vários parâmetros PrimaryKey para serem
	 * inseridos ao filtro. A ordem dos parâmetros é a mesma do array informado
	 * na subclasse.
	 * 
	 * @param primaryKey
	 * @return
	 */
	protected Map<String, Object> _buscarPorId(int... primaryKey) {
		Map<Object, Object> campoValor = new HashMap<Object, Object>(primaryKey.length);

		// mapeando id - valor
		for (int i = 0; i < primaryKey.length; i++) {
			campoValor.put(this.primaryKey[i], primaryKey[i]);
		}

		return this.__buscarPorId(campoValor);
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
	 * @return Map com os valores vindos do ResultSet
	 */
	private Map<String, Object> __buscarPorId(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		ResultSet resultSet = null;
		StringBuilder builder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(this.montaParteQueryID(campoValor));
			builder.append(";");

			PreparedStatement preparedStatement = AbstractDAO.conn.prepareStatement(builder.toString());

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				return null;
			}

			// inserindo atributos no hashMap
			this.preencherMap(map, resultSet, this.getNomeDosAtributosDaSubClasse());

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return map;
	}

	/**
	 * Retorna uma Lista de Map de cada registro
	 * 
	 * @param campoValor
	 *            Map com os campos e seus respectivos valores (Não insira a
	 *            Primary Key)
	 * @return
	 */
	protected List<Map<String, Object>> _listarPor(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		ResultSet resultSet = null;
		StringBuilder builder = new StringBuilder();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Object> ordem = new ArrayList<Object>();

		try {
			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE 1=1");

			for (int i = 0; i < this.campos.length; i++) {
				// inserindo os valores em um array
				if (campoValor.get(campos[i]) != null) {
					ordem.add(campoValor.get(campos[i]));

					builder.append(" AND ");
					// se for string use o LIKE
					if (campoValor.get(campos[i]).getClass().getName().equals("java.lang.String")) {
						builder.append(campos[i] + " LIKE ?");
					} else {
						builder.append(campos[i] + " = ?");
					}
				}
			}

			builder.append(";");

			PreparedStatement preparedStatement = AbstractDAO.conn.prepareStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, ordem, true);

			resultSet = preparedStatement.executeQuery();

			// Iterando os valores retornados no resultSet
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				// inserindo atributos no Map
				this.preencherMap(map, resultSet, this.getNomeDosAtributosDaSubClasse());

				// inserindo o hashMap no arrayList
				listMap.add(map);
			}

			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return listMap;
	}

	/**
	 * Preenche Map com os dados vindos do ResultSet, usando array como chave
	 * para busca
	 */
	protected void preencherMap(Map<String, Object> map, ResultSet rs, String[] chaves) {

		try {
			for (int i = 0; i < chaves.length; i++) {
				map.put(chaves[i], rs.getObject(chaves[i]));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Executa query retornando um Map do ResultSet
	 * 
	 * @param query
	 *            SQL
	 * @param atributos
	 *            Atributos que serão procurados no ResultSet para inserir no
	 *            Map
	 * @return
	 */
	protected List<Map<String, Object>> executarQuery(String query, String[] atributos) {

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		try {
			Statement s = AbstractDAO.conn.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				// preencher map com primary key e campos vindos do resultSet
				Map<String, Object> map = new HashMap<String, Object>();
				this.preencherMap(map, rs, atributos);

				listMap.add(map);
			}

			rs.close();
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// lista
		return listMap;
	}

	/**
	 * Executa query retornando um Map do ResultSet utilizando os atributos
	 * desta DAO filha para o mapeamento.
	 * 
	 * @param query
	 *            SQL
	 * @return
	 */
	protected List<Map<String, Object>> executarQuery(String query) {
		return this.executarQuery(query, this.getNomeDosAtributosDaSubClasse());
	}

	protected List<Map<String, Object>> executarQueryPreparada(String query, Object[] ordem,
			String[] primaryKey, String[] campos) {
		/**
		 * TODO: DESENVOLVER = Executar query Preparada com caracters curingas,
		 * recebendo um array com a ordem dos valores a serem inseridos e
		 * retornando um map dos seus registros. Isto será bastante usado para
		 * relatórios
		 */
		return null;
	}

	/**
	 * Executa Query livre retornando a quantidade de linhas afetadas
	 * 
	 * @param query
	 *            SQL
	 * @return
	 */
	protected int executarUpdate(String query) {
		int linhasAfetadas = 0;
		try {
			Statement s = AbstractDAO.conn.createStatement();
			linhasAfetadas = s.executeUpdate(query);
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return linhasAfetadas;
	}

}
