package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_DONO;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_SCUSE;

public class Mago extends AbstractPersonaggio {
	
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}

	/*
	 * Agisci di Mago
	 * Creo Stringa per messaggio da ritornare
	 * Se il mago possiede un attrezzo allora lo lascia nella stanza corrente
	 * e invia un messaggio dicendo di aver donato al giocatore un oggetto
	 * 
	 * In caso contrario si scusa con il giocatore
	 */
	@Override
	public String agisci(Partita partita) {
		String msg;
		
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} 
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String risposta = "Grazie per avermi regalato \"" + attrezzo.getNome() + "\"! " +
							"Dimezzo il suo peso e te lo lascio qua per terra per ricompensarti!";
		
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
		return risposta;		
	}
}
