package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	//private int numeroStanzeAdiacenti;
	//private Set<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		//this.numeroStanzeAdiacenti = 0;
		//this.numeroAttrezzi = 0;
		//this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.attrezzi = new HashMap<String, Attrezzo>();
	}
	
	/**
	 * Restituisce la collezione di stanze adiacenti alla stanza.
	 * 
	 * @return la collezione di stanze adiacenti.
	 */
	public Map<String, Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}	
	
	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/* 
	 * Ritorna una lista degli attrezzi presenti nella stanza
	 */
	public List<Attrezzo> prendiListaAttrezzi() {
		Collection<Attrezzo> collezioneAtt = this.getAttrezzi().values();
		List<Attrezzo> listaAtt = new ArrayList<Attrezzo>();
		listaAtt.addAll(collezioneAtt);
		return listaAtt;
	}
	
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {	
		if (this.getStanzeAdiacenti().size() < NUMERO_MASSIMO_DIREZIONI)
			this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getAttrezzi().size() < NUMERO_MASSIMO_ATTREZZI) {
			if (this.getAttrezzi().put(attrezzo.getNome(), attrezzo) == null) {
				return true;
			}
			else
				return false;
		} 
		else
			return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		
		risultato.append("Stanza : " + this.nome);
		risultato.append("\nUscite: " + this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: " + this.getAttrezzi().toString());

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzi().containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzi().get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.getAttrezzi().remove(attrezzo.getNome()) != null;
	}

	public List<String> getDirezioni() {
		Collection<String> chiavi = this.stanzeAdiacenti.keySet();
		List<String> direzioni = new ArrayList<String>();
		direzioni.addAll(chiavi);
		return direzioni;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		Stanza that = (Stanza) o;
		return this.getNome().equals(that.getNome()) && 
				this.getAttrezzi().size() == that.getAttrezzi().size() &&
				this.getAttrezzi().equals(that.getAttrezzi()) && 
				this.getStanzeAdiacenti().equals(that.getStanzeAdiacenti());
	}
	
	@Override
	public int hashCode() {
		return this.getAttrezzi().hashCode() + this.getNome().hashCode() +
				this.getDirezioni().hashCode();
	}
}
