package view;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GiocoImpiccato;

/**
 * Classe che rappresenta un pannello che mostra le statistiche (partite giocate
 * e vinte, ultima parola indovinata) per il Gioco dell'Impiccato
 */
public class PannelloStatistiche extends Pannello {

	private JLabel titolo;
	private JLabel statistiche;
	private JButton bottoneMenu;

	private static String nomeSchermata = "Statistiche";
	private static String indicazioneMenu = "Menù";
	private static String indicazionePartiteGiocate = "Partite giocate: ";
	private static String indicazionePartiteVinte = "Parole indovinate: ";
	private static String indicazioneUltimaParolaIndovinata = "Ultima parola indovinata: ";
	private static LayoutManager layout = new BorderLayout(50, 50);

	public PannelloStatistiche() {
		this(GRAFICA_DEFAULT);

	}

	public PannelloStatistiche(GraficaPannello grafica) {

		super(layout, grafica);
		titolo = grafica.creaTitolo(nomeSchermata);
		statistiche = grafica.creaTestoNormale(generaStatistiche(0, 0, ""));
		bottoneMenu = grafica.creaBottone(indicazioneMenu);

		inizializzaPannelloStatistiche();
	}

	private void inizializzaPannelloStatistiche() {
		setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		add(new JPanel() {
			{
				add(titolo);
			}
		}, BorderLayout.NORTH);
		add(new JPanel() {
			{
				add(statistiche);
			}
		}, BorderLayout.CENTER);
		add(new JPanel(new BorderLayout()) {
			{
				add(new JPanel() {
					{
						add(bottoneMenu);
					}
				}, BorderLayout.EAST);
			}
		}, BorderLayout.SOUTH);
	}

	public JLabel getTitolo() {
		return titolo;
	}

	public JLabel getStatistiche() {
		return statistiche;
	}

	public JButton getBottoneMenu() {
		return bottoneMenu;
	}

	/**
	 * Metodo che produce una stringa che descrive le statistiche di gioco
	 * 
	 * @param partite giocate
	 * @param partite vinte
	 * @param ultima  parola indovinata
	 * @return descrizione delle statistiche
	 */
	public static String generaStatistiche(int partiteGiocate, int partiteVinte, String ultimaParolaIndovinata) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>" + indicazionePartiteGiocate + partiteGiocate + "<br>");
		sb.append(indicazionePartiteVinte + partiteVinte + "<br>");
		sb.append(indicazioneUltimaParolaIndovinata + "\"" + ultimaParolaIndovinata.toUpperCase() + "\"");
		return sb.toString();
	}

	@Override
	public void update(Observable modello, Object arg) {
		GiocoImpiccato g = (GiocoImpiccato) modello;
		/*
		 * i dati sulle partite precedenti vengono aggiornati prendendo la versione più
		 * recente dal modello
		 */
		statistiche.setText(generaStatistiche(g.getPartiteGiocate(), g.getPartiteVinte(),
				g.getUltimaParolaIndovinata().orElse("")));
	}
}
