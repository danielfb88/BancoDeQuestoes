package app.util.exceptions;

public class TipoParametroNaoEspecificadoException extends Exception {

	public TipoParametroNaoEspecificadoException() {
		// TODO Auto-generated constructor stub
	}

	public TipoParametroNaoEspecificadoException(String message) {
		super("Tipo do parâmetro ainda não definido: " + message);
		// TODO Auto-generated constructor stub
	}

	public TipoParametroNaoEspecificadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TipoParametroNaoEspecificadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TipoParametroNaoEspecificadoException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
