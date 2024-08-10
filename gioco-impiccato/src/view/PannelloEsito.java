package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GiocoImpiccato;
import view.GraficaPannello.TipoSfondo;
import view.GraficaPannello.TipoTesto;

public class PannelloEsito extends Pannello {

	public enum TipoEsito {

		VITTORIA("assets/victory.png", GraficaPannello.VERDE_SCURO),
		SCONFITTA("assets/game-over.png", GraficaPannello.ROSSO);

		private final String pathImmagine;
		private final Color colore;

		private TipoEsito(String pathImmagine, Color colore) {
			this.pathImmagine = pathImmagine;
			this.colore = colore;
		}

		public String getPathImmagine() {
			return pathImmagine;
		}

		public Color getColore() {
			return colore;
		}

		public String getCommento(String parola, int partiteGiocate, int partiteVinte) {
			String stat = "Partite giocate: " + partiteGiocate + "<br>Partite Vinte: " + partiteVinte;
			return switch (this) {
			case VITTORIA -> "<html>Complimenti!<br>" + stat + "</html>";
			case SCONFITTA -> "<html>La parola era \"" + parola + "\"<br>" + stat + "</html>";
			};
		}

	}

	private JLabel immagine;
	private JLabel testo;
	private JButton bottoneNuovaParola;
	private TipoEsito esito;
	private static String indicazioneNuovaParola = "Nuova parola";
	private static LayoutManager layout = new GridBagLayout();

	public static final GraficaPannello GRAFICA_DEFAULT = new GraficaPannello(
			Map.of(TipoSfondo.PANNELLO, GraficaPannello.TRASPARENTE, TipoSfondo.BOTTONE, GraficaPannello.GIALLO,
					TipoTesto.TITOLO, GraficaPannello.VERDE, TipoTesto.NORMALE, GraficaPannello.VERDE),
			Map.of(TipoTesto.BOTTONE, new Font("Segoe Script", Font.PLAIN, 25), TipoTesto.NORMALE,
					new Font("Calibri Light", Font.PLAIN, 35)));

	public PannelloEsito(GiocoImpiccato modello, String parola, TipoEsito esito) {
		this(modello, GRAFICA_DEFAULT, parola, esito);
	}

	public PannelloEsito(GiocoImpiccato modello, GraficaPannello grafica, String parola, TipoEsito esito) {
		super(modello, layout, grafica);
		this.esito = esito;
		immagine = grafica.creaImmagine(esito.getPathImmagine());
		testo = grafica.creaTestoNormale(
				esito.getCommento(parola, getModello().getPartiteGiocate(), getModello().getPartiteVinte()),
				esito.getColore());
		bottoneNuovaParola = grafica.creaBottone(indicazioneNuovaParola);

		GridBagConstraints disp = GraficaPannello.generaDisposizione(0, 0, 1, 0, GridBagConstraints.WEST);
		add(immagine, disp);
		disp = GraficaPannello.generaDisposizione(0, 1, 1, 0, GridBagConstraints.CENTER);
		add(new JPanel(new BorderLayout()) {
			{
				add(new JPanel() {
					{
						add(testo);
					}
				}, BorderLayout.CENTER);
				add(new JPanel() {
					{
						add(bottoneNuovaParola);
					}
				}, BorderLayout.SOUTH);
			}
		}, disp);

	}
}
