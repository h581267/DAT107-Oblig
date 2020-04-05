package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPU");

	public void lagreNyAvdeling(Avdeling ny) {

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

	public Avdeling finnAvdelingMedPk(int pk) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Avdeling.class, pk);

		} finally {
			em.close();
		}
	}

}