 package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.properties.CostantiDiaDia.SOGLIA_MAGICA_DEFAULT;

public class StanzaMagica extends Stanza {

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		
		if (this.isMagica())
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
	
	public boolean isMagica() {
		return this.contatoreAttrezziPosati > this.sogliaMagica;
	}
	
	public int getQuantiAttrezziPosati() {
		return this.contatoreAttrezziPosati;
	}
	
	public int getSoglia() {
		return this.sogliaMagica;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		StanzaMagica that = (StanzaMagica) o;
		return super.equals(o) && this.getQuantiAttrezziPosati() == that.getQuantiAttrezziPosati() 
				&& this.getSoglia() == that.getSoglia();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.getQuantiAttrezziPosati() + this.getSoglia();
	}
}
