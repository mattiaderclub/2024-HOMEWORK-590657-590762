package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandi factory;
	
	@Test
	public void testGetInformazioni_VaiNordMiCreaUnComandoVaiNord() {
		factory = new FabbricaDiComandiRiflessiva();
		factory.costruisciComando("vai nord");
		assertEquals("vai", factory.getComando().getNome());
		assertEquals("nord", factory.getComando().getParametro());
	}
	
	@Test
	public void testGetInformazioni_VuotoMiCreaComandoNonValido() {
		factory = new FabbricaDiComandiRiflessiva();
		factory.costruisciComando("vuoto");
		assertEquals("non valido" , factory.getComando().getNome());
		assertNull(factory.getComando().getParametro());
	}
	
	@Test
	public void testGetInformazioni_VaiNordNonMiCreaUnComandoPosaAttrezzo() {
		factory = new FabbricaDiComandiRiflessiva();
		factory.costruisciComando("vai nord");
		assertNotEquals("posa", factory.getComando().getNome());
		assertNotEquals("attrezzo", factory.getComando().getParametro());
	}
}
