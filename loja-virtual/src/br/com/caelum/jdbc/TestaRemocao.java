package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = Database.getConnection();
		Statement stm = connection.createStatement();
		
		stm.execute("delete produto where id>3");
		int conta = stm.getUpdateCount();
		System.out.println("conta " + "linhas atualizadas");
		
		stm.close();
		connection.close();
		
	}
}
