package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo;

	@Test
	public void testGetDescrizione_StanzaBuiaHaAttrezzoLuminoso() {
		stanza = new StanzaBuia("buia", "torcia");
		attrezzo = new Attrezzo("torcia", 3);
		stanza.addAttrezzo(attrezzo);
		assertNotEquals("qui c'è un buio pesto", stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizione_StanzaBuiaNonHaAttrezzoLuminoso() {
		stanza = new StanzaBuia("buia", "torcia");
		attrezzo = new Attrezzo("lanterna", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals("qui c'è un buio pesto", stanza.getDescrizione());
	}
}
