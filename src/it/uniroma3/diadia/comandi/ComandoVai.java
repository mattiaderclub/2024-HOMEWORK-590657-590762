package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.getParametro() == null) {
			this.getIO().mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione"); // Hai inserito solo 'vai'
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.getParametro());
		if (prossimaStanza == null) {
			this.getIO().mostraMessaggio("Direzione inesistente"); // Dove voglio andare non ci sono uscite
			return;
		}
		partita.setStanzaCorrente(prossimaStanza); // Aggiorno la stanza corrente essendomi spostato
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(--cfu); // Invece qua prima decremento quindi aggiorno quindi modifico
	}
}
