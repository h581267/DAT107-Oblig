package no.hvl.dat107;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3")

public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattId;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansattdato;
	private String stilling;
	private double mndlonn;
	private int avdNr;
	
	@ManyToOne
	@JoinColumn(name = "avdnr", referencedColumnName = "avdnr")
	private Avdeling avdeling;

	public Ansatt() {
	}

	public Ansatt(String fornavn, String etternavn, String stilling, double mndlonn, int avdNr) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.brukernavn = (fornavn.substring(0, 2) + etternavn.substring(0, 2)).toUpperCase();
		this.ansattdato = LocalDate.now();
		this.stilling = stilling;
		this.mndlonn = mndlonn;
		this.avdNr = avdNr;

	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelsesdato() {
		return ansattdato;
	}

	public void setAnsettelsesdato(LocalDate ansettelsesdato) {
		this.ansattdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public double getMndLonn() {
		return mndlonn;
	}

	public void setMndLonn(double mndlonn) {
		this.mndlonn = mndlonn;
	}

	public int getAvdNr() {
		return avdNr;
	}

	public void setAvdNr(int avdNr) {
		this.avdNr = avdNr;
	}

	public int getAnsattId() {
		return ansattId;
	}

	public void setAnsattId(int ansattId) {
		this.ansattId = ansattId;
	}

	@Override
	public String toString() {
		return "AnsattId: " + ansattId + "\nBrukernavn: " + brukernavn + "\nNavn: " + fornavn + " " + etternavn
				+ "\nAnsattdato: " + ansattdato + "\nMånedslønn: " + mndlonn + "\nAvdelingsNr: " + avdNr + "\n\n";

	}

}