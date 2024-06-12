package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.properties.CostantiDiaDia.DEFAULT_PESO_MAX_BORSA;

public class Borsa {
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}
	
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	/* 
	 * Torna booleano se posso aggiungere l'attrezzo e lo aggiunge anche alla borsa
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())		// Non aggiungo se supero peso
			return false;
		
		return this.getAttrezzi().put(attrezzo.getNome(), attrezzo) == null;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	/* 
	 * Torna un attrezzo (ricerca in base al nome)
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzi().get(nomeAttrezzo);
	}
	
	/*
	 * Torna il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		Collection<Attrezzo> listaAtt = this.getAttrezzi().values();
		
		for (Attrezzo attrezzo : listaAtt) {
			peso += attrezzo.getPeso();
		}
		return peso;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzi().containsKey(nomeAttrezzo);
	}
	
	/*
	 * Torna l'attrezzo che è stato rimosso dalla collezione
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzi().remove(nomeAttrezzo);
	}
	
	public String getDescrizione() {
        return this.toString();
    }
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.getAttrezzi().isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");			
			s.append(this.getAttrezzi().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	/*
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi,
	 * a parità di peso, per nome
	 * 
	 * Dato che dobbiamo ordinare secondo due criteri ci serve un comparatore
	 * esterno
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinataPerPeso = new ArrayList<Attrezzo>(this.getAttrezzi().values());
		ComparatorePerPeso cmp = new ComparatorePerPeso();
		Collections.sort(ordinataPerPeso, cmp);
		return ordinataPerPeso;		
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinatoPerNome = new TreeSet<Attrezzo>(this.getAttrezzi().values());
		return ordinatoPerNome;		
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
	
		Collection<Attrezzo> attrezzi = this.getAttrezzi().values();
		Map<Integer, Set<Attrezzo>> risultato = new HashMap<Integer, Set<Attrezzo>>();
		
		for (Attrezzo attrezzo : attrezzi) {
			/*
			 * Se ho già la chiave recupero dalla mappa l'insieme con quel peso
			 * e aggiungo un attrezzo (perchè è un attrezzo con un peso già esistente
			 * il put non serve perchè il set è già presente in mappa
			 * 
			 * Se non ho la chiave creo il set, aggiungo l'attrezzo con peso mai visto
			 * e lo inserisco nella mappa con chiave il peso di quell'attrezzo
			 */
			if (risultato.containsKey(attrezzo.getPeso())) {
				Set<Attrezzo> set = risultato.get(attrezzo.getPeso());
				set.add(attrezzo);
			}
			else {
				Set<Attrezzo> nuovoPeso = new HashSet<Attrezzo>();
				nuovoPeso.add(attrezzo);
				risultato.put(attrezzo.getPeso(), nuovoPeso);
			}			
		}
		return risultato;		
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		ComparatorePerPeso cmp =  new ComparatorePerPeso();
		SortedSet<Attrezzo> setPerPeso = new TreeSet<Attrezzo>(cmp);
		setPerPeso.addAll(this.getAttrezzi().values());
		return setPerPeso;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		Borsa that = (Borsa) o;
		if (this.getAttrezzi().size() != that.getAttrezzi().size() || 
				this.getPeso() != that.getPeso())
			return false;
		
		return this.getAttrezzi().equals(that.getAttrezzi());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getAttrezzi().hashCode() + 
				this.getPeso() + this.getAttrezzi().size();
	}
}
