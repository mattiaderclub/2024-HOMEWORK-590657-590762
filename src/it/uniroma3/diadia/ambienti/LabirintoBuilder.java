package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	
	private Map<String, Stanza> mappaStanze;
	private Stanza ultimaAggiunta;
	private Labirinto labirinto;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<String, Stanza>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza iniziale = new Stanza(nomeStanza);
		this.ultimaAggiunta = iniziale;
		this.getLabirinto().setStanzaIniziale(iniziale);
		this.mappaStanze.put(iniziale.getNome(), iniziale);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		this.ultimaAggiunta = stanza;
		this.mappaStanze.put(stanza.getNome(), stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza vincente = new Stanza(nomeStanza);
		this.ultimaAggiunta = vincente;
		this.getLabirinto().setStanzaVincente(vincente);
		this.mappaStanze.put(vincente.getNome(), vincente);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		if (this.ultimaAggiunta == null)
			return this;
		this.ultimaAggiunta.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeAdiacente, String direzione) {		
		Stanza stanza = this.mappaStanze.get(nomeStanza);
		Stanza adiacente = this.mappaStanze.get(nomeAdiacente);
		stanza.impostaStanzaAdiacente(direzione, adiacente);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
		Stanza magica = new StanzaMagica(nomeStanza, soglia);	
		this.ultimaAggiunta = magica;		
		this.mappaStanze.put(magica.getNome(), magica);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
		Stanza buia = new StanzaBuia(nomeStanza, nomeAttrezzo);		
		this.ultimaAggiunta = buia;
		this.mappaStanze.put(buia.getNome(), buia);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzione, String nomeAttrezzo) {
		Stanza bloccata = new StanzaBloccata(nomeStanza, direzione, nomeAttrezzo);	
		this.ultimaAggiunta = bloccata;		
		this.mappaStanze.put(bloccata.getNome(), bloccata);
		return this;
	}
	
	public Map<String, Stanza> getListaStanze() {
		return this.mappaStanze;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
