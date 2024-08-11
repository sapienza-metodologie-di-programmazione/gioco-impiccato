package view;

import java.io.File;
import java.io.IOException;

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
		UIManager.put("Panel.background", GraficaPannello.TRASPARENTE);
	}

	private static String titolo = "Gioco dell'impiccato"; // nome della finestra
	private static String pathIcona = "assets/hangman-game-icon.png"; // icona della finestra

	private JPanel pannelloGenerale;
	private PannelloMenu pannelloMenu;
	private PannelloStatistiche pannelloStatistiche;
	private PannelloGioco pannelloGioco;

	public FinestraGiocoImpiccato() {

		super(titolo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File(pathIcona)));
		} catch (IOException e) {
		}

		add(new PannelloGioco("ciao"));
		// add(new PannelloStatistiche());
		// add(new PannelloMenu());
		pack();
		// setSize(dimensioni[0], dimensioni[1]);
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

	public PannelloGioco getPannelloGioco() {
		return pannelloGioco;
	}

	public static void main(String[] args) {
		FinestraGiocoImpiccato f = new FinestraGiocoImpiccato();
	}

}
