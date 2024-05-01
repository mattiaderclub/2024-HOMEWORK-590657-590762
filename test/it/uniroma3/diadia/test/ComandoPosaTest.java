package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoPosaTest {

	private IO io;
	private Attrezzo attrezzo;
	private Partita p;
	private Comando posa;
	
	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.p = new Partita();
		this.posa = new ComandoPosa();
		this.posa.setIO(io);
	}
	
	@Test
	public void testComandoPosa_PosoAttrezzoInAtrio() {
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.p.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.posa.setParametro("attrezzo");
		this.posa.esegui(p);
		assertTrue(this.p.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	public void testComandoPosa_PosoAttrezzoInAtrioECercoAltro() {
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.p.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.posa.setParametro("attrezzo");
		this.posa.esegui(p);
		assertFalse(this.p.getStanzaCorrente().hasAttrezzo("altro"));
	}
	
	@Test
	public void testComandoPosa_PosoAltroECercoAttrezzoInAtrio() {
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.p.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.posa.setParametro("altro");
		this.posa.esegui(p);
		assertNull(this.p.getStanzaCorrente().getAttrezzo(attrezzo.getNome()));
	}
}
