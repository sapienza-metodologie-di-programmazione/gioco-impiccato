package view;

import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Classe che rappresenta la finestra di gioco per il Gioco dell'Impiccato;
 * contiene i pannelli per menù, statistiche e svolgimento partita
 */
public class FinestraGiocoImpiccato extends JFrame {

	static {
		// di default lo sfondo di un pannello è trasparente
		UIManager.put("Panel.background", GraficaPannello.TRASPARENTE);
	}

	private static String titolo = "Gioco dell'impiccato"; // nome della finestra
	private static String pathIcona = "assets/hangman-game-icon.png"; // path dell'icona della finestra

	private JPanel pannelloGenerale;
	private PannelloMenu pannelloMenu;
	private PannelloStatistiche pannelloStatistiche;
	private Optional<PannelloGioco> pannelloGioco;

	public FinestraGiocoImpiccato() {

		super(titolo);

		pannelloMenu = new PannelloMenu();
		pannelloStatistiche = new PannelloStatistiche();
		pannelloGioco = Optional.ofNullable(null);
		/*
		 * il Card Layout del pannello generale dà la possibilità di scegliere quale
		 * pannello visualizzare tra quelli al suo interno
		 */
		pannelloGenerale = new JPanel(new CardLayout()) {
			{
				add(pannelloMenu, Pannello.TipoPannello.MENU.name());
				add(pannelloStatistiche, Pannello.TipoPannello.STATISTICHE.name());
				// il pannello gioco viene aggiunto alla creazione di una partita
			}
		};

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File(pathIcona)));
		} catch (IOException e) {
			// viene usata l'icona di default della finestra
		}

		add(pannelloGenerale);

		/*
		 * le dimensioni della finestra sono settate al minimo necessario per mostrare
		 * interamente gli elementi da essa contenuti
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JPanel getPannelloGenerale() {
		return pannelloGenerale;
	}

	public PannelloMenu getPannelloMenu() {
		return pannelloMenu;
	}

	public PannelloStatistiche getPannelloStatistiche() {
		return pannelloStatistiche;
	}

	public Optional<PannelloGioco> getPannelloGioco() {
		return pannelloGioco;
	}

	/**
	 * Metodo per inizializzare un nuovo pannello gioco (un eventuale pannello
	 * pre-esistente viene sovrascritto) e aggiungerlo al pannello generale della
	 * finestra
	 * 
	 * @param parola da indovinare nel nuovo pannello gioco
	 */
	public void setPannelloGioco(String parola) {
		if (pannelloGioco.isPresent())
			pannelloGenerale.remove(pannelloGioco.get());
		pannelloGioco = Optional.of(new PannelloGioco(parola));
		pannelloGenerale.add(pannelloGioco.get(), Pannello.TipoPannello.GIOCO.name());
	}

	/**
	 * Metodo per visualizzare un determinato pannello contenuto nel pannello
	 * generale (menù, statistiche o gioco)
	 * 
	 * @param tipo del pannello dal visualizzare
	 */
	public void visualizzaPannello(Pannello.TipoPannello tipoPannello) {
		((CardLayout) pannelloGenerale.getLayout()).show(pannelloGenerale, tipoPannello.name());
	}

}
