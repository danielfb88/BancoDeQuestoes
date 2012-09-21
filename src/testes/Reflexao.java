package testes;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import dao.PerguntaDAO;

class Teste1 {
	private String att1;
}

class Teste2 extends Teste1 {
	private String att2;
}

public class Reflexao {

	private int funcao1(Object p, int x) throws NullPointerException {
		if (p == null)
			throw new NullPointerException();
		return x;
	}

	public static void main(String args[]) {
		try {
			PerguntaDAO p = new PerguntaDAO();
			p.id_pergunta = 16;
			if(p.excluir() > 0)
				System.out.println("OK");
			else
				System.out.println("NAO OK");
			
			
			
			System.exit(0);
			
			
			p.id_usuario = 5;
			p.tipo_pergunta = 'A';
			p.nivel_pergunta = 'D';
			p.enunciado = "Uhuuuuu!!";
			p.descricao = "Eta porra! 2.0!!! 2";
			
			p.adicionar();
			System.exit(0);


			Class cls = Class.forName("util.AbstractDAO");
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
