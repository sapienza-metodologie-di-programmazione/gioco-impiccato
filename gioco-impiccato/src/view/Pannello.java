package view;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Pannello extends JPanel {

	// usare factory pannelli?
	public enum TipoPannello {
		MENU, STATISTICHE, GIOCO, ESITO_PARTITA
	}

	public Pannello() {
	}

	public Pannello(LayoutManager layout) {
		super(layout);
	}

}
