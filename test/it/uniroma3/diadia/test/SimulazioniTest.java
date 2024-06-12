package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Simulazioni;

public class SimulazioniTest {

//	private Simulazioni simulazione;
//	private List<String> comandiDaLeggere;
//	private IOSimulator io;
//	
//	@Before
//	public void setUp() {
//		this.simulazione = new Simulazioni();
//		this.comandiDaLeggere = new LinkedList<String>();
//	}
//
//	@Test
//	public void testSimulazione_Monolocale() {
//		comandiDaLeggere.add("guarda");
//		this.io = this.simulazione.simulazioneDiaDia_Monolocale(comandiDaLeggere);
//		assertTrue(io.getMessaggiDaLeggere().contains("guarda"));
//		assertTrue(io.getMessaggiProdotti().contains("Hai vinto!"));
//	}
//	
//	@Test
//	public void testSimulazione_Bilocale() {
//		comandiDaLeggere.add("prendi martello");
//		comandiDaLeggere.add("vai sud");
//		System.out.println(comandiDaLeggere.toString());
//		this.io = this.simulazione.simulazioneDiaDia_Bilocale(comandiDaLeggere);
//		assertTrue(io.getMessaggiDaLeggere().contains("prendi martello"));
//		assertTrue(io.getMessaggiDaLeggere().contains("vai sud"));
//		assertFalse(io.getMessaggiProdotti().contains("Hai vinto!"));
//	}
//	
//	@Test
//	public void testSimulazione_Trilocale_ConStanzaBuia_ConAttrezzoLuminoso() {
//		this.io = this.simulazione.simulazioneDiaDia_Trilocale_ConStanzaBuia_ConAttrezzoLuminoso(comandiDaLeggere);
//	}
//	
//	@Test
//	public void testSimulazione_Trilocale_ConStanzaBuia_SenzaAttrezzoLuminoso() {
//		this.io = this.simulazione.simulazioneDiaDia_Trilocale_ConStanzaBuia_SenzaAttrezzoLuminoso(comandiDaLeggere);
//	}
//	
//	@Test
//	public void testSimulazione_Trilocale_ConStanzaMagica() {
//		this.io = this.simulazione.simulazioneDiaDia_Trilocale_ConStanzaMagica(comandiDaLeggere);
//	}
//	
//	@Test
//	public void testSimulazione_Trilocale_ConStanzaBloccata_ConAttrezzoSbloccante() {
//		this.io = this.simulazione.simulazioneDiaDia_Trilocale_ConStanzaBloccata_ConAttrezzoSbloccante(comandiDaLeggere);
//	}
//	
//	@Test
//	public void testSimulazione_PartitaDifficile() {
//		this.io = this.simulazione.simulazioneDiaDia_PartitaDifficile(comandiDaLeggere);
//	}
}
