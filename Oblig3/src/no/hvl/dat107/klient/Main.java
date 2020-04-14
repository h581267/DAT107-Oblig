package no.hvl.dat107.klient;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class Main {

	public static void main(String[] args) {
	
		AnsattDAO ansDAO = new AnsattDAO();
		AvdelingDAO avdDAO = new AvdelingDAO();
		Ansatt Seb = new Ansatt("Sebastian", "Kiese", "Sjef", 400000.0, 1);
		Avdeling sso = new Avdeling("SSO", 1);
	
		avdDAO.finnAvdelingMedPk(1);
		
		System.out.println(ansDAO.finnAlleAnsatte());
	}

}
