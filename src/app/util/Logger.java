package app.util;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Registra as excessões lançadas na aplicação.
 * 
 * @author Daniel Bonfim <daniel.fb88@gmail.com>
 * @since 21-08-2012
 * 
 */
public class Logger {

	public static void log(String msg, StackTraceElement[] ste) {
		String stackTrace = arrayStackTraceToString(ste);
		StringBuilder sqlBuilder = new StringBuilder();
		
		if (msg != null && !msg.isEmpty())
			msg = "'" + msg + "'";
		

		try {

			sqlBuilder.append("INSERT INTO log ");
			sqlBuilder.append("(datahora, mensagem, stacktrace) ");
			sqlBuilder.append("VALUES ");
			sqlBuilder.append("(now()," + msg + ", '" + stackTrace + "');");

			Statement statement = DAOUtil.getInstance().getStatement();

			statement.executeUpdate(sqlBuilder.toString());
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String arrayStackTraceToString(StackTraceElement[] ste) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ste.length; i++) {
			builder.append(ste[i].toString() + " -> ");
		}

		return builder.toString();
	}

}
