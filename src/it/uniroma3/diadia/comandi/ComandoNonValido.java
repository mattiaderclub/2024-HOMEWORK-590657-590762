package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Comando sconosciuto"); // Si desidera smettere
	}
}
