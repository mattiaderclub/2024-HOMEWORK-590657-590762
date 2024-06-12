package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> nome2stanze;
	
	private Labirinto() {
		this.nome2stanze = new HashMap<String, Stanza>();
	}
	
	public Labirinto(String nomeFile) {
		CaricatoreLabirinto c;
		try {
			c = new CaricatoreLabirinto(nomeFile);
			c.carica();
			
			this.stanzaIniziale = c.getStanzaIniziale();
			this.stanzaVincente = c.getStanzaVincente();
		}
		catch (FileNotFoundException e) {
			System.out.println("File non trovato: " + nomeFile);
			e.printStackTrace();
		}
		catch (FormatoFileNonValidoException e) {
			System.out.println("Formato del file non valido: " + nomeFile);
			e.printStackTrace();
		}
	}
	
	public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale = iniziale;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza vincente) {
		this.stanzaVincente = vincente;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public Map<String, Stanza> getListaStanze() {
		return this.nome2stanze;
	}
	
	static public class LabirintoBuilder {
		
		private Map<String, Stanza> mappaStanze;
		private Stanza ultimaAggiunta;
		private Stanza stanzaIniziale;
		private Stanza stanzaVincente;
		
		public LabirintoBuilder() {
			this.mappaStanze = new HashMap<String, Stanza>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza iniziale = new Stanza(nomeStanza);
			this.ultimaAggiunta = iniziale;
			this.stanzaIniziale = iniziale;
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
			this.stanzaVincente = vincente;
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
		
		public LabirintoBuilder addMago(String nomeMago, String presentazione, String nomeAttrezzo, int pesoAttrezzo) {
			AbstractPersonaggio mago = new Mago(nomeMago, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			this.ultimaAggiunta.setPersonaggio(mago);
			return this;
		}
		
		public LabirintoBuilder addCane(String nomeMago, String presentazione) {
			AbstractPersonaggio cane = new Cane(nomeMago, presentazione);
			this.ultimaAggiunta.setPersonaggio(cane);
			return this;
		} 
		
		public LabirintoBuilder addStrega(String nomeMago, String presentazione) {
			AbstractPersonaggio strega = new Strega(nomeMago, presentazione);
			this.ultimaAggiunta.setPersonaggio(strega);
			return this;
		}
		
		public Map<String, Stanza> getListaStanze() {
			return this.mappaStanze;
		}
		
		public Labirinto getLabirinto() {
			Labirinto labirinto = new Labirinto();
			labirinto.setStanzaIniziale(this.stanzaIniziale);
			labirinto.setStanzaVincente(this.stanzaVincente);
			labirinto.nome2stanze = this.getListaStanze();
			return labirinto;
		}
	}
}
