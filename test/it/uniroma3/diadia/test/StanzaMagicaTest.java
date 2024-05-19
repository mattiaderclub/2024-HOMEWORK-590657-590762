package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private Stanza stanza;
	private Attrezzo a1;
	private Attrezzo a2;

	@Test
	public void testModificaAttrezzo_AggiungoChiaveESpadaAUnaStanzaMagica() {
		this.stanza = new StanzaMagica("magica", 1);
		a1 = new Attrezzo("chiave", 1);
		a2 = new Attrezzo("spada", 1);
		this.stanza.addAttrezzo(a1);
		assertTrue(stanza.getDescrizione().contains("chiave"));
		this.stanza.addAttrezzo(a2);
		
		assertTrue(stanza.getDescrizione().contains("adaps"));
		assertFalse(stanza.getDescrizione().contains("spada"));
		
		assertNotEquals(a2.getPeso(), stanza.getAttrezzo("adaps").getPeso());
		assertEquals(a2.getPeso()*2, stanza.getAttrezzo("adaps").getPeso());
	}

}
