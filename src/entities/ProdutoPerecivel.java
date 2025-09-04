package entities;

import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {

	private LocalDate dataValidade;

	public ProdutoPerecivel(int id, String nome, double preco, LocalDate dataValidade) {
		super(id, nome, preco);
		this.dataValidade = dataValidade;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	@Override
	public void exibirDetalhes() {
		System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Validade: %s (PERECÍVEL)\n", id, nome, preco,
				dataValidade);
	}

}
