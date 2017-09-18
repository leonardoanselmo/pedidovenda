package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.model.TipoPessoa;
import com.algaworks.pedidovenda.service.CadastroClienteService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Endereco novoEndereco;

	@Inject
	private CadastroClienteService cadastroClienteService;
	
	public CadastroClienteBean() {
		limpar();
	}

	public void inicializar() {

		//novoEndereco = new Endereco();

	}

	private void limpar() {
		cliente = new Cliente();
		novoEndereco = new Endereco();		
	}

	public void salvar() {

		cliente = cadastroClienteService.salvar(cliente);

		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void adicionarEndereco(){
		novoEndereco.setCliente(cliente);
		cliente.getEnderecos().add(novoEndereco);		
		novoEndereco = new Endereco();		
	}
	
	public void removerEndereco(){
		this.cliente.getEnderecos().remove(novoEndereco);
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

	public Endereco getNovoEndereco() {
		return novoEndereco;
	}

	public void setNovoEndereco(Endereco novoEndereco) {
		this.novoEndereco = novoEndereco;
	}

	public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }
	

}
