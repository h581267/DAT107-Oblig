package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProsjektId;
	private String navn;
	
	@ManyToMany
	@JoinTable(
			name = "oblig3.Prosjektdeltagelse",
			joinColumns = @JoinColumn(name = "ProsjektId"),
			inverseJoinColumns = @JoinColumn(name = "ansattId")
			)
	List<Ansatt> ansatte;
	
	
	public Prosjekt(String navn) {
		this.navn = navn;
	}
	
	public void leggTilAnsatt(Ansatt a) {
		ansatte.add(a);
	}
	
	
}
