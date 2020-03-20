package com.lucas.api.models;

import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	private String saleId;
	private List<ItemDeVenda> listaDeItens = new ArrayList<ItemDeVenda>();
	
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public List<ItemDeVenda> getListaDeItens() {
		return listaDeItens;
	}
	public void setListaDeItens(List<ItemDeVenda> listaDeItens) {
		this.listaDeItens = listaDeItens;
	}
	

}
