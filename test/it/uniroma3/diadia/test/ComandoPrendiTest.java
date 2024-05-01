package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoPrendiTest {
	
	private IO io;
	private Attrezzo attrezzo;
	private Partita p;
	private Comando prendi;
	
	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.p = new Partita();
		this.prendi = new ComandoPrendi();
		this.prendi.setIO(io);
	}
	
	@Test
	public void testComandoPrendi_PrendoOssoInAtrioEMeLoRitrovoInBorsa() {
		this.prendi.setParametro("osso");
		this.prendi.esegui(p);
		attrezzo = this.p.getGiocatore().getBorsa().getAttrezzo("osso");
		assertEquals("osso", attrezzo.getNome());
	}

	@Test
	public void testComandoPrendi_PrendoOssoInAtrioECercoSpadaInBorsa() {
		this.prendi.setParametro("osso");
		this.prendi.esegui(p);
		attrezzo = this.p.getGiocatore().getBorsa().getAttrezzo("osso");
		assertNotEquals("spada", attrezzo.getNome());
	}
	
	@Test
	public void testComandoPrendi_PrendoAttrezzoInesistente() {
		this.prendi.setParametro("attrezzo");
		this.prendi.esegui(p);
		attrezzo = this.p.getGiocatore().getBorsa().getAttrezzo("attrezzo");
		assertNull(attrezzo);
	}
}
