package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.properties.Fixture;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {

	private IO io;
	
	private Fixture simulazione;
	private DiaDia gioco;
	
	private Stanza s1;
	private Labirinto labirinto;
	private Partita p;
	private AbstractComando vai;

	@Before
	public void setUp() {
		try (Scanner scanner = new Scanner(System.in)) {
			this.io = new IOConsole(scanner);
			
			this.simulazione = new Fixture();
			Labirinto labirinto = this.simulazione.fixturePartitaDifficile();
			this.gioco = new DiaDia(labirinto, io);
			
			this.labirinto = new Labirinto("creoLabirinto.txt");
			this.p = new Partita(this.labirinto);
			this.vai = new ComandoVai();
			this.vai.setIO(io);
		}
		catch (Exception e) {
			System.out.println("Errore!!!");
		}
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
	
	@Test
	public void testComandoVaiSimulazione() {
		this.gioco.processaIstruzione("vai nord");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("nord"), this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Bagno", this.gioco.getPartita().getStanzaCorrente().getNome());
		
		this.gioco.processaIstruzione("vai est");
		assertNull(this.gioco.getPartita().getStanzaCorrente().
				getStanzaAdiacente("est"));
		assertEquals("Bagno", this.gioco.getPartita().getStanzaCorrente().getNome());

		this.gioco.processaIstruzione("vai sud");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Atrio", this.gioco.getPartita().getStanzaCorrente().getNome());
		
		this.gioco.processaIstruzione("vai");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Atrio", this.gioco.getPartita().getStanzaCorrente().getNome());
				
		this.gioco.processaIstruzione("vai ovest");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("ovest"), this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Studio", this.gioco.getPartita().getStanzaCorrente().getNome());
		
		this.gioco.processaIstruzione("vai ovest");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("ovest").getStanzaAdiacente("ovest"), this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Riservata", this.gioco.getPartita().getStanzaCorrente().getNome());
		
		this.gioco.processaIstruzione("vai nord");
		assertEquals(this.gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("ovest").getStanzaAdiacente("ovest"), this.gioco.getPartita().getStanzaCorrente());
		assertEquals("Riservata", this.gioco.getPartita().getStanzaCorrente().getNome());
	}
}
