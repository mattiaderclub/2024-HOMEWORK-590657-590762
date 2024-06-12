package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private AbstractComando comando;

	@Override
	public AbstractComando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		
		this.comando = null;
		IO io = new IOConsole(scannerDiParole);
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();// seconda parola: eventuale parametro
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (AbstractComando) Class.forName(nomeClasse).newInstance();
			comando.setNome(nomeComando);
			comando.setIO(io);
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
			comando.setNome("non valido");
			comando.setIO(io);
			//io.mostraMessaggio("Comando inesistente");
		}
		return comando;
	}

	@Override
	public AbstractComando getComando() {
		return this.comando;
	}
}
