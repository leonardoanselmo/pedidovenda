package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Grupos;
import com.algaworks.pedidovenda.service.CadastroUsuarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Grupo grupoSelecionado;
	private List<Grupo> gruposRaizes = new ArrayList<>();	

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private Grupos grupos;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void inicializar() {

		if (FacesUtil.isNotPostback()) {
			gruposRaizes = grupos.raizes();			
		}

	}

	private void limpar() {
		usuario = new Usuario();		
		gruposRaizes = new ArrayList<Grupo>();		
		grupoSelecionado = null;
	}

	public void adicionarGrupo() {		
		this.usuario.getGrupos().add(grupoSelecionado);		
	}

	public void salvar() {
		usuario = cadastroUsuarioService.salvar(usuario);

		limpar();
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}

	public void removerGrupo() {
		this.usuario.getGrupos().remove(grupoSelecionado);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}

	public void setGruposRaizes(List<Grupo> gruposRaizes) {
		this.gruposRaizes = gruposRaizes;
	}	

}
