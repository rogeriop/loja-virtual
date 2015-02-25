package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaProduto {
    public static void main(String[] args) throws SQLException {
        Produto mesa = new Produto("Mesa Vermelha", "Mesa com 4 pés");

        try (Connection con = new ConnectionPool().getConnection()) {
            ProdutosDAO dao = new ProdutosDAO(con);
            dao.salva(mesa);

            List<Produto> produtos = dao.lista();
            for (Produto produto : produtos) {
                System.out.println(produto.id + " ; " + produto.nome + " ; " + produto.descricao);
            }
        }

    }

}
