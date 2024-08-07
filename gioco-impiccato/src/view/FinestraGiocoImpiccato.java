package view;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class FinestraGiocoImpiccato extends JFrame {

	static {
		UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 40));
		UIManager.put("Button.font", new Font("Segoe Script", Font.PLAIN, 30));

	}

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

		add(new PannelloMenu());
		pack();
		// setSize(DIMENSIONI[0], DIMENSIONI[1]);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		FinestraGiocoImpiccato f = new FinestraGiocoImpiccato();
	}

}
