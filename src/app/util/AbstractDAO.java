package app.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import app.util.conexao.DAOUtil;
import app.util.exceptions.AbstractDAOException;
import app.util.exceptions.TipoParametroNaoEspecificadoException;

/**
 * Classe abstrata que efetua as operações básicas de um DAO: - Adicionar,
 * Editar, Excluir, Buscar e Listar.
 * 
 * @author Daniel Bonfim <daniel.fb88@mail.com>
 * 
 */
public abstract class AbstractDAO {

	/**
	 * Nome da tabela
	 */
	protected String nomeDaTabela;

	/**
	 * O nome do campo primaryKey
	 */
	protected String[] primaryKey;

	/**
	 * Os nomes dos campos da tabela
	 */
	protected String[] campos;

	/**
	 * Verifica se o nome da tabela foi informado na classe filha
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
	 * Adicionar
	 * 
	 * @param campoValor
	 *            HashMap com o nome do campo e o valor relacionado.
	 * @return
	 */
	protected int _adicionar(Map<String, Object> campoValor) {
		this.verificaNomeTabela();

		int linhasAfetadas = 0;
		int i = 0;

		Object valores[] = new Object[campoValor.size()];
		StringBuilder builder = new StringBuilder();

		try {
			builder.append("INSERT INTO ");
			builder.append(this.nomeDaTabela + " ");
			builder.append("(");

			// Iterando os objetos para pegar a chave
			Set<Map.Entry<String, Object>> set = campoValor.entrySet();
			Iterator<Map.Entry<String, Object>> it = set.iterator();

			while (it.hasNext()) {
				Map.Entry<String, Object> me = (Map.Entry<String, Object>) it
						.next();

				// armazenando o valor
				valores[i] = me.getValue();

				// inserindo a virgula depois do primeiro elemento
				if (i++ != 0)
					builder.append(",");
				builder.append(me.getKey());
			}

			builder.append(") ");
			builder.append("VALUES ");

			builder.append("(");

			for (; i > 0; i--) {
				if (i == 1)
					builder.append("? "); // sera executado por ultimo
				else
					builder.append("?, ");
			}
			
			builder.append(") ");
			builder.append(";");
			
			/* TODO: Analize esta query e otimize este ultimo for. Tou achando
			 * estranho ter que colocar os dados em um array. Veja isso, vou dormir.
			 */
			System.out.println(builder.toString());
			System.exit(0);

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, valores);

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linhasAfetadas;
	}

	protected int _editar(Map<Object, Object> campoValor) {
		this.verificaNomeTabela();
		this.verificaPK();

		int linhasAfetadas = 0;
		int i = 0;

		Object valores[] = new Object[campoValor.size()];
		StringBuilder builder = new StringBuilder();

		try {
			builder.append("UPDATE ");
			builder.append(this.nomeDaTabela + " ");
			builder.append("SET ");
			builder.append("(");

			// Iterando os objetos para pegar a chave
			Set<Map.Entry<Object, Object>> set = campoValor.entrySet();
			Iterator<Map.Entry<Object, Object>> it = set.iterator();

			while (it.hasNext()) {
				Map.Entry<Object, Object> me = (Map.Entry<Object, Object>) it
						.next();

				// verificando se é igual a alguma PK
				boolean igualAlgumaPK = false;
				for (int npk = 0; npk < this.primaryKey.length; npk++) {
					if (me.getKey().equals(this.primaryKey[npk]))
						igualAlgumaPK = true;
				}

				// ignorando a(s) primary key(s)
				if (!igualAlgumaPK) {
					// inserindo a virgula depois do primeiro elemento
					if (i++ != 0)
						builder.append(",");

					builder.append((String) me.getKey() + " = ?");

				}
			}

			builder.append(") ");
			builder.append("WHERE ");

			// Id
			for (i = 0; i < this.primaryKey.length; i++) {
				builder.append(this.primaryKey[i] + " = "
						+ campoValor.get(this.primaryKey[i]));
				if (i != this.primaryKey.length - 1)
					builder.append(", ");
			}

			builder.append(";");

			/* TODO: Query deste metodo concluida
			 * Insira os objetos dos parametros no preparedStatement
			 */
			System.out.println(builder.toString());
			System.exit(0);
			// Verificar o prepared Statement com @nome

			PreparedStatement preparedStatement = DAOUtil.getInstance()
					.getPreparedStatement(builder.toString());

			// preparando o statement
			this.prepareStatement(preparedStatement, valores);

			// executando
			linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linhasAfetadas;
	}

	/**
	 * TODO: Completar este método com os tipos restantes.
	 * 
	 * Prepara os dados contra injection
	 * 
	 * @param ps
	 * @param parametros
	 * @return
	 */
	protected void prepareStatement(PreparedStatement ps, Object[] parametros) {
		try {
			for (int i = 0; i < parametros.length; i++) {
				int index = i + 1;
				switch (parametros[i].getClass().getName()) {
				case "java.lang.String":
					ps.setString(index, (String) parametros[i]);
					break;
				case "java.lang.Integer":
					ps.setInt(index, (Integer) parametros[i]);
					break;
				case "java.lang.Double":
					ps.setDouble(index, (Double) parametros[i]);
					break;
				case "java.lang.Boolean":
					ps.setBoolean(index, (Boolean) parametros[i]);
					break;
				case "java.lang.Date":
					ps.setDate(index, (Date) parametros[i]);
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

}
