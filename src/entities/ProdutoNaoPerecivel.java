package entities;

public class ProdutoNaoPerecivel extends Produto {

	private Integer garantia;

	public ProdutoNaoPerecivel(Integer id, String nome, Double preco, Integer garantia) {
		super(id, nome, preco);
		this.garantia = garantia;
	}

	public Integer getGarantia() {
		return garantia;
	}

	@Override
	public void exibirDetalhes() {
		System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Garantia: %d (NÃO PERECÍVEL)\n", id, nome, preco,
				garantia);
	}

}
