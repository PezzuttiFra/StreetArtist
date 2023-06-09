package ArteVera;

import java.util.concurrent.Semaphore;

public class Data {

	public static int IDclienti = 0;
	public static Semaphore ritratto = new Semaphore(1);
	public static Semaphore sedie = new Semaphore(5);	
	
	public static String[] listaNomi = {
			"Giovanni",
			"Mattia",
			"Pippo",
			"Pluto",
			"Piermentosfracellozzi",
			"Eliosterconi",
			"Casimirogrumaioli",
			"Sandrostecchino",
			"Girolamoseghetti",
			"Agneieszka"
	};
}
