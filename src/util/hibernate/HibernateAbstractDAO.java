package util.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Abstração das operações de Adicionar, Editar e Excluir para Hibernate.
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * @since 06-10-2012
 * @version 1.0
 * 
 */
public abstract class HibernateAbstractDAO<T> {

	/**
	 * Adicionar
	 * 
	 * @param obj
	 *            Objeto a ser adicionado
	 * @return
	 */
	public boolean adicionar(Object obj) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			if (obj == null)
				throw new Exception("O Objeto Entity Não pode ser nulo.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(obj);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return statusOk;
	}

	/**
	 * Editar
	 * 
	 * @param obj
	 *            Objeto a ser editado
	 * @return
	 */
	public boolean editar(Object obj) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			if (obj == null)
				throw new Exception("O Objeto Entity Não pode ser nulo.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(obj);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de alteraração. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return statusOk;
	}

	/**
	 * Excluir
	 * 
	 * @param obj
	 *            Objeto a ser excluído
	 * @return
	 */
	public boolean excluir(Object obj) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			if (obj == null)
				throw new Exception("O Objeto Entity Não pode ser nulo.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(obj);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return statusOk;
	}

	/**
	 * Lista todos os registros
	 * 
	 * @param classe
	 *            Classe do Objeto Entity que será retornado.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> listarTodos(Class<T> classe) {
		Session sessao = null;
		Transaction transacao = null;
		Query query = null;
		List<T> resultado = null;

		try {
			if (classe == null)
				throw new Exception("A Classe do Objeto Entity deve ser informada.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			query = sessao.createQuery("from " + classe.getSimpleName());
			resultado = query.list();

			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível listar. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return resultado;
	}

	/**
	 * Lista registros utilizando as informações contidas no Map como
	 * filtro.
	 * 
	 * Para valores que sejam String é utilizado o comando LIKE % valor % ao
	 * montar a SQL.
	 * 
	 * @param classe
	 *            Classe do Objeto Entity que será retornado. É Necessário fazer
	 *            um Cast na sub-classe.
	 * @param campoValor
	 *            Map Contendo os filtros para a busca.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> listar(Class<T> classe, Map<String, Object> campoValor) {
		List<T> list = null;
		Session sessao = null;
		Transaction transacao = null;
		Query query = null;
		StringBuilder builder = null;

		try {
			if (classe == null)
				throw new Exception("A Classe do Objeto Entity deve ser informada.");

			// O Map não pode ser vazio ou nulo 
			if (campoValor == null || campoValor.isEmpty())
				throw new Exception("Objeto map sem opções para filtragem.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			builder = new StringBuilder();
			builder.append("FROM " + classe.getSimpleName() + " WHERE ");

			List<Object> listObj = new ArrayList<Object>();

			/*
			 * Verifica quantidade de valores não nulos. Caso este número seja
			 * igual ao tamanho do Map, significa que todos
			 * os parâmetros de entrada para o filtro são nulos. Neste caso não
			 * há filtros a serem inseridos na query.
			 */
			int countMapValoresNulos = 0;
			int i = 0;

			Iterator<Map.Entry<String, Object>> it = campoValor.entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry<String, Object> map = (Map.Entry<String, Object>) it.next();
				if (map.getValue() != null) {
					listObj.add(map.getValue());

					if (i++ != 0)
						builder.append(" AND ");

					// Se for uma String, use o LIKE
					if (map.getValue() instanceof java.lang.String)
						builder.append(map.getKey() + " LIKE ? ");
					else
						builder.append(map.getKey() + " = ? ");

				} else {
					countMapValoresNulos++;
				}
			}

			/*
			 * Se o valor dos filtros forem nulos, não há filtros para a
			 * consulta. Retorne todos os registros.
			 */
			if (countMapValoresNulos == campoValor.size())
				return listarTodos(classe);

			// Enviando a Query
			query = sessao.createQuery(builder.toString());

			// Preparando os parâmetros
			prepareQuery(query, listObj, true);

			list = query.list();
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível listar. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listar. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * Prepara os dados contra SQL Injection.
	 * 
	 * @param query
	 *            Objeto Query do Hibernate.
	 * @param parametros
	 *            Lista com os valores inseridos na mesma ordem dos caracteres
	 *            coringa na query.
	 */
	protected void prepareQuery(Query query, List<Object> parametros, Boolean useLike) {
		try {
			for (int i = 0; i < parametros.size(); i++) {
				switch (parametros.get(i).getClass().getName()) {
					case "java.lang.String":
						if (useLike)
							query.setString(i, "%" + (String) parametros.get(i) + "%");
						else
							query.setString(i, (String) parametros.get(i));
						break;
					case "java.lang.Character":
						query.setString(i, parametros.get(i).toString());
						break;
					case "java.lang.Integer":
						query.setInteger(i, (Integer) parametros.get(i));
						break;
					case "java.lang.Double":
						query.setDouble(i, (Double) parametros.get(i));
						break;
					case "java.lang.Boolean":
						query.setBoolean(i, (Boolean) parametros.get(i));
						break;
					case "java.sql.Date":
						query.setDate(i, (Date) parametros.get(i));
						break;
					default:
						throw new Exception("Tipo do parâmetro não reconhecido.");
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
	}
}
