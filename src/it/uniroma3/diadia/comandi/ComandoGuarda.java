package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}
}
