package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.properties.Fixture;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoPrendiTest {
	
	private IO io;
	
	private Fixture simulazione;
	private DiaDia gioco;
	
	private Attrezzo attrezzo;
	private Labirinto labirinto;
	private Partita p;
	private AbstractComando prendi;
	
	@Before
	public void setUp() {
		try (Scanner scanner = new Scanner(System.in)) {
			this.io = new IOConsole(scanner);
		
			this.simulazione = new Fixture();
			Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante();
			this.gioco = new DiaDia(labirinto, io);
			
			this.labirinto = new Labirinto("creoLabirinto.txt");
			this.p = new Partita(this.labirinto);
			this.prendi = new ComandoPrendi();
			this.prendi.setIO(io);
		}
		catch (Exception e) {
			System.out.println("Errore!!!");
		}
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
	
	@Test
	public void testComandoPrendiSimulazione_PrendoChiaveEMartelloInAtrioEMeLiRitrovoInBorsa() {
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		
		this.gioco.processaIstruzione("prendi chiave");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 1);
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		this.gioco.processaIstruzione("prendi martello");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testComandoPrendiSimulazione_PrendoMartelloInAtrioECercoChiaveInBorsa() {
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		
		this.gioco.processaIstruzione("prendi martello");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 4);
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testComandoPrendiSimulazione_PrendoAttrezzoInesistente() {
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		
		this.gioco.processaIstruzione("prendi");
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		this.gioco.processaIstruzione("prendi attrezzo");
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
}
