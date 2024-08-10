package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.GiocoImpiccato;

public class FinestraGiocoImpiccato extends JFrame {

	static {
		UIManager.put("Panel.background", GraficaPannello.TRASPARENTE);
	}

	private static String titolo = "Gioco dell'impiccato";
	private static String path_icona = "assets/hangman-game-icon.png";
	private static int[] dimensioni = new int[] { 1200, 800 };

	private JPanel pannelloGenerale;
	private PannelloMenu pannelloMenu;
	private PannelloStatistiche pannelloStatistiche;
	private PannelloGioco pannelloGioco;

	public FinestraGiocoImpiccato() {

		super(titolo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File(path_icona)));
		} catch (IOException e) {
		}

		add(new PannelloGioco(new GiocoImpiccato(), "ciao"));
		// add(new PannelloStatistiche());
		// add(new PannelloMenu());
		pack();
		// setSize(DIMENSIONI[0], DIMENSIONI[1]);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		FinestraGiocoImpiccato f = new FinestraGiocoImpiccato();
	}

}
