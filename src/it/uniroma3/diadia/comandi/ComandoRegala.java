package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import static it.uniroma3.diadia.properties.CostantiDiaDia.ATT_NON_IN_BORSA;
import static it.uniroma3.diadia.properties.CostantiDiaDia.ATT_NON_SPECIFICATO;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_A_CHI;

public class ComandoRegala extends AbstractComando {
	
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if (this.getParametro() == null) {
			this.getIO().mostraMessaggio(ATT_NON_SPECIFICATO);
		}
		else {
			if (personaggio != null) {
				if (partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
					Attrezzo rimosso = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
					personaggio.riceviRegalo(rimosso, partita);
				}
				else {
					this.getIO().mostraMessaggio(ATT_NON_IN_BORSA);
				}
			}
			else
				this.getIO().mostraMessaggio(MESSAGGIO_A_CHI);
		}	
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
