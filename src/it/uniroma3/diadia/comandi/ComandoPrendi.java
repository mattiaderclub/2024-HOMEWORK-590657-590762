package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "prendi";

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo == null)
			io.mostraMessaggio("Cosa vuoi prendere?"); // se scrivo solo "prendi"
		else {
			if (partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo)) { // Se ho attrezzo nella stanza lo catturo
				Attrezzo a = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
				if (partita.getGiocatore().getBorsa().addAttrezzo(a)) // Se posso e riesco ad aggiungere l'attrezzo alla
																		// borsa lo rimuovo dalla stanza
					partita.getStanzaCorrente().removeAttrezzo(a);
				else
					io.mostraMessaggio("Borsa piena"); // Borsa piena o troppo pesante
			} else
				io.mostraMessaggio(this.nomeAttrezzo + " inesistente in " + partita.getStanzaCorrente().getNome());
		}
	}
	
	@Override
	public String getNome() {
		return this.NOME;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
