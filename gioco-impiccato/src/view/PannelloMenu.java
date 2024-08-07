package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PannelloMenu extends Pannello {

	private JLabel titolo;
	private JLabel immagine;
	private JButton bottoneGioco;
	private JButton bottoneStatistiche;

	public static final String NOME_GIOCO = "Gioco dell'impiccato";
	public static final String PATH_IMMAGINE = "assets/forest.png";
	public static final String INDICAZIONE_GIOCO = "Gioca";
	public static final String INDICAZIONE_STATISTICHE = "Statistiche";
	public static final GridLayout GRIGLIA = new GridLayout(1, 2, 30, 0);

	public PannelloMenu() {

		super(GRIGLIA);
		titolo = new JLabel(NOME_GIOCO);
		immagine = new JLabel(new ImageIcon(PATH_IMMAGINE));
		bottoneGioco = new JButton(INDICAZIONE_GIOCO);
		bottoneStatistiche = new JButton(INDICAZIONE_STATISTICHE);

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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int density = 5;
		g.setColor(Color.decode("#b2f2bb"));
		for (int x = 0; x <= getWidth() + getHeight(); x += density) {
			g.drawLine(x, 0, 0, x);
		}
	}

//		GridBagConstraints disposizioni = new GridBagConstraints();
//		GridBagConstraints disposizioni2 = new GridBagConstraints();
//		disposizioni.weightx = 0;
//		disposizioni.weighty = 0;
//		disposizioni.anchor = GridBagConstraints.EAST;
//		disposizioni.gridx = 0;
//		disposizioni.gridy = 0;
//		disposizioni.gridwidth = 1;
//		disposizioni.gridheight = 3;
//
//		disposizioni2.weightx = 1;
//		disposizioni2.weighty = 0;
//		disposizioni2.anchor = GridBagConstraints.SOUTHEAST;
//		disposizioni2.gridx = 0;
//		disposizioni2.gridy = 0;
//		disposizioni2.gridwidth = 3;
//		disposizioni2.gridheight = 2;
//		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		add(new JLabel(new ImageIcon("assets/forest.png")), disposizioni);
//		add(new JLabel("Gioco"), disposizioni2);

}
