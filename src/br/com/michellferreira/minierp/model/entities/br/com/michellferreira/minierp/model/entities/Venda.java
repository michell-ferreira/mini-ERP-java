package br.com.michellferreira.minierp.model.entities;

import java.time.LocalDate;
import java.util.List;

import br.com.michellferreira.minierp.model.enums.StatusVenda;

public class Venda {

	private int id;
	private Cliente cliente;
	private List<Produto> produtosVendidos;
	private LocalDate dataVenda;
	private double valorTotal;
	private StatusVenda status;

	public Venda(int id, Cliente cliente, List<Produto> produtosVendidos) {
		this.id = id;
		this.cliente = cliente;
		this.produtosVendidos = produtosVendidos;

		this.dataVenda = LocalDate.now();
		this.valorTotal = calcularValorTotal();
		this.status = StatusVenda.ATIVO;
	}

	public int getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Produto> getProdutosVendidos() {
		return produtosVendidos;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void cancelarVenda() {
		this.status = StatusVenda.CANCELADO;
	}

	public double calcularValorTotal() {
		double total = 0.0;
		for (Produto p : this.produtosVendidos) {
			total += p.getPreco();
		}
		return total;
	}

}
