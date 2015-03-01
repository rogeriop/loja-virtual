package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriasDAO {
	Connection con;
	public CategoriasDAO(Connection con) {
		this.con = con;
	}
	
    public List<Categoria> lista() throws SQLException {
    	List<Categoria> categorias = new ArrayList<>();
    	 String sql = "SELECT * FROM CATEGORIA";

    	 try(PreparedStatement stm = con.prepareStatement(sql)) {
    		 stm.execute();
    		 try(ResultSet rs = stm.getResultSet()) {
    			 while (rs.next()) {
    				 int id = rs.getInt("id");
    				 String nome = rs.getString("nome");
    				 Categoria categoria = new Categoria(id, nome);
    				 categorias.add(categoria);
    			 }
    		 }
    	 }
    	 return categorias;
    }
    
    public List<Categoria> listaComProdutos() throws SQLException {
    	List<Categoria> categorias = new ArrayList<>();
    	Categoria ultima = null;
    	String sql = "select c.id as c_id, " + 
    	             "       c.nome as c_nome, " + 
    			     "       p.id as p_id,     " + 
    			     "       p.nome as p_nome, " + 
    			     "       p.descricao as p_descricao " + 
    			     "from Categoria as c join Produto as p on p.categoria_id = c.id";
    	try(PreparedStatement stm = con.prepareStatement(sql)) {
    		stm.execute();
    		try(ResultSet rs = stm.getResultSet()) {
    			while(rs.next()) {
	    			int c_id = rs.getInt("c_id");
	    			String c_nome = rs.getString("c_nome");
	    			if (ultima==null || !ultima.getNome().equals(c_nome)) {
	    				Categoria categoria = new Categoria(c_id, c_nome);
	    				categorias.add(categoria);
	    				ultima = categoria;
	    			}
	    			int p_id = rs.getInt("p_id");
	    			String p_nome = rs.getString("p_nome");
	    			String p_descricao = rs.getString("p_descricao");
	    			Produto produto = new Produto (p_nome, p_descricao);
	    			produto.setId(p_id);
	    			ultima.adiciona(produto);
    			}
    			
    		}
    	}
    	return categorias;
    }
}