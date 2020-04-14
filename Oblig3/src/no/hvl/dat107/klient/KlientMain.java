package no.hvl.dat107.klient;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;

public class KlientMain {

	public static void main(String[] args) {
		
		AnsattDAO ans = new AnsattDAO();
		AvdelingDAO avd = new AvdelingDAO();
		Meny meny = new Meny(ans, avd);
		
		meny.start();

	}

}
