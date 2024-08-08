package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FinestraGiocoImpiccato extends JFrame {

	public static final String TITOLO = "Gioco dell'impiccato";
	public static final String PATH_ICONA = "assets/hangman-game-icon.png";
	public static final int[] DIMENSIONI = new int[] { 1200, 800 };

	private JPanel pannelloGenerale;
	private PannelloMenu pannelloMenu;
	private PannelloStatistiche pannelloStatistiche;
	private PannelloGioco pannelloGioco;

	public FinestraGiocoImpiccato() {

		super(TITOLO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File(PATH_ICONA)));
		} catch (IOException e) {
		}

		PannelloMenu menu = new PannelloMenu();
		add(menu);
		pack();
		// setSize(DIMENSIONI[0], DIMENSIONI[1]);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		FinestraGiocoImpiccato f = new FinestraGiocoImpiccato();
	}

}
