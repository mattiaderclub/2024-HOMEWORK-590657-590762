package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_MENO_ATTREZZI;
import static it.uniroma3.diadia.properties.CostantiDiaDia.MESSAGGIO_PIU_ATTREZZI;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public Attrezzo getAttrezzo() {
		return null;
	}

	/*
	 * Agisci di Strega
	 * Classe nidificata mi consente di semplificarmi il tutto per evitare di creare classi che uso solo qua
	 * Salvo in una lista tutte le stanze adiacenti alla stanza corrente
	 * Se il personaggio è stato salutato allora viene spostato nella stanza con più attrezzi sfruttando Collections.max
	 * In caso contrario viene spostato nella stanza con meno attrezzi sfruttando Collections.min
	 */
	@Override
	public String agisci(Partita partita) {
		
		class ComparatorePerNumAttrezzi implements Comparator<Stanza> {
			@Override
			public int compare(Stanza s1, Stanza s2) {
				int cmp = s1.getAttrezzi().size() - s2.getAttrezzi().size();
				if (cmp == 0)
					cmp = s1.getNome().compareTo(s2.getNome());
				return cmp;
			}
		}
		
		String msg;
		ComparatorePerNumAttrezzi comparatore = new ComparatorePerNumAttrezzi();
		List<Stanza> ordinataPerNumAttrezzi = new LinkedList<Stanza>();
		ordinataPerNumAttrezzi.addAll(partita.getStanzaCorrente().getStanzeAdiacenti().values());
		
		if (!this.haSalutato()) {
			Stanza stanzaMenoAtt = Collections.min(ordinataPerNumAttrezzi, comparatore);
			partita.setStanzaCorrente(stanzaMenoAtt);
			msg = MESSAGGIO_MENO_ATTREZZI;
		}
		else {
			Stanza stanzaPiuAtt = Collections.max(ordinataPerNumAttrezzi, comparatore);
			partita.setStanzaCorrente(stanzaPiuAtt);
			msg = MESSAGGIO_PIU_ATTREZZI;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "IL MIO TESSSSSOROOO AHAHAHAH";
	}
}
