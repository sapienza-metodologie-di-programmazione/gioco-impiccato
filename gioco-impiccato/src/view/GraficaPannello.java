package view;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GraficaPannello {

	public static final Color TRASPARENTE = new Color(0, 0, 0, 0);
	public static final Color ARANCIONE = Color.decode("#f08c00");
	public static final Color GIALLO = Color.decode("#ffec99");
	public static final Color VERDE = Color.decode("#b2f2bb");
	public static final Color AZZURRO = Color.decode("#a5d8ff");
	public static final Color BLU = Color.decode("#1971c2");

	public static final Font CORSIVO = new Font("Segoe Script", Font.PLAIN, 30);

	public enum TipoTesto implements Colorabile {
		TITOLO, NORMALE, BOTTONE
	}

	public enum TipoSfondo implements Colorabile {
		PANNELLO, BOTTONE
	}

	private Map<Colorabile, Color> colori = new HashMap<>();
	private Map<TipoTesto, Font> fonts = new HashMap<>();

	public GraficaPannello() {
	}

	public GraficaPannello(Map<Colorabile, Color> colori, Map<TipoTesto, Font> fonts) {
		this.colori = colori;
		this.fonts = fonts;
	}

	public Map<Colorabile, Color> getColori() {
		return colori;
	}

	public Map<TipoTesto, Font> getFonts() {
		return fonts;
	}

	public void setColore(Colorabile componente, Color colore) {
		colori.put(componente, colore);
	}

	public void setFont(TipoTesto componente, Font font) {
		fonts.put(componente, font);
	}

	public JLabel creaTitolo(String testo) {
		return creaTesto(TipoTesto.TITOLO, testo);
	}

	public JLabel creaTestoNormale(String testo) {
		return creaTesto(TipoTesto.NORMALE, testo);
	}

	private JLabel creaTesto(TipoTesto t, String contenuto) {
		JLabel l = new JLabel(contenuto);
		if (colori.containsKey(t))
			l.setForeground(colori.get(t));
		if (fonts.containsKey(t))
			l.setFont(fonts.get(t));
		return l;
	}

	public JButton creaBottone(String testo) {
		JButton b = new JButton(testo);
		if (colori.containsKey(TipoSfondo.BOTTONE))
			return creaBottone(testo, colori.get(TipoSfondo.BOTTONE));
		return creaBottone(testo, TRASPARENTE);
	}

	public JButton creaBottone(String testo, Color coloreSfondo) {
		JButton b = new JButton(testo);
		if (colori.containsKey(TipoTesto.BOTTONE))
			b.setForeground(colori.get(TipoTesto.BOTTONE));
		if (fonts.containsKey(TipoTesto.BOTTONE))
			b.setFont(fonts.get(TipoTesto.BOTTONE));

		b.setBackground(coloreSfondo);

		return b;
	}

	public JLabel creaImmagine(String path) {
		return new JLabel(new ImageIcon(path));
	}

}
