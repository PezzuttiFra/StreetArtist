package ArteVera;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("quanti clienti vuoi arrivino oggi?");
	    Scanner S = new Scanner(System.in);
	    int totaleClienti = S.nextInt();
	    S.close();
		
		for (int i = 0; i < totaleClienti; i++) {
			Cliente c = new Cliente();
			c.start();
		}
	}

}
