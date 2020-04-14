package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Avdeling;

public class AvdelingDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("avdelingPU");

	public void lagreNyAvdeling(Avdeling ny) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(ny);

			tx.commit();
			
			AnsattDAO ans = new AnsattDAO();
			ans.oppdaterAvdeling(ny.getSjefId(), ny.getAvdnr());

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
	
public void oppdaterSjefid(int id, int sjefId) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Avdeling managedAvdeling = em.find(Avdeling.class, id);			
			managedAvdeling.setSjefId(sjefId);
			
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

}