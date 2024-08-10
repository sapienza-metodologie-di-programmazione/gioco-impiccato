package model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

public class GiocoImpiccato {

	private static Set<String> parole;

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

}
