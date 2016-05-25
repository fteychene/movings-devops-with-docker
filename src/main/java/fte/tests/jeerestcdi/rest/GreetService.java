package fte.tests.jeerestcdi.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fte.tests.jeerestcdi.rest.entity.Greet;

public class GreetService {
	
	@PersistenceContext(unitName="datasource")
	private EntityManager em;
	
	@Transactional
	public String greet(String name) {
		Greet greet = new Greet();
		greet.setName(name);
		em.persist(greet);
		return "Hello "+ name;
	}
	
	public List<Greet> list() {
		return em.createQuery("from Greet").getResultList();
	}

}