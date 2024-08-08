package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Pannello extends JPanel {

	// usare factory pannelli?
	public enum TipoPannello {
		MENU, STATISTICHE, GIOCO, ESITO_PARTITA
	}

	private Grafica grafica = new Grafica();
	private static Color coloreSfondoDefault = Color.WHITE;

	public Pannello() {
	}

	public Pannello(LayoutManager layout) {
		super(layout);
	}

	public Pannello(Grafica grafica) {
		this.grafica = grafica;
	}

	public Pannello(LayoutManager layout, Grafica grafica) {
		super(layout);
		this.grafica = grafica;
	}

	public Grafica getGrafica() {
		return grafica;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int density = 5;
		g.setColor(grafica.getColori().getOrDefault(Grafica.TipoSfondo.PANNELLO, coloreSfondoDefault));
		for (int x = 0; x <= getWidth() + getHeight(); x += density) {
			g.drawLine(x, 0, 0, x);
		}
	}
}
