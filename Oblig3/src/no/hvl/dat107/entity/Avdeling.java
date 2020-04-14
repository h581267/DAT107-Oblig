package no.hvl.dat107.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Avdeling {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdnr;
	private String navn;
	private int sjefId;
	
	@OneToMany
	(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List<Ansatt> ansatte;
	
	public Avdeling() {}
	
	public Avdeling(String navn, int sjefId) {
		this.navn = navn;
		this.sjefId = sjefId;
	}
	
	public int getAvdnr() {
		return avdnr;
	}

	public void setAvdnr(int avdnr) {
		this.avdnr = avdnr;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getSjefId() {
		return sjefId;
	}

	public void setSjefId(int sjefId) {
		this.sjefId = sjefId;
	}
	
	public List<Ansatt> getAnsatte(){
		return ansatte;
	}
	
	public void leggTilAnsatt(Ansatt ans) {
		ansatte.add(ans);
	}
	
	public String toString() {
		return avdnr + ", " + navn + "\nSjefId:" + sjefId + "\n";
	}
	
}
