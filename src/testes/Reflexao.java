package testes;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflexao {

	private int funcao1(Object p, int x) throws NullPointerException {
		if (p == null)
			throw new NullPointerException();
		return x;
	}

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("app.util.AbstractDAO");
			Method methlist[] = cls.getDeclaredMethods();

			for (int i = 0; i < methlist.length; i++) {
				Method m = methlist[i];
				System.out.println("nome = " + m.getName());
				System.out.println("membro da classe = "
						+ m.getDeclaringClass());
				System.out.println("modificador = "
						+ Modifier.toString(m.getModifiers()));
				Class pvec[] = m.getParameterTypes();

				for (int j = 0; j < pvec.length; j++)
					System.out.println("parâmetro #" + j + " " + pvec[j]);

				Class evec[] = m.getExceptionTypes();
				for (int j = 0; j < evec.length; j++)
					System.out.println("exceção #" + j + " " + evec[j]);

				System.out.println("tipo de retorno = " + m.getReturnType());
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}

}
