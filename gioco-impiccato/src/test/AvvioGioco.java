package test;

import controller.ControlloGiocoImpiccato;
import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;

//classe di test del Gioco dell'Impiccato
public class AvvioGioco {

	public static void main(String[] args) {

		GiocoImpiccato gioco = new GiocoImpiccato();
		FinestraGiocoImpiccato finestra = new FinestraGiocoImpiccato();
		new ControlloGiocoImpiccato(gioco, finestra);

	}
}
