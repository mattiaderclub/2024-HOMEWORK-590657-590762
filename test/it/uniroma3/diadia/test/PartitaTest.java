package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {
	
	private Labirinto labirinto;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto("labirinto1.txt");
		this.partita = new Partita(labirinto);
	}
	
	@Test
	public void testGetCfu_DiversiDaZero() {
		assertNotEquals(0, this.partita.getGiocatore().getCfu());
	}	
	@Test
	public void testGetCfu_UgualiAVenti() {
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}
	@Test
	public void testGetCfu_Settatia15() {
		this.partita.getGiocatore().setCfu(15);
		assertEquals(15, this.partita.getGiocatore().getCfu());
	}
	
	
	
	@Test
	public void testStanzaCorrenteUgualeAtrio() {
		assertEquals("Atrio" , this.partita.getStanzaCorrente().getNome());
	}
	@Test
	public void testStanzaVincenteUgualeBiblioteca() {
		assertEquals("Biblioteca" , this.partita.getLabirinto().getStanzaVincente().getNome());
	}
	@Test
	public void testStanzaSettata(){
		this.partita.setStanzaCorrente(null);
		assertNull(this.partita.getStanzaCorrente());		
	}
	
	
	
	@Test
	public void testIsFinita_NonFinitaAppenaCreataLaPartita() {
		assertFalse(this.partita.isFinita());
	}
	@Test
	public void testIsFinita_CfuSettatiAZero() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	@Test
	public void testIsFinita_StanzaCorrenteUgualeAVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
}
