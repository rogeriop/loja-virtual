package br.com.caelum.jdbc;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

		int id;
		String nome;
		private final List<Produto> produtos = new ArrayList<>();

		public Categoria(int id, String nome) {
			super();
			this.id = id;
			this.nome = nome;
		}
		public int getId() {
			return id;
		}
		public String getNome() {
			return nome;
		}
		
		public List<Produto> getProdutos() {
			return produtos;
		}
		
		public void adiciona(Produto produto) {
			produtos.add(produto);
		}
		
		
}
