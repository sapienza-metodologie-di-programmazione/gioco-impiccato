package model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

	public PartitaImpiccato(GiocoImpiccato gioco, String parola) {
		this.gioco = gioco;
		stato = StatoPartita.IN_CORSO;
		this.parola = parola;
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

	public void aumentaErrori() {
		errori++;
	}

	public void aggiungiLetteraUsata(Character lettera) {
		lettereUsate.add(lettera);
	}

	// metodo per controllare se si sono verificate le condizioni di vittoria
	// della partita
	public boolean checkVittoria() {
		return !checkSconfitta() && lettereUsate.containsAll(lettereParola);
	}

	public boolean checkSconfitta() {
		return errori > MAX_ERRORI;
	}
}
