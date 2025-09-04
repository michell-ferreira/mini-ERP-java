package entities;

public class ProdutoNaoPerecivel extends Produto {

	private int garantia;

	public ProdutoNaoPerecivel(int id, String nome, double preco, int garantia) {
		super(id, nome, preco);
		this.garantia = garantia;
	}

	public int getGarantia() {
		return garantia;
	}

	@Override
	public void exibirDetalhes() {
		System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Garantia: %d (NÃO PERECÍVEL)\n", id, nome, preco,
				garantia);
	}

}
