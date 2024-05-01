package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaTest {

	private Stanza s1;
	private Stanza s2;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		this.s1 = new Stanza("stanza");
		this.s2 = new Stanza("vuota");
		this.a1 = new Attrezzo("attrezzo", 2);
		s1.impostaStanzaAdiacente("nord", s2);
		s1.addAttrezzo(a1);
	}
	
	@Test
	public void testGetStanzaAdiacente_S2aNordDiS1() {
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testGetStanzaAdiacente_NoS2aSudDiS1() {
		assertNotEquals(s2, s1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testGetStanzaAdiacente_NienteaEstDiS1() {
		assertNull(s1.getStanzaAdiacente("est"));
	}
	
	
	
	@Test
	public void testGetAttrezzo_AttrezzoInS1_Presente() {
		assertEquals(a1, s1.getAttrezzo("attrezzo"));
	}
	@Test
	public void testGetAttrezzo_SpadaInS1_Assente() {
		assertNotEquals(a1, s1.getAttrezzo("spada"));
	}
	@Test
	public void testGetAttrezzo_AttrezzoInS2_Assente() {
		assertNull(s2.getAttrezzo("attrezzo"));
	}
	

	
	@Test
	public void testHasAttrezzo_S1HaAttrezzo() {
		assertTrue(s1.hasAttrezzo("attrezzo"));
	}
	@Test
	public void testHasAttrezzo_S1NonHaSpada() {
		assertFalse(s1.hasAttrezzo("spada"));
	}
	@Test
	public void testHasAttrezzo_S2NonHaAttrezzo() {
		assertFalse(s2.hasAttrezzo("attrezzo"));
	}
	
	
	
	@Test
	public void testRemoveAttrezzo_AttrezzoInS1Rimosso() {
		assertTrue(s1.removeAttrezzo(a1));
	}
	@Test
	public void testRemoveAttrezzo_RimozioneInStanzaVuota() {
		assertFalse(s2.removeAttrezzo(a1));
	}
	@Test
	public void testRemoveAttrezzo_RimozioneAttrezzoAssenteNellaStanza() {
		Attrezzo a2 = new Attrezzo("arma", 4);
		assertFalse(s1.removeAttrezzo(a2));
	}
}
