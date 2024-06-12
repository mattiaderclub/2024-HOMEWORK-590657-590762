package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.properties.CostantiDiaDia.CIBO_PREFERITO;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_MORSO;

public class Cane extends AbstractPersonaggio {

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public Attrezzo getAttrezzo() {
		return null;
	}

	/*
	 * Agisci di Cane
	 * Decrementa di 1 i cfu del protagonista e torna un messaggio di avvertimento
	 */
	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(--cfu);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("Woof Woof, grazie per avermi regalato \"" + attrezzo.getNome() + "\", "); 
		
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			risposta.append("è il mio cibo preferito, tieni!");
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("bastone", 0));
		}
		else {
			risposta.append("non è il mio cibo preferito, bau!");
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
		}
		return risposta.toString();
	}
}
