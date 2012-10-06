package util.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dominio.usuario.Grupo;

/**
 * Abstração das operações de Adicionar, Editar e Excluir para Hibernate.
 * 
 * @author Daniel Bonfim (daniel.fb88@gmail.com)
 * @since 06-10-2012
 * 
 */
public abstract class HibernateAbstractDAO {

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
	 *            Classe do Objeto Entity que será retornado. É Necessário fazer
	 *            um Cast na sub-classe.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> listarTodos(Class classe) {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<Object> resultado = null;

		try {
			if (classe == null)
				throw new Exception("A Classe do Objeto Entity deve ser informada.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			consulta = sessao.createQuery("from " + classe.getSimpleName());
			resultado = consulta.list();

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
	 * @param classe
	 *            Classe do Objeto Entity que será retornado. É Necessário fazer
	 *            um Cast na sub-classe.
	 * @param map
	 *            Map Contendo os filtros para a busca.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> listarPor(Class classe, Map<String, Object> map) {
		List<Grupo> list = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		StringBuilder builder = null;

		try {
			if (classe == null)
				throw new Exception("A Classe do Objeto Entity deve ser informada.");

			if (map.isEmpty())
				throw new Exception("Objeto map sem opções para filtragem.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			builder = new StringBuilder();
			builder.append("from " + classe.getSimpleName() + " where ");

			Iterator<String, Object> it = map.entrySet().iterator();
			while(es.iterator().hasNext()) {
				es.iterator().next();
				builder.append(map.entrySet().iterator().next().);
			}
			
			if (descricao != null)
				builder.append("descricao = :descricao ");

			if (tipo != null)
				builder.append("AND tipo = :tipo ");

			consulta = sessao.createQuery(builder.toString());

			if (descricao != null)
				consulta.setString("descricao", descricao);

			if (tipo != null)
				consulta.setCharacter("tipo", tipo);

			list = ((List<Grupo>) consulta.list());
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível listar os Grupos. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

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
	 * @deprecated Utilizar como exemplo
	 * @param id
	 * @return
	 */
	// TODO: UTILIZAR CLASSE CRITERIA
	public Grupo buscarPorId_(int id) {
		Grupo grupo = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			consulta = sessao.createQuery("from Grupo where id_grupo = :id");
			consulta.setInteger(":id", id);

			grupo = (Grupo) consulta.uniqueResult();
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível buscar o Grupo. Erro: " + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de buscar. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return grupo;
	}

	/**
	 * @deprecated Utilizar como exemplo
	 * @param descricao
	 * @param tipo
	 * @return
	 */
	// TODO: UTILIZAR CLASSE CRITERIA
	@SuppressWarnings("unchecked")
	public List<Grupo> listarPor(String descricao, Character tipo) {
		List<Grupo> list = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		StringBuilder builder = null;

		try {
			if (descricao == null && tipo == null)
				throw new Exception("Filtros com valores nulos.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			builder = new StringBuilder();
			builder.append("from Grupo where ");

			if (descricao != null)
				builder.append("descricao = :descricao ");

			if (tipo != null)
				builder.append("AND tipo = :tipo ");

			consulta = sessao.createQuery(builder.toString());

			if (descricao != null)
				consulta.setString("descricao", descricao);

			if (tipo != null)
				consulta.setCharacter("tipo", tipo);

			list = ((List<Grupo>) consulta.list());
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível listar os Grupos. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

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
}
