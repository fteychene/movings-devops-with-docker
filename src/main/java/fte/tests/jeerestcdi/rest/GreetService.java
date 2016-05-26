package fte.tests.jeerestcdi.rest;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fte.tests.jeerestcdi.rest.entity.Greet;
import fte.tests.jeerestcdi.rest.events.CreationEvent;

public class GreetService {
	
	@PersistenceContext(unitName="datasource")
	private EntityManager em;

	@Inject
	@CreationEvent
	Event<Greet> greetCreation;
	
	@Transactional
	public Greet greet(String name) {
		Greet greet = new Greet();
		greet.setName(name);
		em.persist(greet);
		greetCreation.fire(greet);
		return greet;
	}
	
	@SuppressWarnings("unchecked")
	public List<Greet> list() {
		return em.createQuery("from Greet").getResultList();
	}

}