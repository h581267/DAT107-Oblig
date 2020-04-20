package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
			Avdeling avdOld = em.find(Avdeling.class, managedAnsatt.getAvdNr());
			Avdeling avdNy = em.find(Avdeling.class, avdnr);
			

			if (avdOld.getSjefId() == id) {
				System.out.println("Kan ikke endre fordi den ansatte er sjef i avdeling");
			} else {
				managedAnsatt.setAvdNr(avdnr);
				avdOld.slettAnsatt(managedAnsatt);
				avdNy.leggTilAnsatt(managedAnsatt);
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

	public void registrerProsjektdeltagelse(int aid, int pid) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, aid);
			Prosjekt p = em.find(Prosjekt.class, pid);

			Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, 0);

			em.persist(pd);

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

	public void oppdaterTimerProsjektForAnsatt(int idA, int idB, int timer) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			ProsjektdeltagelsePK pk = new ProsjektdeltagelsePK(idA, idB);
			Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, pk);

			pd.leggTilTimer(timer);
		
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