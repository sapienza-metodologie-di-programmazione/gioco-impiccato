package test;

import controller.ControlloGiocoImpiccato;
import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;

public class AvvioGioco {
	public static void main(String[] args) {
		GiocoImpiccato gioco = new GiocoImpiccato();
		FinestraGiocoImpiccato finestra = new FinestraGiocoImpiccato();
		ControlloGiocoImpiccato controllo = new ControlloGiocoImpiccato(gioco, finestra);
	}
}
