package dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.hibernate.HibernateAbstractDAO;
import util.hibernate.HibernateUtil;
import dominio.usuario.Usuario;

public class UsuarioDAO extends HibernateAbstractDAO {

	public Usuario buscarPorId(int id) {
		Usuario usuario = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			consulta = sessao.createQuery("from Usuario where id_usuario = :id");
			consulta.setInteger(":id", id);

			usuario = (Usuario) consulta.uniqueResult();
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível buscar o Usuario. Erro: " + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de buscar. Mensagem: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return usuario;
	}

	// TODO: UTILIZAR CRITERIA
	@SuppressWarnings("unchecked")
	public List<Usuario> listarPor(String nome, String login) {
		List<Usuario> list = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		StringBuilder builder = null;

		try {
			if (nome == null && login == null)
				throw new Exception("Filtros com valores nulos.");

			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			builder = new StringBuilder();
			builder.append("from Usuario where ");

			if (nome != null)
				builder.append("nome = :nome ");

			if (login != null)
				builder.append("AND login = :login ");

			consulta = sessao.createQuery(builder.toString());

			if (nome != null)
				consulta.setString("nome", nome);

			if (login != null)
				consulta.setString("login", login);

			list = ((List<Usuario>) consulta.list());
			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível listar os Usuarios. Erro: " + e.getMessage());
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
	public List<Usuario> listarTodos() {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<Usuario> resultado = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();

			consulta = sessao.createQuery("from Usuario");
			resultado = (List<Usuario>) consulta.list();

			transacao.commit();

		} catch (HibernateException e) {
			System.out.println("Não foi possível selecionar Usuarios. Erro: " + e.getMessage());
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
