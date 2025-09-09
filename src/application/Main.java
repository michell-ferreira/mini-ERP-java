package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Cliente;
import entities.Produto;
import entities.ProdutoNaoPerecivel;
import entities.ProdutoPerecivel;
import entities.Venda;
import enums.StatusVenda;

public class Main {

	private static List<Produto> produtos = new ArrayList<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static List<Venda> vendas = new ArrayList<>();

	private static int proximoIdProduto = 1;
	private static int proximoIdCliente = 1;
	private static int proximoIdVenda = 1;

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao = 0;

		while (opcao != 10) {
			exibirMenu();

			try {
				opcao = Integer.parseInt(scanner.nextLine());

				switch (opcao) {
				case 1:
					cadastrarProduto();
					break;
				case 2:
					listarProduto();
					break;
				case 3:
					cadastrarCliente();
					break;
				case 4:
					listarCliente();
					break;
				case 5:
					registrarVenda();
					break;
				case 6:
					listarVenda();
					break;
				case 7:
					cancelarVenda();
					break;
				case 10:
					System.out.println("Encerrando o sistema ...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente");
				}

			} catch (NumberFormatException e) {
				System.out.println("Por favor, digite uma opção válida");
				opcao = 0;

			}
			if (opcao != 0) {
				System.out.println("\nPressione Enter para continuar...");
				scanner.nextLine();
			}

		}
		scanner.close();
	}

	public static void exibirMenu() {
		System.out.println("\n--- Mini ERP ---");
		System.out.println("1. Cadastrar Produtos");
		System.out.println("2. Listar Produtos");
		System.out.println("3. Cadastrar Clientes");
		System.out.println("4. Listar Clientes");
		System.out.println("5. Registrar Venda");
		System.out.println("6. Listar Vendas");
		System.out.println("7. Cancelar Vendas");
		System.out.println("10. Sair");
		System.out.print("Escolha uma opção: ");
	}

	public static void cadastrarProduto() {
		try {
			System.out.println("\n--- Cadastro de Produtos ---");
			System.out.print("Nome: ");
			String nome = scanner.nextLine();
			if (nome.trim().isEmpty()) {
				System.out.println("O nome não pode ser vazio");
				return;
			}

			System.out.print("Preço: ");
			double preco = Double.parseDouble(scanner.nextLine());
			if (preco <= 0) {
				System.out.println("O preço não pode ser menor ou igual a 0");
				return;
			}

			System.out.print("O produto é Perecível? (S/N): ");
			String perecivel = scanner.nextLine();

			if (perecivel.equalsIgnoreCase("s")) {
				System.out.print("Data de Validade (dd/MM/yyyy): ");
				String dataStr = scanner.nextLine();
				DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataValidade = LocalDate.parse(dataStr, dataFormato);
				if (dataValidade.isBefore(LocalDate.now())) {
					System.out.println("O produto está fora da validade.");
					return;
				}
				produtos.add(new ProdutoPerecivel(proximoIdProduto, nome, preco, dataValidade));

			} else if (perecivel.equalsIgnoreCase("n")) {
				System.out.print("Garantia: ");
				int garantia = Integer.parseInt(scanner.nextLine());
				if (garantia < 0) {
					System.out.println("Garantia não pode ser menor que 0.");
					return;
				}
				produtos.add(new ProdutoNaoPerecivel(proximoIdProduto++, nome, preco, garantia));

			} else {
				System.out.println("Opção inválida. Tente novamente.");
				return;
			}
			System.out.println("Produto cadastrado com Sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void listarProduto() {
		System.out.println("\n--- Lista de Produtos ---");
		if (produtos.isEmpty()) {
			System.out.println("Lista de Produtos vazia.");
			return;
		}
		for (Produto p : produtos) {
			p.exibirDetalhes();
		}
	}

	public static void cadastrarCliente() {
		System.out.println("\n--- Cadastro de Cliente ---");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		if (nome.trim().isEmpty()) {
			System.out.println("O nome não pode ser vazio");
			return;
		}
		clientes.add(new Cliente(proximoIdCliente++, nome));
		System.out.println("Cliente cadastrado com Sucesso!");
	}

	public static void listarCliente() {
		System.out.println("\n--- Lista de Clientes ---");
		if (clientes.isEmpty()) {
			System.out.println("Lista de Clientes vazia.");
			return;
		}
		for (Cliente c : clientes) {
			c.exibirDetalhes();
		}
	}

	public static void registrarVenda() {
		try {
			System.out.println("\n--- Registrar Venda ---");
			if (produtos.isEmpty() || clientes.isEmpty()) {
				System.out
						.println("É necessário ter ao menos um cliente e um produto cadastrado para registrar venda.");
				return;
			}

			listarCliente();
			System.out.print("Digite o ID do cliente para a venda: ");
			int IdCliente = Integer.parseInt(scanner.nextLine());

			Cliente clienteSelecionado = null;
			for (Cliente c : clientes) {
				if (c.getId() == IdCliente) {
					clienteSelecionado = c;
					break;
				}
			}

			if (clienteSelecionado == null) {
				System.out.println("Cliente com o ID informado não encontrado.");
				return;
			}

			List<Produto> carrinhoCompras = new ArrayList<>();
			int idProduto = -1;
			while (idProduto != 0) {
				listarProduto();
				System.out.print("Digite o ID do produto para adicionar ao carrinho (ou 0 para finalizar): ");
				idProduto = Integer.parseInt(scanner.nextLine());

				if (idProduto != 0) {
					Produto produtoSelecionado = null;
					for (Produto p : produtos) {
						if (p.getId() == idProduto) {
							produtoSelecionado = p;
						}
					}
					if (produtoSelecionado != null) {
						carrinhoCompras.add(produtoSelecionado);
						System.out.println("'" + produtoSelecionado.getNome() + "' adicionado ao carrinho.");
					} else {
						System.out.println("Produto não encontrado.");
					}
				}
			}

			if (carrinhoCompras.isEmpty()) {
				System.out.println("Nenhum produto selecionado. Venda cancelada.");
				return;
			}

			Venda novaVenda = new Venda(proximoIdVenda++, clienteSelecionado, carrinhoCompras);
			vendas.add(novaVenda);

			System.out.println("\n==============================");
			System.out.println("Venda registrada com sucesso!!");
			System.out.printf("O valor total é %.2f\n", novaVenda.getValorTotal());
			System.out.println("==============================");

		} catch (Exception e) {
			System.out.println("Erro ao registrar venda. Verifique os IDs inseridos. " + e.getMessage());
		}

	}

	public static void listarVenda() {
		System.out.println("\n--- Histórico de Vendas ---");
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda foi registrada até o momento.");
			return;
		}
		
		for (Venda v : vendas) {
			System.out.println("---------------------");
			System.out.printf("ID da venda: %d | Data: %s | Cliente: %s (ID: %d) | Status: %s\n",
					v.getId(), v.getDataVenda(), v.getCliente().getNome(), v.getCliente().getId(), v.getStatus());	
			for (Produto p : v.getProdutosVendidos()) {
				System.out.printf("- ID: %d | %s (R$%.2f)\n", p.getId(), p.getNome(), p.getPreco());
			}
			System.out.printf("VALOR TOTAL DA VENDA: R$%.2f\n", v.getValorTotal());
		}
		System.out.println("---------------------");

	}
	
	private static void cancelarVenda(){
		System.out.println("\n--- Cancelamento de Vendas ---");
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda para cancelar");
			return;
		}
		
		listarVenda();
		
		try {
			System.out.print("Digite o ID da venda que deseja cancelar: ");
			int idVenda = Integer.parseInt(scanner.nextLine());
			
			Venda vendaSelecionada = null;
			for (Venda v : vendas) {
				if (v.getId() == idVenda) {
					vendaSelecionada = v;
					break;
				}
			}
			
			if (vendaSelecionada != null) {
				if (vendaSelecionada.getStatus() == StatusVenda.CANCELADO) {
					System.out.println("Esta venda já foi cancelada.");
				} else {
					vendaSelecionada.cancelarVenda();
					System.out.println("Venda ID-" + idVenda + " cancelada com sucesso.");
				}
			} else {
				System.out.println("Venda com o ID-" + idVenda + " não foi encontrada");
				return;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Erro: Digite um ID numérico válido.");
		}
		
	}
}
