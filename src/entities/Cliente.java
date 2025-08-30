package entities;

public class Cliente {

	private Integer id;
	private String nome;

	public Cliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void exibirDetalhes() {
		System.out.printf("ID: %d | Nome: %s\n", id, nome);
	}
}
