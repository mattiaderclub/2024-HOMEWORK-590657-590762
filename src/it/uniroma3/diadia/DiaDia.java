package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale, crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole lettura;
	
	public DiaDia(IOConsole scan) {
		this.lettura = scan;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		this.lettura.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do	{
			istruzione = this.lettura.leggiRiga();
		}
		while (!processaIstruzione(istruzione));	// Esco se do comando 'fine' (torna true) oppure ho vinto (torna true)					
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if (istruzione.isEmpty()) return false;				// isEmpty per controllo contenuto stringa
		
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			this.lettura.mostraMessaggio("Comando sconosciuto");

		if (this.partita.isFinita()) {
			if (this.partita.vinta()) {
				this.lettura.mostraMessaggio("Hai vinto!");
			}
			else
				this.lettura.mostraMessaggio("Hai perso!");
			return true;
		} else
			return false;
	}   

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		this.lettura.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {			// 'direzione' ricevuta coincide con 'parametro' dato al comando
		if(direzione==null)
			this.lettura.mostraMessaggio("Dove vuoi andare ?");		// Hai inserito solo 'vai'
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.lettura.mostraMessaggio("Direzione inesistente");	// Dove voglio andare non ci sono uscite
			else {
				this.partita.setStanzaCorrente(prossimaStanza);		// Aggiorno la stanza corrente essendomi spostato
				
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(--cfu);			// Invece qua prima decremento quindi aggiorno quindi modifico
			}
		}
		this.lettura.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.lettura.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}
	
	/*
	 * Comando per prendere un attrezzo da una stanza e aggiungerlo alla borsa del giocatore
	 */
	private void prendi(String nomeAttrezzo){
		if (nomeAttrezzo == null)
			this.lettura.mostraMessaggio("Cosa vuoi prendere?");	// se scrivo solo "prendi"	
		else {
			if (this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {			// Se ho attrezzo nella stanza lo catturo
				Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if (this.partita.getGiocatore().getBorsa().addAttrezzo(a))			// Se posso e riesco ad aggiungere l'attrezzo alla borsa lo rimuovo dalla stanza
					this.partita.getStanzaCorrente().removeAttrezzo(a);
				else
					this.lettura.mostraMessaggio("Borsa piena");				// Borsa piena o troppo pesante
			}
			else
				this.lettura.mostraMessaggio(nomeAttrezzo + " inesistente in " + this.partita.getStanzaCorrente().getNome());
		}
		this.lettura.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}
	
	/*
	 * Comando per posare un attrezzo in una stanza e rimuoverlo dalla borsa del giocatore
	 */
	private void posa(String nomeAttrezzo){
		if (nomeAttrezzo == null)
			this.lettura.mostraMessaggio("Cosa vuoi posare ?");	
		else {
			if (this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				if (this.partita.getStanzaCorrente().addAttrezzo(a))
					this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				else
					this.lettura.mostraMessaggio("Stanza piena");
			}
			else
				this.lettura.mostraMessaggio(nomeAttrezzo + " inesistente in borsa");
		}
		this.lettura.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.lettura.mostraMessaggio("Grazie di aver giocato!");  // Si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole lettura = new IOConsole();			// Dichiaro istanza di IOConsole nel main
		DiaDia gioco = new DiaDia(lettura);
		gioco.gioca();
	}
}
