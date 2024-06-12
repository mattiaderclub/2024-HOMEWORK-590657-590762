package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_CHI;

public class ComandoSaluta extends AbstractComando {

	private String messaggio;
	
	/*
	 * Salvo in un oggetto Personaggio il personaggio della stanza corrente
	 * Se esiste allora recupera il messaggio dall'azione del personaggio
	 * e lo stampa a schermo sennò chiede al giocatore con chi interagire
	 */
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.saluta();
			this.getIO().mostraMessaggio(this.messaggio);
		}
		else
			this.getIO().mostraMessaggio(MESSAGGIO_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}
}
