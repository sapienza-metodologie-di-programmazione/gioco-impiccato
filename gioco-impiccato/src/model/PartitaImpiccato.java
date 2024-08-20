package model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe che modella una partita del Gioco dell'Impiccato; gestisce le lettere
 * usate, il numero di errori e determina l'esito di vittoria o sconfitta
 */
public class PartitaImpiccato {

	public enum StatoPartita {
		IN_CORSO, PERSA, VINTA
	}

	private GiocoImpiccato gioco;
	private StatoPartita stato;
	private String parola; // parola da indovinare
	private int errori; // conteggio errori commessi con le lettere scelte
	private Set<Character> lettereParola; // lettere che compongono la parola da indovinare
	private Set<Character> lettereUsate; // lettere selezionate dal giocatore fino al momento corrente

	public static final int MAX_ERRORI = 5; // massimo numero di errori concessi prima di perdere la partita

	/**
	 * Costruttore di una partita del Gioco dell'Impiccato
	 * 
	 * @param istanza di Gioco cui la partita appartiene
	 * @param parola  da indovinare
	 */
	public PartitaImpiccato(GiocoImpiccato gioco, String parola) {
		this.gioco = gioco;
		this.parola = parola;
		stato = StatoPartita.IN_CORSO;
		errori = 0;
		lettereParola = parola.chars().mapToObj(c -> (Character) (char) c).collect(Collectors.toSet());
		lettereUsate = new HashSet<Character>();
	}

	public GiocoImpiccato getGioco() {
		return gioco;
	}

	public StatoPartita getStato() {
		return stato;
	}

	public String getParola() {
		return parola;
	}

	public int getErrori() {
		return errori;
	}

	public Set<Character> getLettereParola() {
		return lettereParola;
	}

	public Set<Character> getLettereUsate() {
		return lettereUsate;
	}

	/**
	 * Metodo che aggiunge un carattere all'insieme delle lettere usate; nel caso in
	 * cui il carattere non appartenga alle lettere della parola da indovinare viene
	 * aumentato di 1 il numero degli errori. Vengono inoltre controllate le
	 * condizioni di vittoria o sconfitta ed eventualmente viene terminata la
	 * partita
	 * 
	 * @param lettera usata
	 */
	public void aggiungiLetteraUsata(Character lettera) {

		lettereUsate.add(lettera);
		if (!lettereParola.contains(lettera))
			errori++;

		// si aggiorna il numero di vite nella vista
		gioco.notifyObservers();

		if (checkSconfitta()) {
			stato = StatoPartita.PERSA;
			gioco.terminaPartita();
		}

		else if (checkVittoria()) {
			stato = StatoPartita.VINTA;
			gioco.terminaPartita();
		}

	}

	/*
	 * si vince una partita quando tutte le lettere della parola da indovinare sono
	 * scoperte (l'insieme delle lettere usate contiene tutte le lettere della
	 * parola)
	 */
	private boolean checkVittoria() {
		return lettereUsate.containsAll(lettereParola);
	}

	/*
	 * si perde una partita quando si raggiunge il massimo numero di errori
	 */
	private boolean checkSconfitta() {
		return errori == MAX_ERRORI;
	}
}
