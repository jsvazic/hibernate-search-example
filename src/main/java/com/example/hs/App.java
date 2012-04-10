package com.example.hs;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 * Initialize our index!
 */
public class App {
	public static void main(String[] args) throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory("com.example.mysql").createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
	}
}
