package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/* 
	 * Torna booleano se posso aggiungere l'attrezzo e lo aggiunge anche alla borsa
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())		// Non aggiungo se supero peso
			return false;
		if (this.numeroAttrezzi==10)			// Non aggiungo perchè non c'è spazio
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	/* 
	 * Torna un attrezzo (ricerca in base al nome)
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		int i = 0;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) 
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
			i++;
		}
		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		int i = 0;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null)
				peso += this.attrezzi[i].getPeso();
			
			i++;
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		
		int i = 0;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null)
				if (attrezzo.getNome().equals(nomeAttrezzo)) {
					a = attrezzo;
					this.attrezzi[i] = this.attrezzi[--this.numeroAttrezzi];	// Aggiorno anche il numero di attrezzi
					this.attrezzi[this.numeroAttrezzi] = null;		// Ultimo attrezzo tappa buco di attrezzo rimosso quindi lo elimino dalla sua vecchia posizione
					/*
					 * Non dobbiamo diminuire peso della borsa perchè il metodo getPeso
					 * agisce su attrezzi correnti nella borsa
					 */
				}
			i++;
		}	
		return a;
	}
	
	public String getDescrizione() {
        return this.toString();
    }
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			int i = 0;
			for (Attrezzo attrezzo : this.attrezzi) {
				if (attrezzo != null)
					s.append(attrezzi[i].toString()+" ");
				i++;
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
