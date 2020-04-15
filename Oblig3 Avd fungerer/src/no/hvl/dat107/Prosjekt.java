package no.hvl.dat107;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3")

public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektId;
	private String navn;

	@OneToMany(mappedBy="prosjekt")
	private List<Prosjektdeltagelse> deltagere;

	
	public Prosjekt() {}
	
	public Prosjekt(String navn) {
		this.navn = navn;
	}
	
	public void skrivUt(String innrykk) {
		System.out.printf("%sProsjekt nr %d: %s", innrykk, prosjektId, navn);
	}

	public void skrivUtMedAnsatte() {
		System.out.println();
		skrivUt("");
		int timer = 0;
		deltagere.forEach(a -> a.skrivUt("\n   "));
		
		for(Prosjektdeltagelse p : deltagere) {
			timer += p.getTimer();
		}
		System.out.println("\nTotalt antall timer i prosjektet: " + timer);
		
	}

	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagere.add(prosjektdeltagelse);
	}

	public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagere.remove(prosjektdeltagelse);
	}

	public int getProsjektId() {
		return prosjektId;
	}

	public String getNavn() {
		return navn;
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagere;
	}
}
