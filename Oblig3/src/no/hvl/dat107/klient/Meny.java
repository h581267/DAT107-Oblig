package no.hvl.dat107.klient;

import java.util.Scanner;

import javax.swing.JOptionPane;

import no.hvl.dat107.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;

public class Meny {

	private Tekstgrensesnitt tekstgr;
	private AnsattDAO ansDAO;
	private AvdelingDAO avdDAO;
	private boolean stopp;
	private Scanner tastatur;

	public Meny(AnsattDAO ansDAO, AvdelingDAO avdDAO) {
		tekstgr = new Tekstgrensesnitt(tastatur);
		this.ansDAO = ansDAO;
		this.avdDAO = avdDAO;
		tastatur = new Scanner(System.in);
	}

	public void start() {
		stopp = false;

		while (!stopp) {
			action(tastatur);
		}
	}

	public void action(Scanner tastatur) {

		System.out.println(
				"1.Legg til ansatt\n2.Skriv ut alle ansatte\n3.Søk etter brukernavn\n4.Søk etter ansatt id\n5.Oppdater lønn\n6.Oppdater Avdeling for ansatt"
				+ "\n7.Legg til avdeling\n8.Søk etter avdeling id\n9.Skriv ut alle ansatte i avdeling\n10.Oppdater SjefID\n11.Avslutt");
		
		System.out.println("Skriv inn hva du ønsker å gjøre: ");
		int a = Integer.parseInt(tastatur.nextLine());

		switch (a) {
		case 1:
			ansDAO.lagreNyAnsatt(tekstgr.lesInnAnsatt());
			action(tastatur);
			break;
		case 2:
			tekstgr.visAlleAnsatte(ansDAO);
			action(tastatur);
			break;
		case 3:
			System.out.print("Skriv inn søkeord til brukernavn: ");
			String brukernavn = tastatur.nextLine();
			tekstgr.visAnsattBrukernavn(ansDAO, brukernavn);
			action(tastatur);
			break;
		case 4:
			System.out.print("Skriv inn søkeord til id: ");
			int id = Integer.parseInt(tastatur.nextLine());
			tekstgr.visAnsattPk(ansDAO, id);
			action(tastatur);
			break;
		case 5:
			System.out.print("Skriv inn id på ansatt: ");
			int id1 = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn ny lønn: ");
			double lønn = Double.parseDouble(tastatur.nextLine());
			tekstgr.oppdaterLonn(ansDAO, id1, lønn);
			action(tastatur);
			break;
		case 6:	
			System.out.print("Skriv inn ansatt id: ");
			int id5 = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn ny avdeling id: ");
			int avdnr = Integer.parseInt(tastatur.nextLine());
			tekstgr.oppdaterAvdnr(ansDAO, id5, avdnr);
			action(tastatur);
			break;
		case 7:
			avdDAO.lagreNyAvdeling(tekstgr.lesInnAvdeling());
			action(tastatur);
			break;
		case 8:
			System.out.print("Skriv inn søkeord til id: ");
			int id2 = Integer.parseInt(tastatur.nextLine());
			tekstgr.visAvdelingPk(avdDAO, id2);
			action(tastatur);
			break;
		case 9:
			System.out.print("Skriv inn id til Avdelingen: ");
			int id3 = Integer.parseInt(tastatur.nextLine());
			tekstgr.visAlleAnsatteIAvdeling(id3,avdDAO, ansDAO);
			action(tastatur);
			break;
		case 10:
			System.out.print("Skriv inn id til Avdeling: ");
			int id4 = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn sjefId: ");
			int sjefId = Integer.parseInt(tastatur.nextLine());
			tekstgr.oppdaterSjefId(avdDAO, id4, sjefId);
			action(tastatur);
			break;
		case 11:
			stopp = true;
			tastatur.close();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Ugyldig verdi");
			action(tastatur);
			break;

		}
	}

}
