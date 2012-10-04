package testes;

import org.hibernate.Session;

import util.hibernate.HibernateUtil;

public class ConnectionHibernatePostgres {

	public static void main(String[] args) {
		Session sessao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conectou!");
		} finally {
			sessao.close();
		}

	}

}
