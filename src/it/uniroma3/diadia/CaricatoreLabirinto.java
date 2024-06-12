package it.uniroma3.diadia;

import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.ATTREZZI_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.CANI_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.MAGHI_MARKER;
//import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.PERSONAGGI_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZA_INIZIALE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZA_VINCENTE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZE_BLOCCATE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZE_BUIE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZE_MAGICHE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STANZE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.STREGHE_MARKER;
import static it.uniroma3.diadia.properties.CostantiCaricatoreLabirinto.USCITE_MARKER;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	/*
	 * Costruttore che crea la mappa e l'oggetto che servirà a leggere il file
	 */
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	/*
	 * Metodo che carica il labirinto Ovviamente i metodi vanno scritti in modo
	 * coerente rispetto a come figura la specifica nel file letto
	 */
	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaCani();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/*
	 * Metodo che serve a leggere le righe, nello specifico come iniziano Serve per
	 * i metodi successivi nei quali si usufruisce dell'uso delle costanti per
	 * caricare le varie componenti del labirinto
	 */
	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine(); // Lettura linea

			/*
			 * Sfrutto metodo check per vedere se la riga inizia davvero con il marker
			 * passato se negativo torno messaggio di errore specificato
			 */
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length()); // Ritorna stringa che parte da indice passato come parametro :
													// senza marker
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	/*
	 * Metodo che crea le stanze e le aggiunge alla mappa
	 */
	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER); // Leggo riga
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza); // Creo stanza
			this.nome2stanza.put(nomeStanza, stanza); // Aggiungo la stanza alla mappa
		}
	}

	/*
	 * Metodo che crea le stanze buie e le aggiunge alla mappa
	 */
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for (String specificaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanza = null;
			String attrezzoPerFareLuce = null;

			try (Scanner scanner = new Scanner(specificaBuia)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza."));
					nomeStanza = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo luminoso."));
					attrezzoPerFareLuce = scanner.next();
				}
			}
			Stanza stanzaBuia = new StanzaBuia(nomeStanza, attrezzoPerFareLuce);
			this.nome2stanza.put(nomeStanza, stanzaBuia);
		}
	}

	/*
	 * Metodo che crea le stanze bloccate e le aggiunge alla mappa
	 */
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for (String specificaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String attrezzoCheSblocca = null;

			try (Scanner scanner = new Scanner(specificaBloccata)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza."));
					nomeStanza = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("la direzione bloccata."));
					direzioneBloccata = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo sbloccante."));
					attrezzoCheSblocca = scanner.next();
				}
			}
			Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoCheSblocca);
			this.nome2stanza.put(nomeStanza, stanzaBloccata);
		}
	}

	/*
	 * Metodo che crea le stanze magiche e le aggiunge alla mappa
	 */
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);

		for (String specificaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			String nomeStanza = null;
			String sogliaMagica = null;

			try (Scanner scanner = new Scanner(specificaMagica)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza."));
					nomeStanza = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("la soglia magica della stanza."));
					sogliaMagica = scanner.next();
				}
			}
			int soglia = Integer.parseInt(sogliaMagica);
			Stanza stanzaMagica = new StanzaMagica(nomeStanza, soglia);
			this.nome2stanza.put(nomeStanza, stanzaMagica);
		}
	}

