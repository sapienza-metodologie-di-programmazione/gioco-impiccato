package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GiocoImpiccato;
import model.PartitaImpiccato;
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
			Map.of(TipoTesto.TITOLO, new Font("Arial Bold", Font.PLAIN, 65), TipoTesto.BOTTONE,
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

		setBackground(Color.white);

	}

	private JLabel creaVistaParola(String parola) {
		return getGrafica().creaTitolo((carattereNascosto + " ").repeat(parola.length()).strip());
	}

	private void aggiornaVistaParola(String parola, Set<Character> lettereVisibili) {
		String vistaParola = parola.chars().mapToObj(i -> ((char) i)).map(c -> {
			if (!lettereVisibili.contains(c))
				return carattereNascosto.toString();
			else
				return c.toString().toUpperCase();
		}).reduce("", (s1, s2) -> s1 + " " + s2);
		vistaParola.strip();
		this.vistaParola.setText(vistaParola);
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

	public PannelloEsito getPannelloVittoria() {
		return pannelloVittoria;
	}

	public PannelloEsito getPannelloSconfitta() {
		return pannelloSconfitta;
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

	private void aggiornaBottoneLettera(String parola, Character c, JButton b) {
		if (parola.indexOf(c) != -1) {
			b.setBackground(GraficaPannello.VERDE_CHIARO);
		} else {
			b.setBackground(GraficaPannello.ROSSO_CHIARO);
		}
		b.setEnabled(false);
	}

	@Override
	public void update(Observable modello, Object arg) {
		GiocoImpiccato g = (GiocoImpiccato) modello;
		PartitaImpiccato p = g.getPartitaCorrente().get();
		Set<Character> lettereUsate = p.getLettereUsate();

		aggiornaVistaParola(p.getParola(), lettereUsate);

		if (p.getStato() != PartitaImpiccato.StatoPartita.IN_CORSO) {
			if (p.getStato().equals(PartitaImpiccato.StatoPartita.PERSA))
				mostraEsito(TipoEsito.SCONFITTA);
			else
				mostraEsito(TipoEsito.VITTORIA);
			return;
		}

		int vite = PartitaImpiccato.MAX_ERRORI - p.getErrori();
		aggiornaVite(vite);

		bottoniLettere.forEach((c, b) -> {
			if (lettereUsate.contains(c))
				aggiornaBottoneLettera(g.getPartitaCorrente().get().getParola(), c, b);
		});
	}

}
