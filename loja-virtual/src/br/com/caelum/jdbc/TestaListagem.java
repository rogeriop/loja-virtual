package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {
//		Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");

		ConnectionPool connectionPool = new ConnectionPool();
		Connection connection = connectionPool.getConnection();
		
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("SELECT * FROM PRODUTO");
		ResultSet resultSet = statement.getResultSet();
		
		int id = 0;
		String nome = "";
		String descricao = "";
		
		while (resultSet.next()) {
			id = resultSet.getInt("id");
			nome = resultSet.getString("nome");
			descricao = resultSet.getString("descricao");
			System.out.println(id + " " + nome + " " + descricao);
		}
		
		resultSet.close();
		statement.close();
	}
}
