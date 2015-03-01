package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ProdutosDAO {
	
	Connection con;
	
	public ProdutosDAO (Connection con) {
		this.con = con;
	}
	
    public void salva(Produto produto) throws SQLException {
        String sql = "insert into Produto (nome, descricao) values (?,?)";

        try (PreparedStatement stm = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getDescricao());
            stm.execute();

            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    produto.setId(id);
                }
            }

        }
    }
    public List<Produto> lista() throws SQLException {
    	List<Produto> produtos = new ArrayList<>();
    	 String sql = "Select * from produto";
    	 
    	 try(PreparedStatement stm = con.prepareStatement(sql)) {
    		 stm.execute();
    		 transformaResultadoEmProdutos(stm, produtos);
    	 }
  
    	 return produtos;
    }

	private void transformaResultadoEmProdutos(PreparedStatement stm, List<Produto> produtos) throws SQLException {
		try(ResultSet rs = stm.getResultSet()) {
			 while (rs.next()) {
				 int id = rs.getInt("id");
				 String nome = rs.getString("nome");
				 String descricao = rs.getString("descricao");
				 Produto produto = new Produto(nome, descricao);
				 produto.setId(id);
				 produtos.add(produto);
			 }
		 }
	}
    public List<Produto> busca(Categoria categoria) throws SQLException {
    	List<Produto> produtos = new ArrayList<>();
   	 String sql = "Select * from produto where categoria_id = ?";

   	 try(PreparedStatement stm = con.prepareStatement(sql)) {
 		 stm.setInt(1, categoria.getId());
   		 stm.execute();
   		 transformaResultadoEmProdutos(stm, produtos);
   	 }
 
   	 return produtos;
    	
    }
}
