package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		if (this.getParametro() == null)
			this.getIO().mostraMessaggio("Cosa vuoi posare ?");	
		else {
			if (partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
				Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
				if (partita.getStanzaCorrente().addAttrezzo(a))
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				else
					this.getIO().mostraMessaggio("Stanza piena");
			}
			else
				this.getIO().mostraMessaggio(this.getParametro() + " inesistente in borsa");
		}
	}
}
