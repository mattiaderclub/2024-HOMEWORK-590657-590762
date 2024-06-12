package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import static it.uniroma3.diadia.properties.CostantiDiaDia.elencoComandi;

public class ComandoAiuto extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		this.getIO().mostraMessaggio("");
	}
}
