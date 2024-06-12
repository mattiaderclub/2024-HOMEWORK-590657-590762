package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		if (this.getParametro() == null)
			this.getIO().mostraMessaggio("Cosa vuoi prendere?"); // se scrivo solo "prendi"
		else {
			if (partita.getStanzaCorrente().hasAttrezzo(this.getParametro())) { // Se ho attrezzo nella stanza lo catturo
				Attrezzo a = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
				if (partita.getGiocatore().getBorsa().addAttrezzo(a)) 	// Se posso e riesco ad aggiungere l'attrezzo alla
																		// borsa lo rimuovo dalla stanza
					partita.getStanzaCorrente().removeAttrezzo(a);
				else
					this.getIO().mostraMessaggio("Borsa piena"); // Borsa piena o troppo pesante
			} else
				this.getIO().mostraMessaggio(this.getParametro() + " inesistente in " + partita.getStanzaCorrente().getNome());
		}
	}
}
