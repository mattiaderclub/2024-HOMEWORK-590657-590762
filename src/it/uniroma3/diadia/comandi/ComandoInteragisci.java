package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_CON_CHI;

public class ComandoInteragisci extends AbstractComando {
	
	private String messaggio;

	/*
	 * Salvo in un oggetto Personaggio il personaggio della stanza corrente
	 * Se esiste allora recupera il messaggio dal saluto del personaggio
	 * e lo stampa a schermo sennò avverte il giocatore dell'assenza di qualcuno da salutare
	 */
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			this.getIO().mostraMessaggio(this.messaggio);
		} 
		else
			this.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}
}
