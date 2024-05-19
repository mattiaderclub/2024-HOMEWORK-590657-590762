package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "posa";

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null)
			io.mostraMessaggio("Cosa vuoi posare ?");	
		else {
			if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				if (partita.getStanzaCorrente().addAttrezzo(a))
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				else
					io.mostraMessaggio("Stanza piena");
			}
			else
				io.mostraMessaggio(nomeAttrezzo + " inesistente in borsa");
		}
	}
	
	@Override
	public String getNome() {
		return ComandoPosa.NOME;
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
