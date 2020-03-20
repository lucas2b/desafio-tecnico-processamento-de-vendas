package com.lucas.api.processador;

import java.util.ArrayList;
import java.util.List;

import com.lucas.api.models.Cliente;
import com.lucas.api.models.ItemDeVenda;
import com.lucas.api.models.Venda;
import com.lucas.api.models.Vendedor;

public class Processador {
	
	private List<Cliente>  listaClientes   = new ArrayList<Cliente>();
	private List<Vendedor> listaVendedores = new ArrayList<Vendedor>();
	
	//Controles para encontrar maior vend
	private String idVendaMaior    = "";
	private float  acumMaior 	   = 0;
	private float  valorMaiorVenda = 0;
	
	
	//Controles para encontrar pior vendedor
	private String  nomePiorVendedor 			 = "";
	private float   acumMenor 					 = 0;
	private float   menorSomaDeVendasPorVendedor = 0;
	private boolean flag 						 = true;
	
	public void parserLinhaEntrada(String linha) {
		
		String[] split = linha.split("ç");
		
		switch (split[0]) {
		case "001":
			salvarVendedorEmDto(split);
			break;
			
		case "002":
			salvarClienteEmDto(split);
			break;
			
		case "003":
			salvarVendaEmDto(split);
			break;
		
		default:
			break;
		}
		
		
	}
	
	
	//------------------ Métodos de Parsing do arquivo para DTOs -------------------
	
	private void salvarVendedorEmDto(String[] vendedorSplitted) {
		Vendedor vendedor = new Vendedor();
		vendedor.setCpf(vendedorSplitted[1]);
		vendedor.setName(vendedorSplitted[2]);
		vendedor.setSalary(vendedorSplitted[3]);
		
		listaVendedores.add(vendedor);
		
	}
	
	private void salvarClienteEmDto(String[] clienteSplitted) {
		Cliente cliente = new Cliente();
		cliente.setCnpj(clienteSplitted[1]);
		cliente.setName(clienteSplitted[2]);
		cliente.setBusinessArea(clienteSplitted[3]);
		
		listaClientes.add(cliente);
	}
	
	
	private void salvarVendaEmDto(String[] vendaSplitted) {
		
		Venda venda = new Venda();
		venda.setSaleId(vendaSplitted[1]);
		
		//Tratamento da posição 2 do array.
		
		String   stringItensDaVenda = vendaSplitted[2].substring(1, vendaSplitted[2].length()-1); //retirando colchetes
		String[] arrayListaDeVendas = stringItensDaVenda.split(","); //separando propriedades de uma venda por vírgula
		
		for(String item : arrayListaDeVendas) {
			
			String[] itemSplitted = item.split("-"); //separando ítens por traço
			
			ItemDeVenda itemDeVenda = new ItemDeVenda();
			itemDeVenda.setItemId(itemSplitted[0]);
			itemDeVenda.setItemQuantity(Integer.valueOf(itemSplitted[1]));
			itemDeVenda.setItemPrice(Float.valueOf(itemSplitted[2]));
			
			
			venda.getListaDeItens().add(itemDeVenda);
		}
		
		//Fim do tratamento
		
		
		String nomeDoVendedor = vendaSplitted[3];
		
		//Procurando vendedor pelo nome para adicionar sua venda
		
		listaVendedores.forEach(vendedor -> {
			if(vendedor.getName().equals(nomeDoVendedor))
				vendedor.getListaDeVendas().add(venda);
		});
	}
	
	
	//------------------------ Métodos de Cálculos ------------------------
	
	public int quantidadeDeClientes() {
		return listaClientes.size();
	}
	
	public int quantidadeDeVendedores() {
		return listaVendedores.size();
	}
	
	public String idDaVendaMaisCara() {
		
		listaVendedores.forEach(vendedor -> {
			
			vendedor.getListaDeVendas().forEach(venda -> {
				
				venda.getListaDeItens().forEach(item -> {
					acumMaior += item.getItemPrice() * item.getItemQuantity();
				});
				
				if(acumMaior > valorMaiorVenda) {
					valorMaiorVenda =  acumMaior;
					idVendaMaior 	 = venda.getSaleId();
				}
				
				acumMaior = 0;
			});
		});

		return idVendaMaior;
	}
	
	public String piorVendedor() {
		
		listaVendedores.forEach(vendedor -> {
			vendedor.getListaDeVendas().forEach(venda -> {
				
				venda.getListaDeItens().forEach(item -> {
					acumMenor += item.getItemPrice() * item.getItemQuantity();
					
				});
			});
			
			if(flag) {
				menorSomaDeVendasPorVendedor = acumMenor;
				flag = false;
				
			}else if(acumMenor < menorSomaDeVendasPorVendedor) {
				menorSomaDeVendasPorVendedor = acumMenor;
				nomePiorVendedor = vendedor.getName();
				
			}
			
			acumMenor = 0;
		});
		
		return nomePiorVendedor;
	}
	
	//---------------------- Métodos para Printar DTOs -------------------------
	
	public void printarClientes() {
		listaClientes.forEach(cliente -> {
			System.out.println("Cliente:");
			System.out.println("Nome: " + cliente.getName());
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Business Area: " + cliente.getBusinessArea());
			System.out.println();
		});
	}
	
	public void printarVendedoresESuasVendas() {
		
		listaVendedores.forEach(vendedor -> {
			System.out.println("Vendedor: ");
			System.out.println("Nome: "+vendedor.getName());
			System.out.println("CPF:" + vendedor.getCpf());
			System.out.println("Salário:" + vendedor.getSalary());
			
			System.out.println("Vendas deste vendedor:");
			vendedor.getListaDeVendas().forEach(venda -> {
				
				System.out.print("Id de venda:"  + venda.getSaleId());
				
				venda.getListaDeItens().forEach(item -> {
					System.out.print("(Item ID:" 		+ item.getItemId());
					System.out.print(", Item quantity:" + item.getItemQuantity());
					System.out.print(", Item Price:" 	+ item.getItemPrice() + ")");
				});
				
				System.out.println();
				
				
			});
			
			System.out.println();
			
		});
	}
	

}
