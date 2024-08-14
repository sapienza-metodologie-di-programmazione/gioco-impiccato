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

public class GiocoImpiccato extends Observable {

	private Set<String> parole;
	private int partiteGiocate;
	private int partiteVinte;
	private Optional<String> ultimaParolaIndovinata;
	private Optional<PartitaImpiccato> partitaCorrente;

	private static String pathFileParole = "1000_parole_italiane_comuni.txt";

	public GiocoImpiccato() {
		this(pathFileParole);
	}

	public GiocoImpiccato(String path) {
		parole = caricaFileParole(path);
		ultimaParolaIndovinata = Optional.ofNullable(null);
		partitaCorrente = Optional.ofNullable(null);
	}

	private static Set<String> caricaFileParole(String path) {

		Set<String> parole = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			parole = br.lines().filter(l -> l.length() >= 3).map(l -> {
				Normalizer.normalize(l, Normalizer.Form.NFD);
				l.replaceAll("\\p{M}", "");
				return l;
			}).map(l -> l.toLowerCase()).collect(Collectors.toSet());

		} catch (IOException e) {
			System.out.println("Errore: file parole non caricato");
		}

		return parole;
	}

	public String generaParola() {
		return parole.stream().skip((int) (parole.size() * Math.random())).findFirst().get();
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

	public void aumentaPartiteGiocate() {
		partiteGiocate++;
	}

	public void aumentaPartiteVinte() {
		partiteVinte++;
	}

	public void setUltimaParolaIndovinata(String parola) {
		ultimaParolaIndovinata = Optional.of(parola);
	}

	/**
	 * Metodo per creare una nuova partita del Gioco dell'Impiccato; se un'altra
	 * partita era già in corso essa viene sovrascritta
	 * 
	 * @return true se la nuova partita non sovrascrive alcuna partita
	 *         pre-esistente, false altrimenti
	 */
	public boolean iniziaPartita() {
		boolean b = partitaCorrente.isEmpty();
		partitaCorrente = Optional.of(new PartitaImpiccato(this, generaParola()));
		return b;
	}

	/**
	 * Metodo per terminare la partita corrente del Gioco dell'Impiccato
	 * 
	 * @return true se è presente una partita da terminare, false altrimentis
	 */
	public boolean terminaPartita() {
		boolean b = partitaCorrente.isPresent();
		if (b) {
			setChanged();
			notifyObservers(partitaCorrente.get().getStato());
			partitaCorrente = Optional.empty();
		}
		return b;
	}
}
