package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.properties.Fixture;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoPosaTest {

	private IO io;
	
	private Fixture simulazione;
	private DiaDia gioco;
	
	private Attrezzo attrezzo;
	private Labirinto labirinto;
	private Partita p;
	private AbstractComando posa;

	@Before
	public void setUp() {
		try (Scanner scanner = new Scanner(System.in)) {
			this.io = new IOConsole(scanner);
			
			this.simulazione = new Fixture();
			Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante();
			this.gioco = new DiaDia(labirinto, io);
			this.gioco.processaIstruzione("prendi chiave");
			this.gioco.processaIstruzione("prendi martello");
			
			this.labirinto = new Labirinto("creoLabirinto.txt");
			this.p = new Partita(this.labirinto);
			this.posa = new ComandoPosa();
			this.posa.setIO(io);
		}
		catch (Exception e) {
			System.out.println("Errore!!!");
		}
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
	public void testComandoPosa_PosoAttrezzioInAtrioECercoAltro() {
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
	
	@Test
	public void testComandoPosaSimulazione_PosoAttrezziInAtrio() {
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		
		this.gioco.processaIstruzione("posa chiave");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 4);	
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		
		this.gioco.processaIstruzione("posa martello");
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);	
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testComandoPosaSimulazione_PosoAttrezziInAtrioECercoAltro() {
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		
		this.gioco.processaIstruzione("posa chiave");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 4);	
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("altro"));
	}
	
	@Test
	public void testComandoPosaSimulazione_PosoAltroECercoAttrezziInAtrio() {
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		
		this.gioco.processaIstruzione("posa altro");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);	
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("altro"));
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("altro"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		this.gioco.processaIstruzione("posa");
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);	
		assertFalse(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo(""));
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(this.gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo(""));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		assertFalse(this.gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
	}
}
