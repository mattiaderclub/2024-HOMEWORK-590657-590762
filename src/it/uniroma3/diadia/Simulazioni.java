package it.uniroma3.diadia;

import java.util.List;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.properties.Fixture;

public class Simulazioni {
	
	private Fixture scenario;
	
	public Simulazioni() {
		this.scenario = new Fixture();
	}

	public IOSimulator simulazioneDiaDia_Monolocale(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureMonolocale();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_Bilocale(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureBilocale();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_Trilocale_ConStanzaBuia_ConAttrezzoLuminoso(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureTrilocale_ConStanzaBuia_ConAttrezzoLuminoso();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_Trilocale_ConStanzaBuia_SenzaAttrezzoLuminoso(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureTrilocale_ConStanzaBuia_SenzaAttrezzoLuminoso();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_Trilocale_ConStanzaMagica(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureTrilocale_ConStanzaMagica();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_Trilocale_ConStanzaBloccata_ConAttrezzoSbloccante(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixtureTrilocale_ConStanzaBloccata_ConAttrezzoSbloccante();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
	
	public IOSimulator simulazioneDiaDia_PartitaDifficile(List<String> istruzioni) {
		IOSimulator io = new IOSimulator(istruzioni);
		Labirinto maze = this.scenario.fixturePartitaDifficile();
		
		DiaDia gioco = new DiaDia(maze, io);
		gioco.gioca();
		return io;
	}
}
