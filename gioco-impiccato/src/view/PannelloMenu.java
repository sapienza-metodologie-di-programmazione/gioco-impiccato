package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.Grafica.TipoSfondo;
import view.Grafica.TipoTesto;

public class PannelloMenu extends Pannello {

	private JLabel titolo;
	private JLabel immagine;
	private JButton bottoneGioco;
	private JButton bottoneStatistiche;

	private static String nomeGioco = "<html>Gioco<br>dell'impiccato</html>";
	private static String pathImmagine = "assets/forest.png";
	private static String indicazioneGioco = "Gioca";
	private static String indicazioneStatistiche = "Statistiche";
	private static GridLayout layoutDefault = new GridLayout(1, 2, 30, 0);

	public static final Grafica GRAFICA_DEFAULT = new Grafica(
			Map.of(TipoSfondo.PANNELLO, Color.decode("#b2f2bb"), TipoSfondo.BOTTONE, Color.decode("#ffec99"),
					TipoTesto.TITOLO, Color.decode("#f08c00")),
			Map.of(TipoTesto.TITOLO, new Font("Stencil", Font.PLAIN, 60), TipoTesto.BOTTONE,
					new Font("Segoe Script", Font.PLAIN, 30), TipoTesto.NORMALE, new Font("Arial", Font.PLAIN, 40)));

	public PannelloMenu() {
		this(GRAFICA_DEFAULT);
	}

	public PannelloMenu(Grafica grafica) {

		super(layoutDefault, grafica);
		titolo = grafica.creaTitolo(nomeGioco);
		immagine = new JLabel(new ImageIcon(pathImmagine));
		bottoneGioco = grafica.creaBottone(indicazioneGioco);
		bottoneStatistiche = grafica.creaBottone(indicazioneStatistiche);

		inizializzaPannelloMenu();
	}

	private void inizializzaPannelloMenu() {
		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		add(immagine);
		add(creaPannelloPulsanti());
	}

	private JPanel creaPannelloPulsanti() {
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints disp = generaDisposizione(0, 0, 1, 2, GridBagConstraints.CENTER);
		p.add(titolo, disp);
		disp = generaDisposizione(0, 3, 1, 1, GridBagConstraints.SOUTH);
		p.add(bottoneGioco, disp);
		disp = generaDisposizione(0, 4, 1, 1, GridBagConstraints.NORTH);
		p.add(bottoneStatistiche, disp);
		p.setBackground(new Color(0, 0, 0, 0));
		return p;
	}

	private static GridBagConstraints generaDisposizione(int x, int y, int w, int h, int a) {
		return generaDisposizione(x, y, w, h, a, 1, 1);
	}

	private static GridBagConstraints generaDisposizione(int x, int y, int w, int h, int a, int wx, int wy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		c.anchor = a;
		c.weightx = wx;
		c.weighty = wy;
		return c;
	}

}
