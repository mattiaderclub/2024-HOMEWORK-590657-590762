package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private Stanza s1;
	private Stanza s2;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		this.s1 = new Stanza("stanza");
		this.s2 = new StanzaBloccata("bloccata", "nord", "passepartout");
		this.s2.impostaStanzaAdiacente("nord", s1);
	}

	@Test
	public void testGetStanzaAdiacente_PassepartoutSbloccaStanzaS2() {
		this.a1 = new Attrezzo("passepartout", 5);
		this.s2.addAttrezzo(a1);
		assertEquals(this.s1, this.s2.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacente_ChiaveNonSbloccaStanzaS2() {
		this.a1 = new Attrezzo("chiave", 2);
		this.s2.addAttrezzo(a1);
		assertNotEquals(this.s1, this.s2.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacente_PassepartoutNonServePerAndareInS3() {
		Stanza s3 = new Stanza("nuova");
		this.s2.impostaStanzaAdiacente("est", s3);
		this.a1 = new Attrezzo("passepartout", 5);
		this.s2.addAttrezzo(a1);
		assertEquals(s3, this.s2.getStanzaAdiacente("est"));
	}
}
