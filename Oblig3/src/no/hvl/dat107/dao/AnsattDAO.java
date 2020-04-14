package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU");
	}

	public void lagreNyAnsatt(Ansatt ny) {

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

	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t WHERE t.brukernavn LIKE :brukernavn",
					Ansatt.class);
			query.setParameter("brukernavn", brukernavn);

			return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater

		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedPk(int pk) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, pk);

		} finally {
			em.close();
		}
	}

	public List<Ansatt> finnAlleAnsatte() {

		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t", Ansatt.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public void oppdaterStilling(int id, String tekst) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			managedAnsatt.setStilling(tekst);

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

	public void oppdaterLonn(int id, double mndlonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			managedAnsatt.setMndLonn(mndlonn);

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

	public void oppdaterAvdeling(int id, int avdnr) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			AvdelingDAO avdDAO = new AvdelingDAO();
			Ansatt managedAnsatt = em.find(Ansatt.class, id);
			boolean sjef = false;
			Avdeling avd = avdDAO.finnAvdelingMedPk(managedAnsatt.getAvdNr());

			if (avd.getSjefId() == managedAnsatt.getAnsattId()) {
				System.out.println("Kan ikke endre fordi den ansatte er sjef i avdeling");
			} else {
				managedAnsatt.setAvdNr(avdnr);
			}
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

	public void registrerProsjektdeltagelse(int ansattId, int prosjektId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId);
			Prosjekt p = em.find(Prosjekt.class, prosjektId);
			
			a.leggTilProsjekt(p);
			p.leggTilAnsatt(a);

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

	public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			a = em.merge(a);
			p = em.merge(p);
			
			a.leggTilProsjekt(p);
			p.leggTilAnsatt(a);


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