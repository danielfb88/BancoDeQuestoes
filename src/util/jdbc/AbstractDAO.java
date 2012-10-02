package util.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

/**
 * Classe abstrata que efetua as operações básicas de uma DAO:
 * Adicionar, Editar, Excluir, Buscar e Listar. </br>
 * 
 * Para utiliza-la, crie uma classe especializada e insira atributos públicos
 * com nomes exatamente iguais aos nomes dos campos da tabela. Em seguida,
 * implemente o método abstrato 'config()', informando dentro dele o nome da
 * tabela com o atributo 'nomeDaTabela', e a(s) primary key(s) no array
 * 'primaryKey'.
 * </br></br>
 * 
 * Exemplo de uma especialização da AbstractDAO: </br>
 * 
 * <pre>
 * public class ExemploDAO extends AbstractDAO {
 * 		public Integer id_campoPK;
 * 		public String campo1_texto;
 * 		public Integer campo2_numero;
 * 
 * 		protected void config() {
 * 			nomeDaTabela = "tb_teste";
 * 			primaryKey = new String[] { "id_campoPk" };
 * 			is_autoIncrement = true;
 * 		}
 * 	}
 * </pre>
 * 
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * @since 10-08-2012 (Ultima modificação 28-09-2012)
 * 
 * @version 2.3
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
	 * Marca se a PrimaryKey é auto-incremento ou não.
	 */
	protected boolean is_autoIncrement;

	/**
	 * Ordena pelo número do parâmetro informado.
	 */
	public Integer orderByParam;

	/**
	 * SubClasse
	 */
	private Class<? extends AbstractDAO> subClasse;

	/**
	 * Atributos da Subclasse
	 */
	private Field[] atributosDaSubClasse;

	public AbstractDAO() {
		if (AbstractDAO.conn == null)
			AbstractDAO.conn = ConnectionFactory.getConnection();

		this.prepararParaReflexao();
		this.config();
		this.verificaNomeTabela();
		this.verificaPK();
	}

	/**
	 * Prepara atributos para que possa ser realizada reflexão da subclasse
	 */
	private void prepararParaReflexao() {
		subClasse = this.getClass();
		atributosDaSubClasse = subClasse.getDeclaredFields();
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
				throw new Exception("Nome da tabela não informado na classe " + this.subClasse.getSimpleName());
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * Verifica se o nome da(s) Primary Key(s) foi informado na subclasse
	 */
	private void verificaPK() {
		try {
			if (this.primaryKey == null || this.primaryKey.length == 0) {
				throw new Exception("Nome da coluna de ID não informado na classe " + this.subClasse.getSimpleName());
			}

			/*
			 * Verificando se o nome da primary key informada está em algum dos
			 * atributos da classe
			 */
			int nPrimaryKeyEncontrada = 0;
			for (int nPK = 0; nPK < primaryKey.length; nPK++) {
				for (int nAtributo = 0; nAtributo < atributosDaSubClasse.length; nAtributo++) {
					if (primaryKey[nPK].equals(atributosDaSubClasse[nAtributo].getName())) {
						nPrimaryKeyEncontrada++;
						break;
					}
				}
			}
			/*
			 * Comparando o numero das PrimaryKeys informadas com o numero das
			 * PrimaryKeys encontradas
			 */
			if (primaryKey.length != nPrimaryKeyEncontrada)
				throw new Exception("Primary key informada não consta nos atributos da classe "
						+ this.subClasse.getSimpleName());

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
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
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return arrayValores;
	}

	/**
	 * Limpa os atributos do Objeto.
	 * Seta os atributos como null para fazer uma nova operação.
	 */
	public void limparAtributos() {
		try {
			for (int i = 0; i < atributosDaSubClasse.length; i++) {
				// set(objeto que possui o atributo, valor);
				atributosDaSubClasse[i].set(this, null);
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);
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
				if (parametros.get(i) != null) {
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
						case "java.sql.Date":
							ps.setDate(indexPS, (Date) parametros.get(i));
							break;
						default:
							throw new Exception("Tipo do parâmetro não reconhecido na classe " +
									this.subClasse.getSimpleName());
					}
				} else {
					ps.setObject(indexPS, parametros.get(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Seta valores para os Fields baseado nos valores do ResultSet.
	 * O objeto passado no parâmetro #1 deve obrigatoriamente conter todos os
	 * Fields(atributos) passados no parâmetro #2. Será utilizado Reflexão para
	 * preencher esses fields com os valores do ResultSet.
	 * 
	 * @param obj
	 *            Objeto que possui os Fields a serem setados.
	 * @param fields
	 *            Fields(atributos) que serão setados.
	 * @param rs
	 *            Valores que serão inseridos aos Fields do Objeto.
	 */
	private void setaValoresComReflexao(Object obj, Field[] fields, ResultSet rs) {

		try {
			for (Field field : fields) {
				String tipoDaClasseDoField = field.getType().getName();

				// Fazendo o cast para inserir o valor no atributo da subclasse
				switch (tipoDaClasseDoField) {
					case "java.lang.String":
						field.set(obj, rs.getString(field.getName()));
						break;
					case "java.lang.Character":
						field.set(obj, rs.getString(field.getName()).charAt(0));
						break;
					case "java.lang.Integer":
						field.set(obj, rs.getInt(field.getName()));
						break;
					case "java.lang.Double":
						field.set(obj, rs.getDouble(field.getName()));
						break;
					case "java.lang.Boolean":
						field.set(obj, rs.getBoolean(field.getName()));
						break;
					case "java.sql.Date":
						field.set(obj, rs.getDate(field.getName()));
						break;
					default:
						throw new Exception("Tipo do parâmetro não reconhecido na classe " +
								this.subClasse.getSimpleName());
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
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
	 * valores da segunda lista através do sinal "=".
	 * 
	 * Exemplo:
	 * "campoDaLista1 = valorDaLista1, campoDaLista2 = ValorDaLista2, campoDaLista3 = valorDaLista3"
	 * 
	 * @param listNomeCampo
	 *            List com o nome dos campos.
	 * @param listValorCampo
	 *            List com o valor dos campos.
	 * @param useLike
	 *            Flag usada quando o valor for uma String.
	 * @param separador
	 *            Separador. Pode ser "," ou "AND". EX com virgula: campo1 =
	 *            valor1, campo2 = valor2. EX com AND: campo1 = valor1 AND
	 *            campo2 = valor2
	 * @return
	 */
	private String montaStringCampoEqualValor(List<String> listNomeCampo, List<Object> listValorCampo,
			boolean useLike, String separador) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < listNomeCampo.size(); i++) {
			if (listValorCampo.get(i) != null) {
				if (listValorCampo.get(i).getClass().getName() == "java.lang.String" && useLike)
					builder.append(listNomeCampo.get(i) + " LIKE " + listValorCampo.get(i));
				else
					builder.append(listNomeCampo.get(i) + " = " + listValorCampo.get(i));
			} else {
				builder.append(listNomeCampo.get(i) + " = " + listValorCampo.get(i));
			}

			if (i != listNomeCampo.size() - 1)
				builder.append(" " + separador + " ");
		}
		return builder.toString();
	}

	/**
	 * Monta uma string com os valores da primeira lista relacinando-se com
	 * caractere coringa '?'
	 * 
	 * Exemplo:
	 * "campoDaLista1 = ?, campoDaLista2 = ?, campoDaLista3 = ?"
	 * 
	 * @param listNomeCampo
	 *            List com o nome dos campos.
	 * @param listValorCampo
	 *            List com o valor dos campos. É necessário para saber se pode
	 *            usar o LIKE ou não. (LIKE's são usados apenas ao lidar com
	 *            Strings).
	 * @param useLike
	 *            Flag usada quando o valor for uma String.
	 * @param separador
	 *            Separador. Pode ser "," ou "AND". EX com virgula: campo1 =
	 *            valor1, campo2 = valor2. EX com AND: campo1 = valor1 AND
	 *            campo2 = valor2
	 * @return
	 */
	private String montaStringCampoEqualCoringa(List<String> listNomeCampo, List<Object> listValorCampo,
			boolean useLike, String separador) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < listNomeCampo.size(); i++) {
			if (listValorCampo.get(i) != null) {
				if (listValorCampo.get(i).getClass().getName() == "java.lang.String" && useLike)
					builder.append(listNomeCampo.get(i) + " LIKE ?");
				else
					builder.append(listNomeCampo.get(i) + " = ?");
			} else {
				builder.append(listNomeCampo.get(i) + " = ?");
			}

			if (i != listNomeCampo.size() - 1)
				builder.append(" " + separador + " ");
		}
		return builder.toString();
	}

	/**
	 * Une vários arrays em um array list, preservando a ordem de inserção dos
	 * parâmetros
	 * 
	 * @param arrayObj
	 *            varargs dos arrays a serem unidos
	 * @return
	 */
	private List<Object> unirArrays(Object[]... arrayObj) {
		List<Object> list = new ArrayList<Object>();
		for (int nArrays = 0; nArrays < arrayObj.length; nArrays++) {
			for (int i = 0; i < arrayObj[nArrays].length; i++) {
				list.add(arrayObj[nArrays][i]);
			}
		}
		return list;
	}

	/**
	 * Efetua um:
	 * 
	 * "INSERT INTO 'tabela' (campos) VALUES (valores);"
	 * 
	 * A Primary Key deve ser auto-increment.
	 * 
	 * @return
	 *         Retorna 0 para nenhuma modificação no DB e
	 *         Retorna > 0 (Quantidade de linhas afetadas) Status OK.
	 */
	public int adicionar() {
		int status = 0;
		StringBuilder builder = new StringBuilder();

		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		List<String> atributosNome_NotNull = new ArrayList<String>();
		List<Object> atributosValor_NotNull = new ArrayList<Object>();

		try {
			// Verificando se o array dos valores não é vazio
			if (is_todosValoresNulos(atributosValor))
				throw new Exception("Nenhum valor para os atributos do objeto da classe " +
						this.subClasse.getSimpleName() + " foi preenchido.");

			builder.append("INSERT INTO ");
			builder.append(this.nomeDaTabela);
			builder.append(" (");

			int countVirgula = 0;

			for (int i = 0; i < atributosNome.length; i++) {
				// Verificando se o valor é diferente de nulo e vazio
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()) {
					// se for auto-incremento ignore a primaryKey
					if (is_autoIncrement) {
						// Verifica se o campo não é uma primary key
						if (!is_campoIgualPrimaryKey(atributosNome[i])) {
							// inserindo nomes e valores NAO NULOS  e NAO PRIMARY KEY em arraylist
							atributosNome_NotNull.add(atributosNome[i]);
							atributosValor_NotNull.add(atributosValor[i]);

							// inserindo a virgula depois do primeiro elemento
							if (countVirgula++ != 0)
								builder.append(",");
							builder.append(atributosNome[i]);
						}
					} else {
						// inserindo nomes e valores NAO NULOS + PRIMARY KEY em arraylist
						atributosNome_NotNull.add(atributosNome[i]);
						atributosValor_NotNull.add(atributosValor[i]);

						// inserindo a virgula depois do primeiro elemento
						if (countVirgula++ != 0)
							builder.append(",");
						builder.append(atributosNome[i]);
					}

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
			status = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}

		return status;

	}

	/**
	 * Efetua um:
	 * 
	 * "UPDATE 'tabela' SET 'campos/valores' WHERE filtros"
	 * 
	 * @return Retorno do executeUpdate
	 */
	public int editar() {
		int linhasAfetadas = 0;
		StringBuilder builder = new StringBuilder();

		// todos os atributos
		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		// atributos NAO PRIMARY KEY
		List<String> atributosNome_NotPK = new ArrayList<String>();
		List<Object> atributosValor_NotPK = new ArrayList<Object>();

		// atributos PRIMARY KEYS NAO NULOS
		List<String> atributosNome_PK_NotNull = new ArrayList<String>();
		List<Object> atributosValor_PK_NotNull = new ArrayList<Object>();

		try {
			// Verificando se o array dos valores não é vazio
			if (is_todosValoresNulos(atributosValor))
				throw new Exception("Nenhum valor para Edição foi preenchido no objeto da classe "
						+ this.subClasse.getSimpleName());

			builder.append("UPDATE ");
			builder.append(this.nomeDaTabela);
			builder.append(" SET ");

			for (int i = 0; i < atributosNome.length; i++) {

				/*
				 * Inserindo nomes e valores NAO PRIMARY KEY em arraylist.
				 * Estes valores PODEM ser nulos.
				 */
				if (!is_campoIgualPrimaryKey(atributosNome[i])) {
					atributosNome_NotPK.add(atributosNome[i]);
					atributosValor_NotPK.add(atributosValor[i]);
				}

				/*
				 * Inserindo nomes e valores PRIMARY KEY em arraylist.
				 * Estes valores NÃO PODEM ser nulos.
				 */
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()
						&& is_campoIgualPrimaryKey(atributosNome[i])) {

					// atributos PRIMARY KEYS
					atributosNome_PK_NotNull.add(atributosNome[i]);
					atributosValor_PK_NotNull.add(atributosValor[i]);
				}
			}

			// Verificando se o array dos valores das PK não é vazio
			if (is_todosValoresNulos(atributosValor_PK_NotNull.toArray()))
				throw new Exception("Nenhum valor para Primary Key do objeto na classe "
						+ this.subClasse.getSimpleName() + "foi preenchido.");

			// Inserindo os Campos
			builder.append(montaStringCampoEqualCoringa(atributosNome_NotPK, atributosValor_NotPK,
					false, ","));

			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(montaStringCampoEqualCoringa(atributosNome_PK_NotNull, atributosValor_PK_NotNull,
					false, "AND"));
			builder.append(";");

			// preparando o statement
			PreparedStatement ps = AbstractDAO.conn.prepareStatement(builder.toString());
			this.prepareStatement(ps,
					unirArrays(atributosValor_NotPK.toArray(), atributosValor_PK_NotNull.toArray()), false);

			// executando
			linhasAfetadas = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
		return linhasAfetadas;
	}

	/**
	 * Efetua um:
	 * 
	 * "DELETE FROM 'tabela' WHERE pk"
	 * Exclui apenas por Primary Key.
	 * Este método só funciona se pelo menos uma das Primary Keys tiver sido
	 * preenchida.
	 * 
	 * @return Retorno do executeUpdate
	 */
	public int excluir() {
		int linhasAfetadas = 0;
		StringBuilder builder = new StringBuilder();

		// todos os atributos
		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		// atributos PRIMARY KEYS NAO NULOS
		List<String> atributosNome_PK_NotNull = new ArrayList<String>();
		List<Object> atributosValor_PK_NotNull = new ArrayList<Object>();

		// pegando somente as PK
		for (int nPK = 0; nPK < primaryKey.length; nPK++) {
			for (int nAtributo = 0; nAtributo < atributosNome.length; nAtributo++) {
				if (primaryKey[nPK].equals(atributosNome[nAtributo]) && atributosValor[nAtributo] != null) {
					atributosNome_PK_NotNull.add(atributosNome[nAtributo]);
					atributosValor_PK_NotNull.add(atributosValor[nAtributo]);
					break;
				}
			}
		}

		try {
			// Verificando se o array dos valores não_nulos não é vazio
			if (atributosValor_PK_NotNull.size() == 0)
				throw new Exception(
						"Nenhum valor para os atributos PrimaryKey no objeto da classe "
								+ this.subClasse.getSimpleName()
								+ " foi preenchido.");

			builder.append("DELETE FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");

			// Inserindo os Ids
			builder.append(montaStringCampoEqualValor(atributosNome_PK_NotNull, atributosValor_PK_NotNull, false, "AND"));
			builder.append(";");

			PreparedStatement ps = AbstractDAO.conn.prepareStatement(builder.toString());

			// executando
			linhasAfetadas = ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
		return linhasAfetadas;
	}

	/**
	 * Retorna os dados da pesquisa no próprio objeto. Este método retorna
	 * apenas um registro. O método carregar() usa o tipo de pesquisa where
	 * "like" para string e "=" para os demais.
	 * 
	 * @return
	 */
	public boolean carregar() {
		StringBuilder builder = new StringBuilder();

		// todos os atributos
		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		// atributos NAO NULOS
		List<String> atributosNome_NotNull = new ArrayList<String>();
		List<Object> atributosValor_NotNull = new ArrayList<Object>();

		ResultSet rs = null;

		try {
			// Verificando se o array dos valores não é vazio
			if (is_todosValoresNulos(atributosValor))
				throw new Exception("Nenhum valor para os atributos no objeto da classe "
						+ this.subClasse.getSimpleName() + " foi preenchido.");

			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");

			for (int i = 0; i < atributosNome.length; i++) {
				// Verificando se o valor é diferente de nulo e vazio
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()) {

					atributosNome_NotNull.add(atributosNome[i]);
					atributosValor_NotNull.add(atributosValor[i]);
				}
			}

			builder.append(montaStringCampoEqualCoringa(atributosNome_NotNull, atributosValor_NotNull,
					true, "AND"));

			builder.append(" LIMIT 1");
			builder.append(";");

			PreparedStatement ps = AbstractDAO.conn.prepareStatement(builder.toString());
			this.prepareStatement(ps, atributosValor_NotNull, true);

			rs = ps.executeQuery();

			if (!rs.next()) {
				rs.close();
				ps.close();
				return false;
			}

			// inserindo valores do resultSet na subclasse
			this.setaValoresComReflexao(this, atributosDaSubClasse, rs);

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return true;
	}

	/**
	 * Lista os registros que atendam aos filtros. Este método retorna uma lista
	 * de DAOs.
	 * 
	 * @return Lista de Objetos da Sub-classe (Lista de DAO's).
	 */
	public List<? extends AbstractDAO> listar() {
		StringBuilder builder = new StringBuilder();

		// todos os atributos
		String[] atributosNome = getNomeDosAtributosDaSubClasse();
		Object[] atributosValor = getValorDosAtributosDaSubClasse();

		// atributos NAO NULOS
		List<String> atributosNome_NotNull = new ArrayList<String>();
		List<Object> atributosValor_NotNull = new ArrayList<Object>();

		List<AbstractDAO> list = new ArrayList<AbstractDAO>();
		ResultSet rs = null;

		try {

			builder.append("SELECT * FROM ");
			builder.append(this.nomeDaTabela);
			builder.append(" WHERE ");
			builder.append("1=1 ");

			// Verificando se existem valores a serem filtrados
			if (!is_todosValoresNulos(atributosValor)) {
				builder.append("AND ");
			}

			for (int i = 0; i < atributosNome.length; i++) {
				// Verificando se o valor é diferente de nulo e vazio
				if (atributosValor[i] != null && !atributosValor[i].toString().isEmpty()) {

					atributosNome_NotNull.add(atributosNome[i]);
					atributosValor_NotNull.add(atributosValor[i]);
				}
			}

			builder.append(montaStringCampoEqualCoringa(atributosNome_NotNull, atributosValor_NotNull,
					true, "AND"));

			/*
			 * Order By
			 */
			if (orderByParam != null) {
				if ((orderByParam > 0) && (orderByParam <= atributosNome.length)) {
					builder.append(" ORDER BY ");
					builder.append(atributosNome[orderByParam - 1]);

				} else {
					throw new Exception("O index para o OrderByParam está fora do " +
							"intervalo dos atributos da classe " + subClasse.getSimpleName() +
							" - Número de atributos: " + atributosNome.length +
							" - Index informado: " + orderByParam);
				}
			}

			builder.append(";");

			PreparedStatement ps = AbstractDAO.conn.prepareStatement(builder.toString());
			this.prepareStatement(ps, atributosValor_NotNull, true);

			rs = ps.executeQuery();

			while (rs.next()) {
				Object obj = subClasse.newInstance();
				this.setaValoresComReflexao(obj, atributosDaSubClasse, rs);

				list.add((AbstractDAO) obj);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return list;
	}

	/**
	 * Executa query retornando uma lista de objetos da Sub-Classe carregado com
	 * os valores vindos do ResultSet.
	 * 
	 * @param query
	 *            SQL
	 * @return Lista de Objetos DAO
	 */
	protected List<? extends AbstractDAO> executarQuery(String query) {
		List<AbstractDAO> list = new ArrayList<AbstractDAO>();

		try {
			Statement s = AbstractDAO.conn.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				Object obj = subClasse.newInstance();
				this.setaValoresComReflexao(obj, atributosDaSubClasse, rs);
				list.add((AbstractDAO) obj);
			}

			rs.close();
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}

		return list;
	}

	/**
	 * Executa query preparada e retornando uma lista de objetos da Sub-Classe
	 * carregado com os valores vindos do ResultSet. Deve-se obrigatoriamente
	 * utilizar o LIKE para filtros que forem uma String. Caso contrário, nenhum
	 * registro será retornado.
	 * 
	 * @param query
	 *            SQL
	 * @param Array
	 *            com valor dos caracteres coringas. A ordem dos indices deve
	 *            ser a mesma da posição dos caracteres na query.
	 * @return
	 */
	protected List<? extends AbstractDAO> executarQueryPreparada(String query, List<Object> valores) {
		List<AbstractDAO> list = new ArrayList<AbstractDAO>();
		ResultSet rs = null;

		try {
			PreparedStatement ps = AbstractDAO.conn.prepareStatement(query);
			prepareStatement(ps, valores, true);
			rs = ps.executeQuery();

			while (rs.next()) {
				Object obj = subClasse.newInstance();
				this.setaValoresComReflexao(obj, atributosDaSubClasse, rs);
				list.add((AbstractDAO) obj);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}

		return list;
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
			System.exit(-1);
		}

		return linhasAfetadas;
	}

	/**
	 * Inicia Tranzação
	 */
	public void iniciarTranzacao() {
		try {
			if ((AbstractDAO.conn != null) && !AbstractDAO.conn.isClosed()
					&& (AbstractDAO.conn.getAutoCommit() == true)) {
				AbstractDAO.conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Commita modificações
	 */
	public void commit() {
		try {
			if ((AbstractDAO.conn != null) && !AbstractDAO.conn.isClosed()
					&& (AbstractDAO.conn.getAutoCommit() == false)) {
				AbstractDAO.conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Rollback das Modificações
	 */
	public void rollback() {
		try {
			if ((AbstractDAO.conn != null) && !AbstractDAO.conn.isClosed()
					&& (AbstractDAO.conn.getAutoCommit() == false)) {
				AbstractDAO.conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Commita modificações e finaliza a Tranzação
	 */
	public void finalizarTranzacao() {
		try {
			if ((AbstractDAO.conn != null) && !AbstractDAO.conn.isClosed()
					&& (AbstractDAO.conn.getAutoCommit() == false)) {
				AbstractDAO.conn.commit();
				AbstractDAO.conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
