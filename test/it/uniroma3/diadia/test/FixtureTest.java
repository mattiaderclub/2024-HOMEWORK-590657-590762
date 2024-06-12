package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.properties.Fixture;

public class FixtureTest {
	
	private IO io;
	private Fixture simulazione;
	
	@Before
	public void setUp() {
		try (Scanner scanner = new Scanner(System.in)) {
			this.io = new IOConsole(scanner);
			this.simulazione = new Fixture();
		}
		catch (Exception e) {
			System.out.println("Errore!!!");
		}
	}
	
	@Test
	public void testSimulazioneMonolocale() {
		Labirinto labirinto = this.simulazione.fixtureMonolocale();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("guarda");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getLabirinto().getStanzaVincente());
		assertTrue(gioco.getPartita().vinta());
	}
	
	@Test
	public void testSimulazioneBilocale() {
		Labirinto labirinto = this.simulazione.fixtureBilocale();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("prendi spada");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		gioco.processaIstruzione("prendi martello");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 3);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		gioco.processaIstruzione("vai nord");
		assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
		assertTrue(gioco.getPartita().vinta());
	}
	
	@Test
	public void testSimulazioneTrilocale_ConStanzaBuia_ConAttrezzoLuminoso() {
		Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaBuia_ConAttrezzoLuminoso();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("vai nord");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("nord"), gioco.getPartita().getStanzaCorrente());
		assertEquals("Caverna", gioco.getPartita().getStanzaCorrente().getNome());
		assertEquals("qui c'è un buio pesto", gioco.getPartita().getStanzaCorrente().getDescrizione());
		
		gioco.processaIstruzione("vai sud");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getStanzaCorrente());
		
		gioco.processaIstruzione("prendi torcia");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 3);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("torcia"));
		
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("posa torcia");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("torcia"));
		assertNotEquals("qui c'è un buio pesto", gioco.getPartita().getStanzaCorrente().getDescrizione());
		
		gioco.processaIstruzione("vai est");
		assertTrue(gioco.getPartita().vinta());		
	}
	
	@Test
	public void testSimulazioneTrilocale_ConStanzaBuia_SenzaAttrezzoLuminoso() {
		Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaBuia_SenzaAttrezzoLuminoso();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("vai nord");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("nord"), gioco.getPartita().getStanzaCorrente());
		assertEquals("Caverna", gioco.getPartita().getStanzaCorrente().getNome());
		assertEquals("qui c'è un buio pesto", gioco.getPartita().getStanzaCorrente().getDescrizione());
		
		gioco.processaIstruzione("vai sud");
		gioco.processaIstruzione("prendi torcia");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("torcia"));
		
		gioco.processaIstruzione("prendi lanterna");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 3);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
		
		gioco.processaIstruzione("vai nord");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("nord"), gioco.getPartita().getStanzaCorrente());
		assertEquals("qui c'è un buio pesto", gioco.getPartita().getStanzaCorrente().getDescrizione());
		
		gioco.processaIstruzione("vai est");
		assertTrue(gioco.getPartita().vinta());	
	}
	
	@Test
	public void testSimulazioneTrilocale_ConStanzaMagica() {
		Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaMagica();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("prendi lanterna");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 3);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
		
		gioco.processaIstruzione("prendi bastone");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 7);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("bastone"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("bastone"));
		
		gioco.processaIstruzione("vai sud");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("sud"), gioco.getPartita().getStanzaCorrente());
		assertEquals("Laboratorio", gioco.getPartita().getStanzaCorrente().getNome());
		
		gioco.processaIstruzione("posa lanterna");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
		
		gioco.processaIstruzione("posa bastone");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("bastone"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("bastone"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("enotsab"));
		assertTrue(gioco.getPartita().getStanzaCorrente().getAttrezzo("enotsab").getPeso() == 8);
		
		gioco.processaIstruzione("prendi bastone");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("bastone"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("enotsab"));
		
		gioco.processaIstruzione("prendi enotsab");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 8);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("enotsab"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("enotsab"));
		
		gioco.processaIstruzione("prendi lanterna");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
		
		gioco.processaIstruzione("vai ovest");
		assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
		assertTrue(gioco.getPartita().vinta());	
	}
	
	@Test
	public void testSimulazioneTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante() {
		Labirinto labirinto = this.simulazione.fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("prendi martello");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		gioco.processaIstruzione("vai nord");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale().
				getStanzaAdiacente("nord"), gioco.getPartita().getStanzaCorrente());
		assertEquals("Riservata", gioco.getPartita().getStanzaCorrente().getNome());
		
		gioco.processaIstruzione("posa martello");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("martello"));
		
		String bloccata = "Stanza bloccata in direzione " + "est" + "\nPrendi "
				+ "chiave " + "e posalo nella stanza";
		assertEquals(bloccata, gioco.getPartita().getStanzaCorrente().getDescrizione());
		
		gioco.processaIstruzione("vai est");
		assertEquals(gioco.getPartita().getStanzaCorrente().getStanzaAdiacente("est"),
				gioco.getPartita().getStanzaCorrente());
		
		gioco.processaIstruzione("vai sud");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getStanzaCorrente());
		
		gioco.processaIstruzione("prendi chiave");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("posa chiave");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("chiave"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("chiave"));
		
		gioco.processaIstruzione("vai est");
		assertEquals(gioco.getPartita().getStanzaCorrente(), gioco.getPartita().getLabirinto().getStanzaVincente());
		assertTrue(gioco.getPartita().vinta());	
	}
	
	@Test
	public void testSimulazionePartitaDifficile_ProvaComandiSpeciali() {
		Labirinto labirinto = this.simulazione.fixturePartitaDifficile();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("vai");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getStanzaCorrente());
		assertEquals("Atrio", gioco.getPartita().getStanzaCorrente().getNome());
		
		gioco.processaIstruzione("vai est");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getStanzaCorrente());
		assertEquals("Atrio", gioco.getPartita().getStanzaCorrente().getNome());
		
		gioco.processaIstruzione("prendi");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		
		gioco.processaIstruzione("prendi zaino");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("zaino"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("zaino"));
		
		gioco.processaIstruzione("prendi pistola");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("pistola"));
		assertFalse(gioco.getPartita().getStanzaCorrente().hasAttrezzo("pistola"));
		
		gioco.processaIstruzione("posa");
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 5);
		
		gioco.processaIstruzione("posa pistola");
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getAttrezzi().isEmpty());
		assertTrue(gioco.getPartita().getGiocatore().getBorsa().getPeso() == 0);
		assertFalse(gioco.getPartita().getGiocatore().getBorsa().hasAttrezzo("pistola"));
		assertTrue(gioco.getPartita().getStanzaCorrente().hasAttrezzo("pistola"));
		
		gioco.processaIstruzione("");
		assertEquals(gioco.getPartita().getLabirinto().getStanzaIniziale(), 
				gioco.getPartita().getStanzaCorrente());
		
		assertTrue(gioco.getPartita().giocatoreIsVivo());
		gioco.processaIstruzione("fine");
		assertTrue(gioco.getPartita().isFinita());	
	}
	
	@Test
	public void testSimulazionePartitaDifficile_SoloComandi() {
		Labirinto labirinto = this.simulazione.fixturePartitaDifficile();
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.processaIstruzione("prendi pistola");
		gioco.processaIstruzione("vai sud");
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("prendi lampada");
		gioco.processaIstruzione("vai sud");
		gioco.processaIstruzione("vai sud");
		gioco.processaIstruzione("posa lampada");
		gioco.processaIstruzione("prendi martello");
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("vai ovest");
		gioco.processaIstruzione("vai ovest");
		gioco.processaIstruzione("posa martello");
		gioco.processaIstruzione("vai nord");
		gioco.processaIstruzione("guarda");
		assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
		assertTrue(gioco.getPartita().vinta());	
	}
}
