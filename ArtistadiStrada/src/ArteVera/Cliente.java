package ArteVera;

import java.util.Random;

public class Cliente extends Thread{
	
	public long tempoArrivo = System.currentTimeMillis(); //istante arrivo
	Random rand = new Random();
	public int tempoAttesaMs = 1000;
	public String nome = "";
	public int ID = 0;
	private int maxTempoPerRitrarre = 1000;
	private int minTempoPerRitrarre = 300;
	private int maxAttesa = 2000;
	private int tempoPazienzaPaziente = rand.nextInt(maxAttesa);

	
	public Cliente(){
		this.nome = Data.listaNomi[rand.nextInt(10)];
		this.ID = Data.IDclienti;
		Data.IDclienti++;
	}
	
	public void run() {
		
	try {
		Data.sedie.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	long tempoTrascorso = System.currentTimeMillis() - tempoArrivo;
	if(tempoTrascorso > tempoPazienzaPaziente) {
		Data.sedie.release();
		System.out.println("Il cliente " + this.nome+ " con ID: "+ this.ID +" se ne va insoddisfatto");
		return;
	}
	
	try {
		Data.ritratto.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Data.sedie.release();
	
	System.out.println("L'artista ha ritratto il signor "+ this.nome + " ID: " + this.ID);
	int attesa = rand.nextInt(maxTempoPerRitrarre - minTempoPerRitrarre) + minTempoPerRitrarre;
		try {
		sleep(attesa);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Data.ritratto.release();//fine con rilascio
	return;

	}
}
