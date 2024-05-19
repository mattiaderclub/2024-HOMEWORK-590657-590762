package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale, crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO console;

	public DiaDia(Labirinto labirinto, IO io) {
		this.console = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.console.leggiRiga();
		} while (!processaIstruzione(istruzione)); // Esco se do comando 'fine' (torna true) oppure ho vinto (torna
													// true)
	}

	public boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		else if (!this.partita.giocatoreIsVivo())
			this.console.mostraMessaggio("Hai esaurito i CFU...");
		else if (factory.getComando().getNome().equals("fine"))
			this.partita.setFinita();

		return this.partita.isFinita();
	}
	
	public Partita getPartita() {
		return this.partita;
	}

	public static void main(String[] argc) {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione */
		IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne")
				.addStanza("Aula")
				.addAttrezzo("spada", 3)
				.addStanzaVincente("Biblioteca")
				.addAttrezzo("torcia", 5)
				.addAdiacenza("Aula","LabCampusOne","nord")
				.addAdiacenza("LabCampusOne","Aula","sud")
				.addAdiacenza("LabCampusOne","Biblioteca","ovest")
				.getLabirinto();		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
}
