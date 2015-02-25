package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaProduto {
	public static void main(String[] args) throws SQLException {
		Produto produto = new Produto(null, null);
		try(Connection con = new ConnectionPool().getConnection()) {
			
		}
		
	}
}
