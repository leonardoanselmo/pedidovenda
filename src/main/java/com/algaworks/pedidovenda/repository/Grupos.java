package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.repository.filter.GrupoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Grupo guardar(Grupo grupo) {		
		return manager.merge(grupo);		
	}
	
	@Transactional
	public void remover(Grupo grupo) {
		try {
			grupo = porId(grupo.getId());
			manager.remove(grupo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo não pode ser excluído.");
		}
	}

	public Grupo porNome(String nome) {
		try {
			return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Grupo> raizes() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> filtrados(GrupoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);
				
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}
}
