package controller;

import java.util.List;
import java.util.Optional;

import model.GiocoImpiccato;
import view.FinestraGiocoImpiccato;
import view.Pannello;
import view.PannelloEsito;
import view.PannelloGioco;

/**
 * Classe incaricata della gestione delle interazioni tra modello e vista del
 * Gioco dell'Impiccato; è responsabile delle azioni eseguite al clic dei
 * bottoni
 */
public class ControlloGiocoImpiccato {

	private GiocoImpiccato modello;
	private FinestraGiocoImpiccato vista;

	public ControlloGiocoImpiccato(GiocoImpiccato modello, FinestraGiocoImpiccato vista) {
		this.modello = modello;
		this.vista = vista;
		/*
		 * il pannello statistiche deve osservare il modello del gioco per avere
		 * conteggi aggiornati di partite vinte e giocate
		 */
		modello.addObserver(vista.getPannelloStatistiche());
		/*
		 * anche il pannello della partita di gioco deve osservare il modello per
		 * gestire la vista della parola da indovinare, le vite e la scopertura delle
		 * lettere, tuttavia il pannello viene creato successivamente, al clic del
		 * bottone "Gioca" dal menù o "Nuova parola" da una partita terminata
		 */
		inizializzaAzioniBottoni();
	}

	private void inizializzaAzioniBottoni() {
		inizializzaBottoniMenu();
		inizializzaBottoniStatistiche();
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

	private void inizializzaPartita() {

		// una eventuale partita già in corso viene sovrascritta
		Optional<PannelloGioco> vecchio = vista.getPannelloGioco();
		if (vecchio.isPresent()) {
			modello.deleteObserver(vecchio.get());
			modello.deleteObserver(vecchio.get().getPannelloVittoria());
			modello.deleteObserver(vecchio.get().getPannelloSconfitta());
		}

		modello.iniziaPartita();
		vista.setPannelloGioco(modello.getPartitaCorrente().get().getParola());

		/*
		 * il pannello della partita e i pannelli per mostrare il suo esito (vittoria o
		 * sconfitta) sono registrati come observer del modello del gioco (i pannelli
		 * esito riportano le statistiche delle partite precedenti)
		 */
		modello.addObserver(vista.getPannelloGioco().get());
		modello.addObserver(vista.getPannelloGioco().get().getPannelloVittoria());
		modello.addObserver(vista.getPannelloGioco().get().getPannelloSconfitta());
	}

	private void inizializzaBottoniGioco() {

		/*
		 * qui non ci si preoccupa di gestire eventuali errori di Optional vuoto perché
		 * il metodo è privato e chiamato solo dopo l'inizializzazione di una partita (e
		 * la creazione di un pannello gioco)
		 */
		PannelloGioco p = vista.getPannelloGioco().get();

		p.getBottoneMenu().addActionListener(e -> cambiaSchermata(Pannello.TipoPannello.MENU));

		/*
		 * al clic di un bottone con una lettera, essa viene aggiunta alle lettere usate
		 * nel modello
		 */
		p.getBottoniLettere().forEach(
				(c, b) -> b.addActionListener(e -> modello.getPartitaCorrente().get().aggiungiLetteraUsata(c)));

		/*
		 * al clic del bottone "Nuova parola" in un pannello esito (vittoria /
		 * sconfitta) si crea una nuova partita e un nuovo pannello gioco
		 */
		for (PannelloEsito x : List.of(p.getPannelloVittoria(), p.getPannelloSconfitta())) {
			x.getBottoneNuovaParola().addActionListener(e -> {
				inizializzaPartita();
				inizializzaBottoniGioco();
				cambiaSchermata(Pannello.TipoPannello.GIOCO);
			});
		}
	}

	/**
	 * Metodo per visualizzare una determinata schermata del Gioco dell'Impiccato
	 * 
	 * @param tipo del pannello da visualizzare
	 */
	public void cambiaSchermata(Pannello.TipoPannello schermata) {
		vista.visualizzaPannello(schermata);
		vista.repaint();
	}

}
