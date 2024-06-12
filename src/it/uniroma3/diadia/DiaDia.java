package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_BENVENUTO;

import java.util.Scanner;

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
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		
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
		try (Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
			Labirinto labirinto = new Labirinto("fixturePartitaDifficile.txt");
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
		catch (Exception e) {
			System.out.println("Errore!!!");
		}
	}
}
