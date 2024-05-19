package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class Fixture {
	
	public Labirinto fixtureMonolocale() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Atrio")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixtureBilocale() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBuia_ConAttrezzoLuminoso() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("torcia", 3)
				.addStanzaBuia("Caverna", "torcia")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Caverna", "Atrio", "sud")
				.addAdiacenza("Atrio", "Caverna", "nord")
				.addAdiacenza("Biblioteca", "Caverna", "ovest")
				.addAdiacenza("Caverna", "Biblioteca", "est")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBuia_SenzaAttrezzoLuminoso() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("lanterna", 3)
				.addStanzaBuia("Caverna", "torcia")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Caverna", "Atrio", "sud")
				.addAdiacenza("Atrio", "Caverna", "nord")
				.addAdiacenza("Biblioteca", "Caverna", "ovest")
				.addAdiacenza("Caverna", "Biblioteca", "est")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixtureTrilocale_ConStanzaMagica() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("lanterna", 3)
				.addAttrezzo("bastone", 4)
				.addStanzaMagica("Laboratorio", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Laboratorio", "Atrio", "nord")
				.addAdiacenza("Atrio", "Laboratorio", "sud")
				.addAdiacenza("Biblioteca", "Laboratorio", "est")
				.addAdiacenza("Laboratorio", "Biblioteca", "ovest")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("martello", 4)
				.addStanzaBloccata("Riservata", "est", "chiave")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Riservata", "Atrio", "sud")
				.addAdiacenza("Atrio", "Riservata", "nord")
				.addAdiacenza("Biblioteca", "Riservata", "ovest")
				.addAdiacenza("Riservata", "Biblioteca", "est")
				.getLabirinto();
		return labirinto;
	}
	
	public Labirinto fixturePartitaDifficile() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("pistola", 5)
				.addStanzaBuia("Cantina", "lampada")
				.addAttrezzo("martello", 2)
				.addAdiacenza("Atrio", "Cantina", "sud")
				.addAdiacenza("Cantina", "Atrio", "nord")
				.addStanzaVincente("Biblioteca")
				.addStanza("Bagno")
				.addAttrezzo("tubo", 2)
				.addAttrezzo("lampada", 4)
				.addAdiacenza("Bagno", "Atrio", "sud")
				.addAdiacenza("Atrio", "Bagno", "nord")
				.addStanza("Studio")
				.addAttrezzo("penna", 0)
				.addAdiacenza("Studio", "Atrio", "est")
				.addAdiacenza("Atrio", "Studio", "ovest")
				.addStanzaBloccata("Riservata", "nord", "martello")
				.addAdiacenza("Biblioteca", "Riservata", "sud")
				.addAdiacenza("Riservata", "Biblioteca", "nord")
				.addAdiacenza("Studio", "Riservata", "ovest")
				.addAdiacenza("Riservata", "Studio", "est")
				.getLabirinto();
		return labirinto;
	}
}
