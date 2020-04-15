package no.hvl.dat107;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Meny {

	private Tekstgrensesnitt tekstgr;
	private AnsattDAO ansDAO;
	private AvdelingDAO avdDAO;
	private ProsjektDAO proDAO;
	private boolean stopp;
	private Scanner tastatur;

	public Meny(AnsattDAO ansDAO, AvdelingDAO avdDAO, ProsjektDAO proDAO) {
		tekstgr = new Tekstgrensesnitt(tastatur);
		this.ansDAO = ansDAO;
		this.avdDAO = avdDAO;
		this.proDAO = proDAO;
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
				"\n1.Legg til ansatt\n2.Skriv ut alle ansatte\n3.Søk etter brukernavn\n4.Søk etter ansatt id\n5.Oppdater lønn\n6.Oppdater Avdeling for ansatt"
				+ "\n7.Legg til avdeling\n8.Søk etter avdeling id\n9.Skriv ut alle ansatte i avdeling\n10.Oppdater SjefID\n11.Legg inn prosjekt\n12.Registrer projektdeltakelse"
				+ "\n13.Legg til timer i prosjekt\n14.Skriv ut prosjekt\n16.Avslutt");
		
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
			proDAO.lagreNyttProsjekt(tekstgr.lesInnProsjekt());
			action(tastatur);
			break;
		case 12:
			System.out.print("Skriv inn AnsattID: ");
			int idA = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn ProsjektID den ansatte skal knyttes til: ");
			int idP = Integer.parseInt(tastatur.nextLine());
			ansDAO.registrerProsjektdeltagelse(idA, idP);
			action(tastatur);
			break;
		case 13:
			System.out.print("Skriv inn AnsattID: ");
			int idA1 = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn ProsjektID til prosjektet timene skal legges til: ");
			int idP1 = Integer.parseInt(tastatur.nextLine());
			System.out.print("Skriv inn antall timer: ");
			int timer = Integer.parseInt(tastatur.nextLine());
			ansDAO.oppdaterTimerProsjektForAnsatt(idA1, idP1, timer);
			action(tastatur);
			break;
		case 14:
			System.out.print("Skriv inn ProsjektID: ");
			int idP2 = Integer.parseInt(tastatur.nextLine());
			proDAO.skrivUtProsjekt(idP2);
			action(tastatur);
			break;
		case 16:
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
