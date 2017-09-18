package com.algaworks.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PesquisaClientesBean1 {

private List<Integer> clientes;
	
	public PesquisaClientesBean1() {
		clientes = new ArrayList<>();
		clientes.add(1);
	}

	public List<Integer> getClientes() {
		return clientes;
	}
	
}

