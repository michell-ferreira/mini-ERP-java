package br.com.michellferreira.minierp.model.entities;

public class Cliente {

	private int id;
	private String nome;

	public Cliente(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void exibirDetalhes() {
		System.out.printf("ID: %d | Nome: %s\n", id, nome);
	}
}
