package model;

import java.nio.file.Path;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;

public class GiocoImpiccato extends Observable {

	private Set<String> parole;
	private int partiteGiocate;
	private int partiteVinte;
	private Optional<String> ultimaParolaIndovinata;
	private Optional<PartitaImpiccato> partitaCorrente;

	public GiocoImpiccato() {
	}

	public GiocoImpiccato(Path fileParole) {

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
		partitaCorrente = Optional.of(new PartitaImpiccato(this, "boh"));
		return b;
	}

	/**
	 * Metodo per terminare la partita corrente del Gioco dell'Impiccato
	 * 
	 * @return true se è presente una partita da terminare, false altrimentis
	 */
	public boolean terminaPartita() {
		boolean b = partitaCorrente.isPresent();
		if (b)
			partitaCorrente = Optional.empty();
		return b;
	}
}
