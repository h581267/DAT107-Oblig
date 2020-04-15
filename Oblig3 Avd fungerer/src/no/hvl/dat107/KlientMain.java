package no.hvl.dat107;

public class KlientMain {

	public static void main(String[] args) {
		
		AnsattDAO ans = new AnsattDAO();
		AvdelingDAO avd = new AvdelingDAO();
		ProsjektDAO pro = new ProsjektDAO();
		Meny meny = new Meny(ans, avd, pro);
		
		meny.start();

	}

}
