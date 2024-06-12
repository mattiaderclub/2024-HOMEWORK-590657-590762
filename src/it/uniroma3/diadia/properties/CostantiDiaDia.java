package it.uniroma3.diadia.properties;

public class CostantiDiaDia {

	/* messaggio di benvenuto al gioco per il giocatore */
	static final public String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	/* numero massimo di uscite disponibili in una stanza qualsiasi */
	static final public int NUMERO_MASSIMO_DIREZIONI = 4;
	
	/* numero massimo di attrezzi contenuti in una stanza qualsiasi */
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;
	
	/* soglia magica default di una stanza magica */
	static final public int SOGLIA_MAGICA_DEFAULT = 3;
	
	/* elenco comandi stampati dal comando 'aiuto' */
	static final public String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", 
			"posa", "guarda", "interagisci", "regala", "saluta"};
	
	/* messaggio inviato al giocatore se interagisce in una stanza senza personaggi */
	static final public String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	
	/* messaggio inviato al giocatore se attrezzo che si vuole regalare non si trova in borsa */
	static final public String ATT_NON_IN_BORSA = "Attrezzo da regalare assente in borsa!";
	
	/* messaggio inviato al giocatore se non specifica l'attrezzo da regalare */
	static final public String ATT_NON_SPECIFICATO = "Non hai specificato cosa vuoi regalare...";
	
	/* messaggio inviato al giocatore se vuole regalare qualcosa in una stanza senza personaggi */
	static final public String MESSAGGIO_A_CHI = "Nella stanza non ci sta nessuno a cui regalare qualcosa";
	
	/* messaggio inviato al giocatore se vuole salutare in una stanza senza personaggi */
	static final public String MESSAGGIO_CHI = "Nella stanza non ci sta nessuno da salutare...";
	
	/* peso massimo borsa del giocatore */
	static final public int DEFAULT_PESO_MAX_BORSA = 10;
	
	/* cfu iniziali del giocatore */
	static final public int CFU_INIZIALI = 20;
	
	/* nome del cibo preferito del cane */
	static final public String CIBO_PREFERITO = "osso";
	
	/* messaggio inviato dal cane al giocatore quando morde */
	static final public String MESSAGGIO_MORSO = "Woof Woof non tornare più oppure perderai altri cfu!";
	
	/* messaggio inviato dal mago quando dona un attrezzo al giocatore */
	static final public String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	
	/* messaggio inviato dal mago se non possiede attrezzi da donare al giocatore */
	static final public String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	/* messaggio inviato dalla strega quando sposta il giocatore nella stanza con più attrezzi : è stata salutata */
	static final public String MESSAGGIO_PIU_ATTREZZI = "Grazie per avermi salutato! " +
			"Nella stanza adiacente con più attrezzi verrai spostato!";

	/* messaggio inviato dalla strega quando sposta il giocatore nella stanza con più attrezzi : non è stata salutata */
	static final public String MESSAGGIO_MENO_ATTREZZI = "Non mi hai salutato! " +
			"Nella stanza adiacente con meno attrezzi verrai catapultato!";
}
