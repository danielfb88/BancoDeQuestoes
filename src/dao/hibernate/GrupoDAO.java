package dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.hibernate.HibernateUtil;
import dominio.usuario.Grupo;

public class GrupoDAO {

	public boolean salvar(Grupo grupo) {
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

	public boolean atualizar(Grupo grupo) {
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

	public Grupo buscarPorId(Integer id) {
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

	public Grupo buscarGrupoPor(String descricao, Character tipo) {
		Grupo grupo = null;
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
			builder.append("from Grupo where");
			
			if (descricao != null)
				builder.append("descricao = :descricao");

			if (tipo != null)
				builder.append("tipo = :tipo");

			consulta = sessao.createQuery(builder.toString());
			consulta.setString(":descricao", descricao);
			consulta.setCharacter("tipo", tipo);

			grupo = (Grupo) consulta.uniqueResult();
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível buscar o Grupo. Erro: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
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
}
