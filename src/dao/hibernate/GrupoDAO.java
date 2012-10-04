package dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.hibernate.HibernateUtil;
import dominio.usuario.Grupo;

public class GrupoDAO {

	public boolean adicionar(Grupo grupo) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(grupo);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o Grupo. Erro: " + e.getMessage());
			e.printStackTrace();

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

	public boolean editar(Grupo grupo) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(grupo);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o Grupo. Erro: " + e.getMessage());
			e.printStackTrace();

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

	public boolean excluir(Grupo grupo) {
		boolean statusOk = false;
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(grupo);
			transacao.commit();
			statusOk = true;

		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o Grupo. Erro: " + e.getMessage());
			e.printStackTrace();

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

	public Grupo buscarPorId(int id) {
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

	@SuppressWarnings("unchecked")
	public List<Grupo> listarTodos() {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<Grupo> resultado = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			consulta = sessao.createQuery("from Grupo");
			resultado = (List<Grupo>) consulta.list();

			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível selecionar Grupos. Erro: " + e.getMessage());
			e.printStackTrace();

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
}
