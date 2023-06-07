# ArteVera

Questo programma simula l'arrivo di clienti in un negozio d'arte per farsi fare un ritratto.

## Classi

Il programma è composto da tre classi: `Main`, `Data` e `Cliente`.

### Main

La classe `Main` contiene il metodo `main` che avvia il programma. Chiede all'utente quanti clienti vuole arrivino oggi e crea un nuovo oggetto `Cliente` per ogni cliente. Ogni oggetto `Cliente` viene avviato come un thread.

### Data

La classe `Data` contiene variabili statiche condivise tra tutti gli oggetti `Cliente`. Queste includono l'ID del cliente corrente, i semafori per il ritratto e le sedie e una lista di nomi da assegnare ai clienti.

### Cliente

La classe `Cliente` estende la classe `Thread` e rappresenta un singolo cliente. Ogni cliente ha un tempo di arrivo, un tempo di attesa massimo, un nome e un ID. Quando viene avviato il thread del cliente, cerca di acquisire una sedia. Se non riesce ad acquisirla entro il tempo massimo di attesa, il cliente se ne va insoddisfatto. Altrimenti, cerca di acquisire il semaforo del ritratto e viene ritratto dall'artista. Una volta terminato il ritratto, il semaforo viene rilasciato.

## Utilizzo

Per utilizzare questo programma, eseguire la classe `Main`. Verrà chiesto all'utente di inserire il numero di clienti che vuole arrivino oggi. Inserire un numero intero e premere invio. Il programma simulerà l'arrivo dei clienti e la loro interazione con l'artista.
