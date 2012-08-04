package app.testes;

import java.sql.Connection;
import java.sql.SQLException;

import app.util.*;

public class Testaconexao {

	public static void main(String[] args) {
		try {
			DAOUtil.getInstance().getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Conectou");
		DAOUtil.getInstance().closeConnection();
	}

}
