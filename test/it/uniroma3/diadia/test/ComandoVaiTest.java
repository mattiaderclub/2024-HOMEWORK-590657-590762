package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {

	private IO io;
	private Stanza s1;
	private Partita p;
	private Comando vai;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.p = new Partita();
		this.vai = new ComandoVai();
		this.vai.setIO(io);
	}

	@Test
	public void testComandoVai_AndandoAEstMiTrovoinN11() {
		this.s1 = this.p.getStanzaCorrente().getStanzaAdiacente("est");
		this.vai.setParametro("est");
		this.vai.esegui(p);
		assertEquals(this.s1, this.p.getStanzaCorrente());
	}

	@Test
	public void testComandoVai_AndandoAEstNonMiTrovoInLaboratorio() {
		this.s1 = this.p.getStanzaCorrente().getStanzaAdiacente("ovest");
		this.vai.setParametro("est");
		this.vai.esegui(p);
		assertNotEquals(this.s1, this.p.getStanzaCorrente());
	}

	@Test
	public void testComandoVai_AndandoANordEsisteUnaStanza() {
		this.vai.setParametro("nord");
		this.vai.esegui(p);
		assertNotNull(this.p.getStanzaCorrente());
	}
}
