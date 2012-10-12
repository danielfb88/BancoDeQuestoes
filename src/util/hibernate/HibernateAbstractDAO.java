package util.hibernate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Abstração das operações de Adicionar, Editar, Excluir, Listar e ListarTodos
 * para Hibernate.
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
			System.out.println("Não foi possível listar todos os registros. Erro: " + e.getMessage());
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
	 * Lista registros utilizando os valores dos atributos contidas no Objeto
	 * Entity como filtro.
	 * 
	 * Para valores que sejam String é utilizado o comando LIKE % valor % ao
	 * montar a SQL.
	 * 
	 * @param classe
	 *            Classe do Objeto Entity que será retornado. É Necessário fazer
	 *            um Cast na sub-classe.
	 * @param entityObject
	 *            Objeto Entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> listar(Class<T> classe, Object entityObject) {
		List<T> list = null;
		Session sessao = null;
		Transaction transacao = null;
		Query query = null;
		StringBuilder builder = null;

		try {
			if (classe == null)
				throw new Exception("A Classe do Objeto Entity deve ser informada.");

			// O Objeto Entity (Pojo) não pode ser nulo 
			if (entityObject == null)
				throw new Exception("Objeto Entity nulo.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			builder = new StringBuilder();
			builder.append("FROM " + classe.getSimpleName() + " WHERE ");

			List<List<String>> listCampos_Tabela0_Classe1 = obterNomeDosCampos_Tabela0_Classe1(entityObject);
			List<String> listNomeCamposDaTabela = listCampos_Tabela0_Classe1.get(0);
			List<String> listNomeCamposDaClasse = listCampos_Tabela0_Classe1.get(1);

			List<Object> listValoresCamposDaClasse = obterValores(listNomeCamposDaClasse, entityObject);
			List<Object> listValoresCamposDaClasseNaoNulos = new ArrayList<Object>();

			/*
			 * Verifica quantidade de valores não nulos. Caso este número seja
			 * igual ao tamanho da lista, significa que todos
			 * os parâmetros de entrada para o filtro são nulos. Neste caso não
			 * há filtros a serem inseridos na query.
			 */
			int countValoresNulos = 0;
			int i = 0;

			for (int k = 0; k < listValoresCamposDaClasse.size(); k++) {
				if (listValoresCamposDaClasse.get(k) != null) {
					listValoresCamposDaClasseNaoNulos.add(listValoresCamposDaClasse.get(k));

					if (i++ != 0)
						builder.append(" AND ");

					// Se for uma String, use o LIKE
					if (listValoresCamposDaClasse.get(k) instanceof java.lang.String)
						builder.append(listNomeCamposDaTabela.get(k) + " LIKE ? ");
					else
						builder.append(listNomeCamposDaTabela.get(k) + " = ? ");

				} else {
					countValoresNulos++;
				}

			}

			/*
			 * Se os valores dos filtros forem nulos, não há filtros para a
			 * consulta. Retorne todos os registros.
			 */
			if (countValoresNulos == listValoresCamposDaClasse.size())
				return listarTodos(classe);

			// Enviando a Query
			query = sessao.createQuery(builder.toString());

			// Preparando os parâmetros
			prepareQuery(query, listValoresCamposDaClasseNaoNulos, true);

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
	 * Transforma o primeiro caractere em maiuscula
	 * 
	 * @param palavra
	 * @return
	 */
	private String maiuscula1(String palavra) {
		return palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
	}

	/**
	 * Obtém os valores do Objeto do parametro #2, baseado no nome dos campos
	 * recebidos do parametro #1.
	 * 
	 * @param nomeDosCamposDaClasse
	 *            Lista de String com o nome dos atributos que devem existir no
	 *            objeto do parametro #2
	 * @param pojo
	 *            Objeto que será refletido e copiado os valores contidos nos
	 *            atributos descritos no parametro #1
	 * @return
	 */
	private List<Object> obterValores(List<String> nomeDosCamposDaClasse, Object pojo) {
		List<Object> listValores = new ArrayList<Object>();

		try {
			Class<? extends Object> pojoClass = pojo.getClass();
			Method[] methods = pojoClass.getMethods();

			for (String campo : nomeDosCamposDaClasse) {
				// modificando a string campo para se parecer com um metodo 'get'
				String nomeDoMetodo = ("get" + maiuscula1(campo)).trim();

				// procurando pelo método definido acima
				for (Method method : methods) {
					if (method.getName().equals(nomeDoMetodo)) {
						// invocando-o e obtendo o seu valor
						listValores.add(method.invoke(pojo));
						break;
					}
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);

		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return listValores;
	}

	/**
	 * Obtem o nome dos campos da classe através da Reflexão, e da tabela
	 * atraves
	 * das Anotações (quando existirem).
	 * 
	 * @return Lista de Lista de String.
	 *         A Lista 0 refere-se a tabela.
	 *         A Lista 1 refere-se a classe.
	 * @throws Exception
	 * 
	 */
	private List<List<String>> obterNomeDosCampos_Tabela0_Classe1(Object pojo) {
		List<List<String>> listCampos_Tabela0_Classe1 = new ArrayList<List<String>>();

		try {

			// Verificando se o Pojo é uma Entity
			if (pojo.getClass().getAnnotation(Entity.class) == null)
				throw new Exception("O objeto Pojo de entrada não é uma Entity.");

			List<String> camposDaTabela = new ArrayList<String>();
			List<String> camposDaClasse = new ArrayList<String>();

			// Pegando os Fields
			Field[] pojoFields = pojo.getClass().getDeclaredFields();
			for (Field pojoField : pojoFields) {

				// Verificando se o field possui a anotação Column
				Column annotationColumn = pojoField.getAnnotation(Column.class);
				if (annotationColumn != null) {
					camposDaTabela.add(annotationColumn.name());
					camposDaClasse.add(pojoField.getName());

				} else {
					// Verificando se o field possui a anotação ID
					if (pojoField.getAnnotation(Id.class) != null) {
						camposDaTabela.add(pojoField.getName());
						camposDaClasse.add(pojoField.getName());
					}

					// Verificando se o field possui a anotação JoinColumn
					JoinColumn annotationJoinColumn = pojoField.getAnnotation(JoinColumn.class);
					if (annotationJoinColumn != null) {
						camposDaTabela.add(annotationJoinColumn.name());
						camposDaClasse.add(pojoField.getName());
					}
				}
			}

			listCampos_Tabela0_Classe1.add(camposDaTabela);
			listCampos_Tabela0_Classe1.add(camposDaClasse);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return listCampos_Tabela0_Classe1;
	}

	/**
	 * Prepara os dados contra SQL Injection. Caso o objeto a ser inserido ao
	 * bind seja um POJO, é verificado se ele é uma Entity. Caso positivo, o
	 * campo que possuir a Annotation '@ID' desta Entity é inserido a Query.
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
						/*
						 * Pegando o Id caso o objeto seja uma Entity
						 */
						Class<? extends Object> classObj = parametros.get(i).getClass();
						Entity entity = classObj.getAnnotation(Entity.class);

						// Verificando se é uma Entity
						if (entity != null) {
							List<String> listNomeCamposDaClasse = obterNomeDosCampos_Tabela0_Classe1(parametros.get(i)).get(1);
							Method[] methods = classObj.getMethods();

							for (Method method : methods) {
								for (String campo : listNomeCamposDaClasse) {

									// Verificando se o campo possui Annotation @Id
									if (classObj.getDeclaredField(campo).getAnnotation(Id.class) != null) {

										// modificando a string campo para se parecer com um metodo 'get'
										String nomeDoMetodo = ("get" + maiuscula1(campo)).trim();

										// Procurando pelo nome do método definido acima
										if (method.getName().equals(nomeDoMetodo)) {
											// invocando-o e obtendo o seu valor
											Integer id = (Integer) method.invoke(parametros.get(i));

											if (id != null) {
												query.setInteger(i, id);
												break;

											} else {
												throw new Exception("O valor obtido atraves do método "
														+ nomeDoMetodo + " não pode ser nulo.");
											}
										}
									}
								}
							}

						} else {
							throw new Exception("Tipo do parâmetro não reconhecido.");
						}
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(-1);
		}
	}
}
