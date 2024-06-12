package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	/*
	 * Crea StringBuilder a cui attacca stringhe diverse a seconda dello stato dell'oggetto
	 * "Ciao io sono " +
	 * attacca la presentazione o "Ci siamo già presentati!" se il personaggio è stato salutato o meno
	 */
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome() + ".");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");

		this.haSalutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	abstract public Attrezzo getAttrezzo();

	@Override
	public String toString() {
		return this.getNome();
	}
}
