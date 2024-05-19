package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaOrdinamentiTest {
	
	private Borsa borsa;
	private Attrezzo piombo;
	private Attrezzo ps;
	private Attrezzo piuma;
	private Attrezzo libro;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.piombo = new Attrezzo("piombo", 3);
		this.ps = new Attrezzo("ps", 4);
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 1);
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(ps);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
	}
	
	@Test
	public void getContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinato = borsa.getContenutoOrdinatoPerPeso();
		assertEquals("libro", ordinato.get(0).getNome());
		assertEquals("piuma", ordinato.get(1).getNome());
		assertEquals(3, ordinato.get(2).getPeso());
		assertEquals(4, ordinato.get(3).getPeso());
	}
	
	@Test
	public void getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinato = borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = ordinato.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		assertEquals("libro", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("piombo", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("piuma", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("ps", it.next().getNome());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> ordinato = borsa.getContenutoRaggruppatoPerPeso();		
		Iterator<Attrezzo> it = ordinato.get(4).iterator();
		
		assertTrue(it.hasNext());
		assertEquals("ps", it.next().getNome());
		assertTrue(!it.hasNext());
		
		it = ordinato.get(3).iterator();
		assertTrue(it.hasNext());
		assertEquals("piombo", it.next().getNome());
		assertTrue(!it.hasNext());
		
		it = ordinato.get(1).iterator();
		assertTrue(it.hasNext());
		assertEquals("piuma", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("libro", it.next().getNome());
		assertTrue(!it.hasNext());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinato = borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = ordinato.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		assertEquals("libro", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("piuma", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("piombo", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("ps", it.next().getNome());
		assertFalse(it.hasNext());
	}	
}
