package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;


public class LabirintoTest {

	@Test
	public void testGetStanzaIniziale_UgualeAAtrio() {
		assertEquals("Atrio", new Labirinto().getStanzaIniziale().getNome());
	}
	@Test
	public void testGetStanzaIniziale_DiversaDaBiblioteca() {
		assertNotEquals("Biblioteca", new Labirinto().getStanzaIniziale().getNome());
	}
	@Test
	public void testGetStanzaIniziale_DiversaDaNull() {
		assertNotNull(new Labirinto().getStanzaIniziale());
	}
	
	
	
	@Test
	public void testGetStanzaVincente_UgualeABiblioteca() {
		assertEquals("Biblioteca", new Labirinto().getStanzaVincente().getNome());
	}
	@Test
	public void testGetStanzaVincente_DiversaDaAtrio() {
		assertNotEquals("Atrio", new Labirinto().getStanzaVincente().getNome());
	}
	@Test
	public void testGetStanzaVincente_DiversaDaNull() {
		assertNotNull(new Labirinto().getStanzaVincente());
	}
}
