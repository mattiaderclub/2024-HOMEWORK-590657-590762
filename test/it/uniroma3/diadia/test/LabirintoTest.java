package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;


public class LabirintoTest {
	
	private Labirinto labirinto;

	@Before
	public void setUp() {
		this.labirinto = new Labirinto("labirinto1.txt");
	}
	
	@Test
	public void testGetStanzaIniziale_UgualeAAtrio() {
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
	}
	@Test
	public void testGetStanzaIniziale_DiversaDaBiblioteca() {
		assertNotEquals("Biblioteca", this.labirinto.getStanzaIniziale().getNome());
	}
	@Test
	public void testGetStanzaIniziale_DiversaDaNull() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	
	
	@Test
	public void testGetStanzaVincente_UgualeABiblioteca() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}
	@Test
	public void testGetStanzaVincente_DiversaDaAtrio() {
		assertNotEquals("Atrio", this.labirinto.getStanzaVincente().getNome());
	}
	@Test
	public void testGetStanzaVincente_DiversaDaNull() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
}
