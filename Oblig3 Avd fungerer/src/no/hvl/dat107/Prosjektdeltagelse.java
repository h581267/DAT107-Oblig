package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3")
@IdClass(ProsjektdeltagelsePK.class)
public class Prosjektdeltagelse {

	private int timer;

	@Id
	@ManyToOne
	@JoinColumn(name = "ansattId")
	private Ansatt ansatt;

	@Id
	@ManyToOne
	@JoinColumn(name = "prosjektId")
	private Prosjekt prosjekt;

	public Prosjektdeltagelse() {
	}

	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, int timer) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.timer = timer;

		ansatt.leggTilProsjektdeltagelse(this);
		prosjekt.leggTilProsjektdeltagelse(this);
	}

	public void skrivUt(String innrykk) {
		System.out.printf("%sDeltagelse: %s %s, %s, %d timer", innrykk, ansatt.getFornavn(), ansatt.getEtternavn(),
				prosjekt.getNavn(), timer);
	}
	
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public void leggTilTimer(int timer) {
		this.timer += timer;
	}
}
