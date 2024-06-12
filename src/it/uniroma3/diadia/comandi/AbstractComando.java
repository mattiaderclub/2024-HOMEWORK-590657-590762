package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {

	private IO io;
	private String nome;
	private String parametro;
	
	public void setIO(IO io) {
		this.io = io;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public IO getIO() {
		return this.io;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	abstract public void esegui(Partita partita);
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		AbstractComando that = (AbstractComando) o;
		return that.getNome().equals(this.getNome()) && 
				this.getParametro().equals(that.getParametro());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode() + 
				this.getParametro().hashCode();
	}
}
