package com.lucas.api.models;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
	
	private String cpf;
	private String name;
	private String salary;
	private List<Venda> listaDeVendas = new ArrayList<Venda>();
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public List<Venda> getListaDeVendas() {
		return listaDeVendas;
	}
	public void setListaDeVendas(List<Venda> listaDeVendas) {
		this.listaDeVendas = listaDeVendas;
	}
	
	@Override
	public String toString() {
		return "Vendedor [cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]"; 
	}

}
