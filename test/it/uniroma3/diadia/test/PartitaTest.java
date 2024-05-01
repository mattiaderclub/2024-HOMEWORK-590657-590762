package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
// import org.junit.*;		x before
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class PartitaTest {
	
	@Test
	public void testGetCfu_DiversiDaZero() {
		assertNotEquals(0, new Partita().getGiocatore().getCfu());
	}	
	@Test
	public void testGetCfu_UgualiAVenti() {
		assertEquals(20, new Partita().getGiocatore().getCfu());
	}
	@Test
	public void testGetCfu_Settatia15() {
		Partita test = new Partita();
		test.getGiocatore().setCfu(15);
		assertEquals(15, test.getGiocatore().getCfu());
	}
	
	
	
	@Test
	public void testStanzaCorrenteUgualeAtrio() {
		assertEquals("Atrio" , new Partita().getStanzaCorrente().getNome());
	}
	@Test
	public void testStanzaVincenteUgualeBiblioteca() {
		assertEquals("Biblioteca" , new Partita().getLabirinto().getStanzaVincente().getNome());
	}
	@Test
	public void testStanzaSettata(){
		Partita test = new Partita();
		test.setStanzaCorrente(null);
		assertNull(test.getStanzaCorrente());		
	}
	
	
	
	@Test
	public void testIsFinita_NonFinitaAppenaCreataLaPartita() {
		assertFalse(new Partita().isFinita());
	}
	@Test
	public void testIsFinita_CfuSettatiAZero() {
		Partita test = new Partita();
		test.getGiocatore().setCfu(0);
		assertTrue(test.isFinita());
	}
	@Test
	public void testIsFinita_StanzaCorrenteUgualeAVincente() {
		Partita test = new Partita();
		test.setStanzaCorrente(test.getLabirinto().getStanzaVincente());
		assertTrue(test.isFinita());
	}
}
