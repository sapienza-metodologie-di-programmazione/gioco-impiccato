package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe che rappresenta un pannello con il menù del Gioco dell'Impiccato
 */
public class PannelloMenu extends Pannello {

	private JLabel titolo;
	private JLabel immagine;
	private JButton bottoneGioco;
	private JButton bottoneStatistiche;

	private static String nomeGioco = "<html>Gioco<br>dell'impiccato</html>";
	private static String pathImmagine = "assets/forest.png";
	private static String indicazioneGioco = "Gioca";
	private static String indicazioneStatistiche = "Statistiche";
	private static LayoutManager layout = new GridLayout(1, 2, 40, 0);

	public PannelloMenu() {
		this(GRAFICA_DEFAULT);
	}

	public PannelloMenu(GraficaPannello grafica) {

		super(layout, grafica);
		titolo = grafica.creaTitolo(nomeGioco);
		immagine = grafica.creaImmagine(pathImmagine);
		bottoneGioco = grafica.creaBottone(indicazioneGioco);
		bottoneStatistiche = grafica.creaBottone(indicazioneStatistiche);

		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		add(immagine);
		add(creaPannelloPulsanti());
	}

	private JPanel creaPannelloPulsanti() {
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints disp = GraficaPannello.generaDisposizione(0, 3, 1, 1, GridBagConstraints.SOUTH);
		p.add(titolo, disp);
		disp = GraficaPannello.generaDisposizione(0, 4, 1, 1, GridBagConstraints.CENTER);
		p.add(bottoneGioco, disp);
		disp = GraficaPannello.generaDisposizione(0, 5, 1, 1, GridBagConstraints.NORTH);
		p.add(bottoneStatistiche, disp);
		return p;
	}

	public JLabel getTitolo() {
		return titolo;
	}

	public JLabel getImmagine() {
		return immagine;
	}

	public JButton getBottoneGioco() {
		return bottoneGioco;
	}

	public JButton getBottoneStatistiche() {
		return bottoneStatistiche;
	}

	@Override
	public void update(Observable modello, Object arg) {
		// il menù non ha dati da aggiornare
	}

}
