package app.util.exceptions;

public class IDInvalidoException extends EstadoInvalidoException {
	private static final long serialVersionUID = 8298778765618297415L;

	public IDInvalidoException() {
		// TODO Auto-generated constructor stub
	}

	public IDInvalidoException(String message) {
		super("ID 0 ou nulo. " + message);
		// TODO Auto-generated constructor stub
	}

	public IDInvalidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IDInvalidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IDInvalidoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
