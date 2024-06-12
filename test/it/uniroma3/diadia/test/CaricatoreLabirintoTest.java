package it.uniroma3.diadia.test;

import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.BILOCALE;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.MONOLOCALE;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.MULTILOCALE_CON_CREATURE;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.MULTILOCALE_CON_STANZE_SPECIALI;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.TRILOCALE;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto maze;

	@Test
	public void testCarica_Monolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.maze = new CaricatoreLabirinto(new StringReader(MONOLOCALE));
		this.maze.carica();
		assertEquals("Atrio", this.maze.getStanzaIniziale().getNome());
		assertEquals("Atrio", this.maze.getStanzaVincente().getNome());
		assertTrue(this.maze.getMappaCaricata().containsKey("Atrio"));
	}
	
	@Test
	public void testCarica_Bilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.maze = new CaricatoreLabirinto(new StringReader(BILOCALE));
		this.maze.carica();
		
		/* Controllo stanza iniziale e stanza vincente */
		assertEquals("Atrio", this.maze.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", this.maze.getStanzaVincente().getNome());
		
		/* Controllo attrezzi */
		assertTrue(this.maze.getMappaCaricata().get("Atrio").hasAttrezzo("martello"));
		assertEquals(3, this.maze.getMappaCaricata().get("Atrio").getAttrezzo("martello").getPeso());
		
		/* Controllo adiacenze */
		assertEquals("Biblioteca", this.maze.getMappaCaricata().get("Atrio").getStanzaAdiacente("nord").getNome());
		assertEquals("Atrio", this.maze.getMappaCaricata().get("Biblioteca").getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testCarica_Trilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.maze = new CaricatoreLabirinto(new StringReader(TRILOCALE));
		this.maze.carica();

		/* Controllo stanza iniziale e stanza vincente */
		assertEquals("Atrio", this.maze.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", this.maze.getStanzaVincente().getNome());
		
		/* Controllo attrezzi */
		assertTrue(this.maze.getMappaCaricata().get("Atrio").hasAttrezzo("martello"));
		assertEquals(3, this.maze.getMappaCaricata().get("Atrio").getAttrezzo("martello").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Atrio").hasAttrezzo("torcia"));
		assertEquals(1, this.maze.getMappaCaricata().get("Atrio").getAttrezzo("torcia").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Laboratorio").hasAttrezzo("spada"));
		assertEquals(5, this.maze.getMappaCaricata().get("Laboratorio").getAttrezzo("spada").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Biblioteca").hasAttrezzo("libro"));
		assertEquals(2, this.maze.getMappaCaricata().get("Biblioteca").getAttrezzo("libro").getPeso());
		
		
		/* Controllo adiacenze */
		assertEquals("Biblioteca", this.maze.getMappaCaricata().get("Atrio").getStanzaAdiacente("nord").getNome());
		assertEquals("Laboratorio", this.maze.getMappaCaricata().get("Atrio").getStanzaAdiacente("est").getNome());
		assertEquals("Atrio", this.maze.getMappaCaricata().get("Biblioteca").getStanzaAdiacente("sud").getNome());
		assertEquals("Atrio", this.maze.getMappaCaricata().get("Laboratorio").getStanzaAdiacente("ovest").getNome());
	}

	@Test
	public void testCarica_Multilocale_ConStanzeSpeciali() throws FileNotFoundException, FormatoFileNonValidoException {
		this.maze = new CaricatoreLabirinto(new StringReader(MULTILOCALE_CON_STANZE_SPECIALI));
		this.maze.carica();

		/* Controllo stanza iniziale e stanza vincente */
		assertEquals("Atrio", this.maze.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", this.maze.getStanzaVincente().getNome());
		
		/* Controllo attrezzi */
		assertTrue(this.maze.getMappaCaricata().get("Atrio").hasAttrezzo("martello"));
		assertEquals(3, this.maze.getMappaCaricata().get("Atrio").getAttrezzo("martello").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Laboratorio").hasAttrezzo("spada"));
		assertEquals(5, this.maze.getMappaCaricata().get("Laboratorio").getAttrezzo("spada").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Biblioteca").hasAttrezzo("libro"));
		assertEquals(2, this.maze.getMappaCaricata().get("Biblioteca").getAttrezzo("libro").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Caverna").hasAttrezzo("chiave"));
		assertEquals(4, this.maze.getMappaCaricata().get("Caverna").getAttrezzo("chiave").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Incantesimi").hasAttrezzo("torcia"));
		assertEquals(1, this.maze.getMappaCaricata().get("Incantesimi").getAttrezzo("torcia").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Portale").hasAttrezzo("medaglia"));
		assertEquals(0, this.maze.getMappaCaricata().get("Portale").getAttrezzo("medaglia").getPeso());
		
		/* Controllo adiacenze */
		assertEquals("Caverna", this.maze.getMappaCaricata().get("Atrio").getStanzaAdiacente("sud").getNome());
		assertEquals("Atrio", this.maze.getMappaCaricata().get("Caverna").getStanzaAdiacente("nord").getNome());
		assertEquals("Laboratorio", this.maze.getMappaCaricata().get("Caverna").getStanzaAdiacente("ovest").getNome());
		assertEquals("Caverna", this.maze.getMappaCaricata().get("Laboratorio").getStanzaAdiacente("est").getNome());
		assertEquals("Incantesimi", this.maze.getMappaCaricata().get("Laboratorio").getStanzaAdiacente("ovest").getNome());
		assertEquals("Laboratorio", this.maze.getMappaCaricata().get("Incantesimi").getStanzaAdiacente("est").getNome());
		assertEquals("Portale", this.maze.getMappaCaricata().get("Incantesimi").getStanzaAdiacente("ovest").getNome());
		assertEquals("Incantesimi", this.maze.getMappaCaricata().get("Portale").getStanzaAdiacente("est").getNome());
		assertEquals("Portale", this.maze.getMappaCaricata().get("Portale").getStanzaAdiacente("nord").getNome());
		assertEquals(0, this.maze.getMappaCaricata().get("Biblioteca").getStanzeAdiacenti().size());
	}
	
	@Test
	public void testCarica_Multilocale_ConCreature() throws FileNotFoundException, FormatoFileNonValidoException {
		this.maze = new CaricatoreLabirinto(new StringReader(MULTILOCALE_CON_CREATURE));
		this.maze.carica();

		/* Controllo stanza iniziale e stanza vincente */
		assertEquals("Atrio", this.maze.getStanzaIniziale().getNome());
		assertEquals("Biblioteca", this.maze.getStanzaVincente().getNome());
		
		/* Controllo attrezzi */
		assertTrue(this.maze.getMappaCaricata().get("Atrio").hasAttrezzo("martello"));
		assertEquals(3, this.maze.getMappaCaricata().get("Atrio").getAttrezzo("martello").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Laboratorio").hasAttrezzo("spada"));
		assertEquals(5, this.maze.getMappaCaricata().get("Laboratorio").getAttrezzo("spada").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Biblioteca").hasAttrezzo("libro"));
		assertEquals(2, this.maze.getMappaCaricata().get("Biblioteca").getAttrezzo("libro").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Caverna").hasAttrezzo("chiave"));
		assertEquals(4, this.maze.getMappaCaricata().get("Caverna").getAttrezzo("chiave").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Incantesimi").hasAttrezzo("torcia"));
		assertEquals(1, this.maze.getMappaCaricata().get("Incantesimi").getAttrezzo("torcia").getPeso());
		assertTrue(this.maze.getMappaCaricata().get("Portale").hasAttrezzo("passepartout"));
		assertEquals(0, this.maze.getMappaCaricata().get("Portale").getAttrezzo("passepartout").getPeso());
		
		/* Controllo creature */
		assertNull(this.maze.getMappaCaricata().get("Portale").getPersonaggio());
		assertEquals("Merlino", this.maze.getMappaCaricata().get("Laboratorio").getPersonaggio().getNome());
		assertTrue(this.maze.getMappaCaricata().get("Laboratorio").getPersonaggio().getAttrezzo() != null);
		assertEquals(7, this.maze.getMappaCaricata().get("Laboratorio").getPersonaggio().getAttrezzo().getPeso());
		assertEquals("Befana", this.maze.getMappaCaricata().get("Caverna").getPersonaggio().getNome());
		assertTrue(this.maze.getMappaCaricata().get("Caverna").getPersonaggio().getAttrezzo() == null);
		assertEquals("Spike", this.maze.getMappaCaricata().get("Atrio").getPersonaggio().getNome());
		assertTrue(this.maze.getMappaCaricata().get("Atrio").getPersonaggio().getAttrezzo() == null);
		
		/* Controllo adiacenze */
		assertEquals("Caverna", this.maze.getMappaCaricata().get("Atrio").getStanzaAdiacente("sud").getNome());
		assertEquals("Atrio", this.maze.getMappaCaricata().get("Caverna").getStanzaAdiacente("nord").getNome());
		assertEquals("Laboratorio", this.maze.getMappaCaricata().get("Caverna").getStanzaAdiacente("ovest").getNome());
		assertEquals("Caverna", this.maze.getMappaCaricata().get("Laboratorio").getStanzaAdiacente("est").getNome());
		assertEquals("Incantesimi", this.maze.getMappaCaricata().get("Laboratorio").getStanzaAdiacente("ovest").getNome());
		assertEquals("Laboratorio", this.maze.getMappaCaricata().get("Incantesimi").getStanzaAdiacente("est").getNome());
		assertEquals("Portale", this.maze.getMappaCaricata().get("Incantesimi").getStanzaAdiacente("ovest").getNome());
		assertEquals("Incantesimi", this.maze.getMappaCaricata().get("Portale").getStanzaAdiacente("est").getNome());
		assertEquals("Biblioteca", this.maze.getMappaCaricata().get("Portale").getStanzaAdiacente("nord").getNome());
		assertEquals(0, this.maze.getMappaCaricata().get("Biblioteca").getStanzeAdiacenti().size());
	}

}
