package it.uniroma3.diadia.properties;

public class CostantiCaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	static final public String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	static final public String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	static final public String STANZA_VINCENTE_MARKER = "Vincente:";
	
	/* prefisso della riga contenente la specifica delle stanze buie */
	static final public String STANZE_BUIE_MARKER = "Buie:";
	
	/* prefisso della riga contenente la specifica delle stanze bloccate */
	static final public String STANZE_BLOCCATE_MARKER = "Bloccate:";
	
	/* prefisso della riga contenente la specifica delle stanze magiche */
	static final public String STANZE_MAGICHE_MARKER = "Magiche:";
	
	/* prefisso della riga contenente la specifica dei cani */
	static final public String PERSONAGGI_MARKER = "Personaggi:";
	
	/* prefisso della riga contenente la specifica dei cani */
	static final public String CANI_MARKER = "Cani:";
	
	/* prefisso della riga contenente la specifica dei maghi */
	static final public String MAGHI_MARKER = "Maghi:";
	
	/* prefisso della riga contenente la specifica delle streghe */
	static final public String STREGHE_MARKER = "Streghe:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	static final public String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	static final public String USCITE_MARKER = "Uscite:";
	
	/* descrizione di un monolocale */
	static final public String MONOLOCALE = 
			"Stanze:Atrio\n" +
			"Buie:\n" + 
			"Bloccate:\n" +
			"Magiche:\n" + 
			"Inizio:Atrio\n" + 
			"Vincente:Atrio\n" +
			"Attrezzi:\n" +
			"Cani:\n" +
			"Maghi:\n" +
			"Streghe:\n" +
			"Uscite:\n";
	
	/* descrizione di un bilocale */
	static final public String BILOCALE = 
			"Stanze:Atrio,Biblioteca\n" + 
			"Buie:\n" + 
			"Bloccate:\n" + 
			"Magiche:\n" + 
			"Inizio:Atrio\n" + 
			"Vincente:Biblioteca\n" + 
			"Attrezzi:martello 3 Atrio\n" + 
			"Cani:\n" +
			"Maghi:\n" +
			"Streghe:\n" +
			"Uscite:Biblioteca sud Atrio,Atrio nord Biblioteca\n";
	
	/* descrizione di un trilocale */
	static final public String TRILOCALE = 
			"Stanze:Atrio,Biblioteca,Laboratorio\n" + 
			"Buie:\n" + 
			"Bloccate:\n" + 
			"Magiche:\n" + 
			"Inizio:Atrio\n" + 
			"Vincente:Biblioteca\n" + 
			"Attrezzi:martello 3 Atrio,torcia 1 Atrio,"
					+ "spada 5 Laboratorio,libro 2 Biblioteca\n" +
			"Cani:\n" +
			"Maghi:\n" +
			"Streghe:\n" +
			"Uscite:Atrio est Laboratorio,Atrio nord Biblioteca,"
					+ "Laboratorio ovest Atrio,Biblioteca sud Atrio\n";
	
	/* descrizione di un multilocale contenente stanze buie, magiche e bloccate */
	static final public String MULTILOCALE_CON_STANZE_SPECIALI = 
			"Stanze:Atrio,Biblioteca,Laboratorio\n" + 
			"Buie:Caverna torcia\n" + 
			"Bloccate:Portale nord chiave\n" + 
			"Magiche:Incantesimi 2\n" +
			"Inizio:Atrio\n" + 
			"Vincente:Biblioteca\n" + 
			"Attrezzi:martello 3 Atrio,spada 5 Laboratorio,libro 2 Biblioteca," 
					+ "chiave 4 Caverna,torcia 1 Incantesimi,medaglia 0 Portale\n" +
			"Cani:\n" +
			"Maghi:\n" +
			"Streghe:\n" +
			"Uscite:Atrio sud Caverna,Caverna nord Atrio,Caverna ovest Laboratorio,"
					+ "Laboratorio est Caverna,Laboratorio ovest Incantesimi,"
					+ "Incantesimi est Laboratorio, Incantesimi ovest Portale,"
					+ "Portale est Incantesimi,Portale nord Biblioteca\n";
	
	/* descrizione di un multilocale con stanze buie, magiche, bloccate e creature (maghi, cani, streghe) */
	static final public String MULTILOCALE_CON_CREATURE = 
			"Stanze:Atrio,Biblioteca,Laboratorio\n" + 
			"Buie:Caverna torcia\n" +  
			"Bloccate:Portale nord passepartout\n" + 
			"Magiche:Incantesimi 2\n" +
			"Inizio:Atrio\n" + 
			"Vincente:Biblioteca\n" + 
			"Attrezzi:martello 3 Atrio,spada 5 Laboratorio,libro 2 Biblioteca," 
					+ "chiave 4 Caverna,torcia 1 Incantesimi,passepartout 0 Portale\n" +
			"Cani:Spike Atrio\n" +
			"Maghi:Merlino ascia 7 Laboratorio\n" +
			"Streghe:Befana Caverna\n" +
			"Uscite:Atrio sud Caverna,Caverna nord Atrio,Caverna ovest Laboratorio,"
					+ "Laboratorio est Caverna,Laboratorio ovest Incantesimi,"
					+ "Incantesimi est Laboratorio, Incantesimi ovest Portale,"
					+ "Portale est Incantesimi,Portale nord Biblioteca\n";
}
