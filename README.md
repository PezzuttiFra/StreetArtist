# Problema di multithreading, "Artista di strada"

## Main:
```java
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
```
Questa è la classe main della mia soluzione, esegue in modo consecutivo 2 azioni principali:

- Richiede il numro totale di clienti che si vuole avere tramite uno scanner
- Esegue un ciclo for che inizzializza(e fa partire) il numero di clienti richiesto

## Data:
```java
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
```
La classe Data contiene i semafori , la lista dei nomi e l'ID, sono tutti statici di modo che possano averne accesso tutti i clienti in modo semplice 


## Cliente:
```java
public class Cliente extends Thread{
	public long tempoArrivo = System.currentTimeMillis(); //istante arrivo
	Random rand = new Random();
	public int tempoAttesaMs = 1000;
	public String nome = "";
	public int ID = 0;
	private int maxTempoPerRitrarre = 1000;
	private int minTempoPerRitrarre = 800;
	private int maxAttesa = 800;
	
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
	if(tempoTrascorso > maxAttesa) {
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
```
# @Override:
Avendo fatto l'override del contruttore di "Cliente" ad ogniuno di questi viene assegnato un nome casuale fra l'elenco presente nella classe Data e un ID procedurale (anche essi conteggiati nella classe Data)

```java
    @override
	public Cliente(){
		this.nome = Data.listaNomi[rand.nextInt(10)];
		this.ID = Data.IDclienti;
		Data.IDclienti++;
	}
```

# Semaforo:

Quando viene acquisito il semaforo delle sedie (al massimo da 5 clienti contemporaneamente) il cliente che lo ha acquisito controllerà se il tepmpo che ha aspettato da quando è stato istanziato è massimo del suo tempo massimo di attesa (tempoPazienzaPaziente) che è pari an un numero casuale che può avere come valore massimo il valore maxAttesa.
Se il tempo di attesa è quindi troppo lungo il cliente se ne andrà insottisatto, sennò cercherà di acquisire il semaforo dell'artistaper farsi ritrarre.