//	private void leggiECreaPersonaggi() throws FormatoFileNonValidoException {
//		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
//
//		for (String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
//
//			String tipoPersonaggio = null;
//			String nomePersonaggio = null;
//			String ubicazione = null;
//			String nomeAttrezzoPersonaggio = null;
//			String pesoAttrezzoPersonaggio = null;
//
//			try (Scanner scanner = new Scanner(specificaPersonaggio)) {
//				while (scanner.hasNext()) {
//					check(scanner.hasNext(), msgTerminazionePrecoce("il tipo del personaggio."));
//					tipoPersonaggio = scanner.next();
//					check(scanner.hasNext(), msgTerminazionePrecoce("il nome del personaggio."));
//					nomePersonaggio = scanner.next();
//					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza dove va collocato."));
//					ubicazione = scanner.next();
//
//					if (tipoPersonaggio.equals("Mago")) {
//						check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo tenuto."));
//						nomeAttrezzoPersonaggio = scanner.next();
//						check(scanner.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo tenuto."));
//						pesoAttrezzoPersonaggio = scanner.next();
//					}
//				}
//			}
//			inizializzaPersonaggio(tipoPersonaggio, nomePersonaggio, ubicazione, nomeAttrezzoPersonaggio,
//					pesoAttrezzoPersonaggio);
//		}
//	}
//
//	private void inizializzaPersonaggio(String tipo, String nome, String stanza, String nomeAtt, String pesoAtt)
//			throws FormatoFileNonValidoException {
//		try {
//			String nomeClasse = "it.uniroma3.diadia.personaggi." + tipo;
//			AbstractPersonaggio personaggio = (AbstractPersonaggio) Class.forName(nomeClasse).newInstance();
//			
//		}
//		catch {
//			/*
//			 * CATCH eccezione che non esiste la classe -> messaggio che non esiste la classe : con check ?
//			 * CATCH che il ParseInt della stringa del peso restituisce un vero numero
//			 * Aggiungere i set a AbstractPersonaggio con implementazioni di metodi astratti se necessario (sicuramente necessario)
//			 * 
//			 * Quanto lo vogliamo fare difficile ?
//			 * Usiamo check in leggiECreaPersonaggi per controllare che il tipo è uguale 
//			 * a Mago o Strega o Cane o lo vogliamo fare qua
//			 * Tante alternative
//			 */
//		}
//	}

	/*
	 * Metodo che crea i cani
	 */
	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for (String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String ubicazione = null;

			try (Scanner scanner = new Scanner(specificaCane)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome del cane."));
					nomeCane = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza dove va collocato."));
					ubicazione = scanner.next();
				}
			}
			inizializzaCane(nomeCane, ubicazione);
		}
	}

	private void inizializzaCane(String nomeCane, String nomeStanza) throws FormatoFileNonValidoException {
		String presenta = "Woof woof sono il cane" + nomeCane;
		AbstractPersonaggio cane = new Cane(nomeCane, presenta);
		check(isStanzaValida(nomeStanza),
				"Cane " + nomeCane + " non collocabile: stanza " + nomeStanza + " inesistente");
		this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
	}

	/*
	 * Metodo che crea i maghi
	 */
	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for (String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String nomeAtt = null;
			String pesoAtt = null;
			String ubicazione = null;

			try (Scanner scanner = new Scanner(specificaMago)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome del mago."));
					nomeMago = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo tenuto."));
					nomeAtt = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo tenuto."));
					pesoAtt = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza dove va collocato."));
					ubicazione = scanner.next();
				}
			}
			inizializzaMago(nomeMago, nomeAtt, pesoAtt, ubicazione);
		}
	}

	private void inizializzaMago(String nomeMago, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo); // Converte stringa in intero
			Attrezzo attrezzoMago = new Attrezzo(nomeAttrezzo, peso);
			String presenta = "Ciao sono il mago" + nomeMago + " e posso darti questo : " + nomeAttrezzo;
			AbstractPersonaggio mago = new Mago(nomeMago, presenta, attrezzoMago);
			check(isStanzaValida(nomeStanza),
					"Mago " + nomeMago + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
			;
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido"); // Passo false perchè in check si trasforma
																			// in true e verifica condizione (guarda
																			// check -> if)
		}
	}

	/*
	 * Metodo che crea le streghe
	 */
	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for (String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String ubicazione = null;

			try (Scanner scanner = new Scanner(specificaStrega)) {
				while (scanner.hasNext()) {
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della strega."));
					nomeStrega = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza dove va collocata."));
					ubicazione = scanner.next();
				}
			}
			inizializzaStrega(nomeStrega, ubicazione);
		}
	}

	private void inizializzaStrega(String nomeStrega, String nomeStanza) throws FormatoFileNonValidoException {
		String presenta = "Ciao sono la strega" + nomeStrega + ", attento a come ti comporti";
		AbstractPersonaggio strega = new Strega(nomeStrega, presenta);
		check(isStanzaValida(nomeStanza),
				"Strega " + nomeStrega + " non collocabile: stanza " + nomeStanza + " inesistente");
		this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
		;
	}

	/*
	 * Metodo che scannerizza la stringa passata non contando le virgole (virgole
	 * separano le stringhe utili)
	 */
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(","); // Si segna un simbolo che delimita le stringhe lette
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next()); // Aggiunge stringa alla lista
		}
		return result;
	}

	/*
	 * Metodo che legge dal file il nome della stanza iniziale e di quella vincente
	 */
	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	/*
	 * Metodo che legge dal file la specifica degli attrezzi (nome, peso, stanza in
	 * cui si trova)
	 */
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;

			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				while (scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
					nomeAttrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),
							msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
					pesoAttrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(), msgTerminazionePrecoce(
							"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
					nomeStanza = scannerLinea.next();
				}
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza); // Guarda descrizione di posaAttrezzo
		}
	}

	/*
	 * Metodo che riceve 3 stringhe (nome dell'attrezzo, peso dell'attrezzo (lo
	 * legge da File) e il nome della stanza dove va aggiunto Cattura una
	 * NumberFormatException : String pesoAttrezzo non contiene qualcosa di
	 * convertibile a intero
	 */
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo); // Converte stringa in intero
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido"); // Passo false perchè in check si trasforma
																			// in true e verifica condizione (guarda
																			// check -> if)
		}
	}

	/*
	 * Controlla se la stanza esiste all'interno della mappa
	 */
	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	/*
	 * Metodo che leggendo da un file imposta le adiacenze delle stanze Gruppi di 3
	 * stringhe Prima stringa letta -> stanza di partenza Seconda stringa letta ->
	 * direzione dell'adiacenza , direzione stanza d'uscita Terza stringa letta ->
	 * stanza d'uscita , stanza adiacente
	 */
	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);

		for (String specificaUscite : separaStringheAlleVirgole(specificheUscite)) {

			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null;

			try (Scanner scannerDiLinea = new Scanner(specificaUscite)) {
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
					stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),
							msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
					dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la destinazione di una uscita della stanza "
							+ stanzaPartenza + " nella direzione " + dir));
					stanzaDestinazione = scannerDiLinea.next();

				}
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione); // Connette stanze passate
			}
		}
	}

	/*
	 * Messaggio che avverte della presenza di un errore
	 */
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	/*
	 * Connette due stanze date 3 stringhe come parametri : rispettivamente nome
	 * della stanza di partenza, direzione, nome stanza di uscita
	 */
	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}

	/*
	 * Metodo che gestisce gli errori ricevendo un booleano che rappresenta una
	 * condizione da verificare Se if contiene true allora significa che siamo di
	 * fronte a un errore Spesso quando si usa check il booleano corrisponde a
	 * isStanzaValida
	 */
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getMappaCaricata() {
		return this.nome2stanza;
	}
}
