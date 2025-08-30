package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Cliente;
import entities.Produto;
import entities.ProdutoNaoPerecivel;
import entities.ProdutoPerecivel;

public class Main {

	static List<Produto> produtos = new ArrayList<>();
	static List<Cliente> clientes = new ArrayList<>();

	private static int proximoIdProduto = 1;
	private static int proximoIdCliente = 1;

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
}
