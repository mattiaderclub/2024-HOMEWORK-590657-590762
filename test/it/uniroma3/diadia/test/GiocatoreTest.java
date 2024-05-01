package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {

	@Test
	public void testGetCfu_DiversiDaZero() {
		assertNotEquals(0, new Giocatore().getCfu());
	}	
	@Test
	public void testGetCfu_UgualiAVenti() {
		assertEquals(20, new Giocatore().getCfu());
	}
	@Test
	public void testGetCfu_Settatia15() {
		Giocatore test = new Giocatore();
		test.setCfu(15);
		assertEquals(15, test.getCfu());
	}
	
	
	
	@Test
	public void testGetBorsa_BorsaCreata() {
		assertNotNull(new Giocatore().getBorsa());
	}
	@Test
	public void testGetBorsa_DueGiocatoriHannoBorseDiverse() {
		Giocatore test = new Giocatore();
		assertTrue(test.getBorsa() != new Giocatore().getBorsa());
	}
	@Test
	public void testGetBorsa_BorseDiDueGiocatoriHannoStessoPesoMax() {
		Giocatore test = new Giocatore();
		assertEquals(new Giocatore().getBorsa().getPesoMax(), test.getBorsa().getPesoMax());
	}
}
