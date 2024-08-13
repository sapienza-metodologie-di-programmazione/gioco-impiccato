package controller;

import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;
import view.Pannello;

public class ControlloGiocoImpiccato {

	private GiocoImpiccato modello;
	private FinestraGiocoImpiccato vista;

	public ControlloGiocoImpiccato(GiocoImpiccato modello, FinestraGiocoImpiccato vista) {
		this.modello = modello;
		this.vista = vista;
		inizializzaAzioniBottoni();
	}

	private void inizializzaAzioniBottoni() {
		inizializzaBottoniMenu();
		inizializzaBottoniStatistiche();
	}

	private void inizializzaBottoniMenu() {
		vista.getPannelloMenu().getBottoneGioco().addActionListener(e -> {
			vista.setPannelloGioco(modello.generaParola());
			inizializzaBottoniGioco();
			cambiaSchermata(Pannello.TipoPannello.GIOCO);
		});

		vista.getPannelloMenu().getBottoneStatistiche()
				.addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.STATISTICHE));
	}

	private void inizializzaBottoniStatistiche() {
		vista.getPannelloStatistiche().getBottoneMenu()
				.addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.MENU));
	}

	private void inizializzaBottoniGioco() {
		vista.getPannelloGioco().getBottoneMenu().addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.MENU));

	}

	private void cambiaSchermata(Pannello.TipoPannello schermata) {
		vista.visualizzaPannello(schermata);
		vista.repaint();
	}

}
