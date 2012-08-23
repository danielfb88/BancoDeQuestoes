package app.util.exceptions;

public class CamposObrigatoriosInvalidosException extends
		EstadoInvalidoException {
	private static final long serialVersionUID = 1347109015536658661L;

	public CamposObrigatoriosInvalidosException() {
		// TODO Auto-generated constructor stub
	}

	public CamposObrigatoriosInvalidosException(String message) {
		super("Campos obrigatórios inválidos ou nulos." + message);
		// TODO Auto-generated constructor stub
	}

	public CamposObrigatoriosInvalidosException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CamposObrigatoriosInvalidosException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CamposObrigatoriosInvalidosException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
