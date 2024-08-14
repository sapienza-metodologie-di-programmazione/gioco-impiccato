package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.GraficaPannello.TipoSfondo;
import view.GraficaPannello.TipoTesto;
import view.PannelloEsito.TipoEsito;

/**
 * Classe che rappresenta un pannello in cui si svolge una partita del Gioco
 * dell'Impiccato
 */
public class PannelloGioco extends Pannello {

	private JLabel immagineVite;
	private JButton bottoneMenu;
	private JLabel vistaParola;
	private Map<Character, JButton> bottoniLettere;
	private JPanel pannelloSuperiore;
	private JPanel pannelloBottoni;
	private PannelloEsito pannelloVittoria;
	private PannelloEsito pannelloSconfitta;

	private static Character carattereNascosto = '_';
	private static String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	private static String indicazioneMenu = "Torna al menù";
	private static String pathImmagineVite = "assets/5-lives.png";
	private static Map<Integer, String> pathImmaginiVite = Map.of(1, "assets/1-life.png", 2, "assets/2-lives.png", 3,
			"assets/3-lives.png", 4, "assets/4-lives.png", 5, "assets/5-lives.png");

	private static LayoutManager layout = new GridLayout(2, 1, 20, 20);

	public static final GraficaPannello GRAFICA_DEFAULT = new GraficaPannello(
			Map.of(TipoSfondo.PANNELLO, GraficaPannello.GIALLO, TipoSfondo.BOTTONE, GraficaPannello.AZZURRO,
					TipoTesto.TITOLO, GraficaPannello.ARANCIONE),
			Map.of(TipoTesto.TITOLO, new Font("Stencil", Font.PLAIN, 65), TipoTesto.BOTTONE,
					new Font("Arial", Font.PLAIN, 30), TipoTesto.NORMALE, new Font("Calibri Light", Font.PLAIN, 40)));

	public PannelloGioco(String parola) {
		this(GRAFICA_DEFAULT, parola);
	}

	public PannelloGioco(GraficaPannello grafica, String parola) {
		super(layout, grafica);
		immagineVite = grafica.creaImmagine(pathImmagineVite);
		bottoneMenu = grafica.creaBottone(indicazioneMenu, GraficaPannello.GIALLO);
		bottoneMenu.setFont(new Font("Segoe Script", Font.PLAIN, 25));

		vistaParola = creaVistaParola(parola);
		bottoniLettere = creaBottoniLettere();
		pannelloSuperiore = creaPannelloSuperiore();
		pannelloBottoni = creaPannelloBottoni();
		pannelloVittoria = new PannelloEsito(TipoEsito.VITTORIA);
		pannelloSconfitta = new PannelloEsito(TipoEsito.SCONFITTA);

		setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		add(pannelloSuperiore);
		add(pannelloBottoni);

	}

	private JLabel creaVistaParola(String parola) {
		return getGrafica().creaTitolo((carattereNascosto + " ").repeat(parola.length()).strip());
	}

	private Map<Character, JButton> creaBottoniLettere() {
		return (Map<Character, JButton>) alfabeto.chars().mapToObj(i -> (char) i).collect(
				Collectors.toMap(x -> (Character) x, x -> getGrafica().creaBottone(x.toString().toUpperCase())));
	}

	private JPanel creaPannelloSuperiore() {
		JPanel p = new JPanel(new GridLayout(2, 1, 20, 20));
		p.add(new JPanel(new GridLayout(1, 2, 300, 0)) {
			{
				add(immagineVite);
				add(bottoneMenu);

			}
		});
		p.add(vistaParola);
		return p;
	}

	private JPanel creaPannelloBottoni() {
		JPanel p = new JPanel(new GridLayout(4, 7, 10, 10));
		bottoniLettere.values().forEach(b -> {
			p.add(b);
			if (b.getText().equals("U")) {
				JButton spazio = new JButton();
				spazio.setVisible(false);
				p.add(spazio);
			}
		});
		return p;
	}

	public JButton getBottoneMenu() {
		return bottoneMenu;
	}

	public Map<Character, JButton> getBottoniLettere() {
		return bottoniLettere;
	}

	private void aggiornaVite(int numeroVite) {
		getGrafica().sostituisciImmagine(immagineVite, pathImmaginiVite.get(numeroVite));
		repaint();
	}

	public void mostraEsito(PannelloEsito.TipoEsito esito) {
		remove(pannelloBottoni);
		switch (esito) {
		case VITTORIA -> add(pannelloVittoria);
		case SCONFITTA -> add(pannelloSconfitta);
		}

	}

	@Override
	public void update(Observable modello, Object arg) {

	}

}
