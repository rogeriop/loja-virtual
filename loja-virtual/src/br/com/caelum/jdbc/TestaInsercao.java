package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		
		String nome = "Produto'n";
		String descricao = "Descrição do produto 'n";
		String sql = "insert into Produto (nome, descricao) values (?, ?)";
		
		Connection connection = Database.getConnection();
//		Statement statement = connection.createStatement();
		
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
//		statement.execute("insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')",
//				Statement.RETURN_GENERATED_KEYS);
	
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		boolean resultado = statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}
		
		statement.close();
		connection.close();
		
		
	}
}
