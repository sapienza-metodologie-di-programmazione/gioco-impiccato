package view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class FinestraGiocoImpiccato extends JFrame {

	static {
		UIManager.put("Label.font", getFont("Nunito-Regular.ttf"));
		UIManager.put("Button.font", new Font(Font.DIALOG, Font.PLAIN, 21));
	}

	private static Map<String, Font> fonts = new HashMap<>();

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

	public static Font getFont(String name) {
		Font font = null;
		if (name == null) {
			return new Font(Font.SERIF, Font.PLAIN, 14);
		}

		try {
			// load from a cache map, if exists
			if (fonts != null && (font = fonts.get(name)) != null) {
				return font;
			}
			String fName = "assets/font/" + name;
			File fontFile = new File(fName);
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(font);

			fonts.put(name, font);
		} catch (Exception ex) {
			System.out.println("Not loaded");
		}
		return font;
	}

	public static void main(String[] args) {
		FinestraGiocoImpiccato f = new FinestraGiocoImpiccato();
	}

}
