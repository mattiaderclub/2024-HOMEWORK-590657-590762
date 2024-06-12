package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = Direzione.valueOf(direzioneBloccata);
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza;

		if (!hasAttrezzo(attrezzoSbloccante)) {
			if (direzione.equals(direzioneBloccata.name()))
				stanza = this;
			else
				stanza = super.getStanzaAdiacente(direzione);
		} else
			stanza = super.getStanzaAdiacente(direzione);

		return stanza;
	}
	
	@Override
	public String getDescrizione() {
		String deviSbloccare = "Stanza bloccata in direzione " + direzioneBloccata.name() + "\nPrendi "
								+ attrezzoSbloccante + " e posalo nella stanza";
		if (!hasAttrezzo(attrezzoSbloccante))
			return deviSbloccare;
		return super.getDescrizione();     
    }
	
	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	
	public String getAttrezzoSbloccante() {
		return this.attrezzoSbloccante;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		StanzaBloccata that = (StanzaBloccata) o;
		return super.equals(o) && this.getAttrezzoSbloccante().equals(that.getAttrezzoSbloccante())
				&& this.getDirezioneBloccata().equals(that.getDirezioneBloccata());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.getAttrezzoSbloccante().hashCode() +
				this.getDirezioneBloccata().hashCode();
	}
}
