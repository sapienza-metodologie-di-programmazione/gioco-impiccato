package controller;

import java.util.List;
import java.util.Optional;

import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;
import view.Pannello;
import view.PannelloEsito;
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

	private void inizializzaPartita() {
		Optional<PannelloGioco> vecchio = vista.getPannelloGioco();
		if (vecchio.isPresent())
			modello.deleteObserver(vecchio.get());
		modello.iniziaPartita();
		vista.setPannelloGioco(modello.getPartitaCorrente().get().getParola());
		modello.addObserver(vista.getPannelloGioco().get());
		modello.addObserver(vista.getPannelloGioco().get().getPannelloVittoria());
		modello.addObserver(vista.getPannelloGioco().get().getPannelloSconfitta());
	}

	private void inizializzaBottoniMenu() {
		vista.getPannelloMenu().getBottoneGioco().addActionListener(e -> {
			inizializzaPartita();
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
		inizializzaBottoneMenuDaGioco();
		inizializzaBottoniLettere();
		inizializzaBottoneNuovaParola();
	}

	private void inizializzaBottoneMenuDaGioco() {
		vista.getPannelloGioco().get().getBottoneMenu()
				.addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.MENU));
	}

	private void inizializzaBottoniLettere() {
		vista.getPannelloGioco().get().getBottoniLettere().forEach(
				(c, b) -> b.addActionListener(e -> modello.getPartitaCorrente().get().aggiungiLetteraUsata(c)));
	}

	private void inizializzaBottoneNuovaParola() {
		for (PannelloEsito p : List.of(vista.getPannelloGioco().get().getPannelloVittoria(),
				vista.getPannelloGioco().get().getPannelloSconfitta())) {
			p.getBottoneNuovaParola().addActionListener(e -> {
				inizializzaPartita();
				inizializzaBottoniGioco();
				cambiaSchermata(Pannello.TipoPannello.GIOCO);
			});
		}
	}

	private void cambiaSchermata(Pannello.TipoPannello schermata) {
		vista.visualizzaPannello(schermata);
		vista.repaint();
	}

}
