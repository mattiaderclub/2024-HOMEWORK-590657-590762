package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> messaggiDaLeggere;
	private List<String> messaggiProdotti;
	private int indiceLettura;
	private int indiceAggiunta;	

	public IOSimulator(List<String> comandi) {
		this.messaggiDaLeggere = comandi;
		this.messaggiProdotti = new ArrayList<String>();
		this.indiceLettura = 0;
		this.indiceAggiunta = 0;
	}
	
	/*
	 * Recupera una stringa dall'array e la resistuisce
	 */
	@Override
	public String leggiRiga() {
		String stringa = this.messaggiDaLeggere.get(indiceLettura);
		this.indiceLettura++;
		return stringa;
	}

	/*
	 * Riceve un messaggio e lo aggiunge all'array di stringhe
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(indiceAggiunta, messaggio);;
		this.indiceAggiunta++;
	}
	
	public List<String> getMessaggiDaLeggere() {
		return this.messaggiDaLeggere;
	}
	
	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}
}
