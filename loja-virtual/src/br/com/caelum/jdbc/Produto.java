package br.com.caelum.jdbc;

public class Produto {
	int id;
	String nome;
	String descricao;
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
