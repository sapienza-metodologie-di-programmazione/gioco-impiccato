package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe che rappresenta il modello del Gioco dell'Impiccato; gestisce le
 * statistiche delle partite giocate e contiene un riferimento all'eventuale
 * partita in corso
 */
public class GiocoImpiccato extends Observable {

	private Set<String> parole; // insieme da cui estrarre le parole da indovinare
	private int partiteGiocate;
	private int partiteVinte;
	private Optional<String> ultimaParolaIndovinata;
	private Optional<PartitaImpiccato> partitaCorrente;

	/*
	 * file con parole di default (1000 parole italiane) reperito da
	 * https://github.com/napolux/paroleitaliane
	 */
	private static String pathFileParole = "1000_parole_italiane_comuni.txt";

	public GiocoImpiccato() {
		this(pathFileParole);
	}

	/**
	 * Costruttore del modello del Gioco dell'Impiccato
	 * 
	 * @param il path del file di testo con le parole da indovinare
	 */
	public GiocoImpiccato(String path) {
		parole = caricaFileParole(path);
		ultimaParolaIndovinata = Optional.ofNullable(null);
		partitaCorrente = Optional.ofNullable(null);
	}

	private static Set<String> caricaFileParole(String path) {

		Set<String> parole = new HashSet<String>();
		/*
		 * costrutto "try with resources", si assicura che il file venga chiuso dopo
		 * l'utilizzo
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			/*
			 * uso di stream per eliminare le parole lunghe meno di 3 caratteri e gli
			 * accenti e per convertire tutte le lettere in minuscolo
			 */
			parole = br.lines().filter(l -> l.length() >= 3).map(l -> {
				Normalizer.normalize(l, Normalizer.Form.NFD);
				l.replaceAll("\\p{M}", "");
				return l;
			}).map(l -> l.toLowerCase()).collect(Collectors.toSet());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return parole;
	}

	public int getPartiteGiocate() {
		return partiteGiocate;
	}

	public int getPartiteVinte() {
		return partiteVinte;
	}

	public Optional<String> getUltimaParolaIndovinata() {
		return ultimaParolaIndovinata;
	}

	public Optional<PartitaImpiccato> getPartitaCorrente() {
		return partitaCorrente;
	}

	/**
	 * Metodo per estrarre casualmente una parola da indovinare dall'insieme di
	 * parole caricato da file
	 * 
	 * @return una parola da indovinare
	 */
	public String estraiParola() {
		return parole.stream().skip((int) (parole.size() * Math.random())).findFirst().get();
	}

	/**
	 * Metodo per creare una nuova partita del Gioco dell'Impiccato; se un'altra
	 * partita era già in corso essa viene sovrascritta
	 */
	public void iniziaPartita() {
		partitaCorrente = Optional.of(new PartitaImpiccato(this, estraiParola()));
	}

	/**
	 * Metodo per terminare la partita corrente del Gioco dell'Impiccato
	 */
	public void terminaPartita() {
		if (partitaCorrente.isPresent()) {
			partiteGiocate++;
			if (partitaCorrente.get().getStato().equals(PartitaImpiccato.StatoPartita.VINTA)) {
				partiteVinte++;
				ultimaParolaIndovinata = Optional.of(partitaCorrente.get().getParola());
			}
			notifyObservers(); // vengono aggiornate le statistiche nelle vista
			partitaCorrente = Optional.empty();
		}
	}

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
