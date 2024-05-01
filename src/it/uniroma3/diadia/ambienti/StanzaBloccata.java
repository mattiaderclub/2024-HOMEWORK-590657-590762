package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza;

		if (!hasAttrezzo(attrezzoSbloccante)) {
			if (direzione.equals(direzioneBloccata))
				stanza = this;
			else
				stanza = super.getStanzaAdiacente(direzione);
		} else
			stanza = super.getStanzaAdiacente(direzione);

		return stanza;
	}
	
	@Override
	public String getDescrizione() {
		String deviSbloccare = "Stanza bloccata in direzione " + direzioneBloccata + "\nPrendi "
								+ attrezzoSbloccante + "e posalo nella stanza";
		if (!hasAttrezzo(attrezzoSbloccante))
			return deviSbloccare;
		return super.getDescrizione();     
    }
}
