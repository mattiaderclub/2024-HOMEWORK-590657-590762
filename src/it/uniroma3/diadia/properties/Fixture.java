package it.uniroma3.diadia.properties;

import it.uniroma3.diadia.ambienti.Labirinto;

public class Fixture {
	
	public Labirinto fixtureMonolocale() {
		return new Labirinto("monolocale.txt");
	}
	
	public Labirinto fixtureBilocale() {
		return new Labirinto("bilocale.txt");
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBuia_ConAttrezzoLuminoso() {
		return new Labirinto("trilocaleBuiaConAttrezzoLuminoso.txt");
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBuia_SenzaAttrezzoLuminoso() {
		return new Labirinto("trilocaleBuiaSenzaLuminoso.txt");
	}
	
	public Labirinto fixtureTrilocale_ConStanzaMagica() {
		return new Labirinto("trilocaleConMagica.txt");
	}
	
	public Labirinto fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante() {
		return new Labirinto("trilocaleConBloccataConSbloccante.txt");
	}
	
	public Labirinto fixturePartitaDifficile() {
		return new Labirinto("fixturePartitaDifficile.txt");
	}
}
