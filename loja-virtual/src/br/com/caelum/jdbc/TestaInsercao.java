package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		
		String sql = "insert into Produto (nome, descricao) values (?, ?)";
		
		try(Connection connection = Database.getConnection()) {
//		Statement statement = connection.createStatement();

			connection.setAutoCommit(false);
			
			try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
//		statement.execute("insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')",
//				Statement.RETURN_GENERATED_KEYS);
	
				String nome = "TV LCD";
				String descricao = "32 polegadas";
		
				adiciona(nome, descricao, statement);
		
				nome = "Blueray";
				descricao = "Full HDMI";
		
				adiciona(nome, descricao, statement);
			connection.commit();
//			statement.close();
			} catch (Exception exception) {
				exception.printStackTrace();
				connection.rollback();
				System.out.println("Rollback efetuado com sucesso!");
			}
//			connection.close();
		}
		
		
	}

	private static void adiciona(String nome, String descricao,
			PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
//		if (nome.equals("Blueray")) {
//			throw new IllegalArgumentException("Problema ocorrido");
//		}
		
		boolean resultado = statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}
		
		resultSet.close();
	}
}
