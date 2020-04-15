package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.Prosjekt;

public class ProsjektDAO {

	private EntityManagerFactory emf;

	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("prosjektPU");
	}

	public Prosjekt finnProsjektMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt prosjekt = null;
		try {
			prosjekt = em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
		return prosjekt;
	}

	public void lagreNyttProsjekt(Prosjekt ny) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(ny);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	public void skrivUtProsjekt(int id) {

		EntityManager em = emf.createEntityManager();
		Prosjekt prosjekt = null;
		try {
			
			prosjekt = em.find(Prosjekt.class, id);
			prosjekt.skrivUtMedAnsatte();

		} finally {
			em.close();
		}
	}

}