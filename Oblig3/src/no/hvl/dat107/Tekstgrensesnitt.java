package no.hvl.dat107;

import java.util.Scanner;

public class Tekstgrensesnitt {

	private Scanner tastatur;
	
	public Tekstgrensesnitt(Scanner tastatur) {
		this.tastatur = tastatur;
	}
	
	public Ansatt lesInnAnsatt(){
		
		String fornavn; 
		String etternavn; 
		double mndLonn; 
		String stilling;
		int avdeling;
		
		tastatur = new Scanner(System.in);
		
		System.out.print("Skriv inn fornavn: ");
		fornavn = tastatur.nextLine();

		System.out.print("Skriv inn etternavn: ");
		etternavn = tastatur.nextLine();

		System.out.print("Skriv inn månedslønn: ");
		mndLonn = Double.parseDouble(tastatur.nextLine());
		
		System.out.print("Skriv inn stilling: ");
		stilling = tastatur.nextLine();
		
		System.out.print("Skriv inn avdNr: ");
		avdeling = Integer.parseInt(tastatur.nextLine());
		
		Ansatt retur = new Ansatt(fornavn, etternavn, stilling, mndLonn, avdeling);
		
		return retur;
	}
	
	public Avdeling lesInnAvdeling() {
		String navn;
		int sjefid;
		
		tastatur = new Scanner(System.in);
		
		System.out.print("Skriv inn avdelingsnavn: ");
		navn = tastatur.nextLine();
		
		System.out.print("Skriv inn sjefID: ");
		sjefid = Integer.parseInt(tastatur.nextLine());
		
		Avdeling retur = new Avdeling(navn, sjefid);
		
		return retur;
	}
	
	public void visAlleAnsatte(AnsattDAO ans) {
		System.out.print(ans.finnAlleAnsatte());
	}
	
	public void visAnsattBrukernavn(AnsattDAO ans, String brukernavn) {
		System.out.print(ans.finnAnsattMedBrukernavn(brukernavn));
	}
	
	public void visAnsattPk(AnsattDAO ans, int id) {
		System.out.print(ans.finnAnsattMedPk(id));
	}
	
	public void visAvdelingPk(AvdelingDAO avd, int id) {
		System.out.print(avd.finnAvdelingMedPk(id));
	}
	
	public void oppdaterLonn(AnsattDAO ans, int id, double nyLonn) {
		ans.oppdaterLonn(id, nyLonn);
	}
	
}
