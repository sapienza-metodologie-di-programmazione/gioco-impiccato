package controller;

import java.util.Optional;

import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;
import view.Pannello;
import view.PannelloGioco;

public class ControlloGiocoImpiccato {

	private GiocoImpiccato modello;
	private FinestraGiocoImpiccato vista;

	public ControlloGiocoImpiccato(GiocoImpiccato modello, FinestraGiocoImpiccato vista) {
		this.modello = modello;
		this.vista = vista;
		modello.addObserver(vista.getPannelloStatistiche());
		inizializzaAzioniBottoni();
	}

	private void inizializzaAzioniBottoni() {
		inizializzaBottoniMenu();
		inizializzaBottoniStatistiche();
	}

	private void inizializzaBottoniMenu() {
		vista.getPannelloMenu().getBottoneGioco().addActionListener(e -> {
			Optional<PannelloGioco> vecchio = vista.getPannelloGioco();
			if (vecchio.isPresent())
				modello.deleteObserver(vecchio.get());
			modello.iniziaPartita();
			vista.setPannelloGioco(modello.getPartitaCorrente().get().getParola());
			modello.addObserver(vista.getPannelloGioco().get());
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
		vista.getPannelloGioco().get().getBottoneMenu()
				.addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.MENU));

	}

	private void cambiaSchermata(Pannello.TipoPannello schermata) {
		vista.visualizzaPannello(schermata);
		vista.repaint();
	}

}
