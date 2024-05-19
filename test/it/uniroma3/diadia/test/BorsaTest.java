package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa b1;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		this.b1 = new Borsa();
		this.a1 = new Attrezzo("attrezzo", 3);
	}
	
	@Test
	public void testIsEmpty_BorsaVuota() {
		assertTrue(b1.getAttrezzi().isEmpty());
	}
	@Test
	public void testIsEmpty_BorsaConAttrezzo() {
		b1.addAttrezzo(a1);
		assertFalse(b1.getAttrezzi().isEmpty());
	}
	@Test
	public void testIsEmpty_AttrezzoRimosso() {
		b1.addAttrezzo(a1);
		b1.removeAttrezzo("attrezzo");
		assertTrue(b1.getAttrezzi().isEmpty());
	}

	
	
	@Test
	public void testGetPeso_BorsaVuota() {
		assertEquals(0, b1.getPeso());
	}
	@Test
	public void testGetPeso_BorsaConPesoDiversoDaZero() {
		b1.addAttrezzo(a1);
		assertNotEquals(0, b1.getPeso());
	}
	@Test
	public void testGetPeso_BorsaConPesoUgualeATre() {
		b1.addAttrezzo(a1);
		assertTrue(b1.getPeso() == 3);
	}

	
	
	@Test
	public void testRemoveAttrezzo_RimuoviAttrezzoAggiuntoNellaBorsa() {
		b1.addAttrezzo(a1);
		assertNotNull(b1.removeAttrezzo("attrezzo"));
	}
	@Test
	public void testRemoveAttrezzo_RimuoviAttrezzoAssenteNellaBorsa() {
		assertNull(b1.removeAttrezzo("attrezzo"));
	}
	@Test
	public void testRemoveAttrezzo_RimuoviNessunAttrezzo() {
		b1.addAttrezzo(a1);
		assertEquals(null, b1.removeAttrezzo("arma"));
	}	
}
