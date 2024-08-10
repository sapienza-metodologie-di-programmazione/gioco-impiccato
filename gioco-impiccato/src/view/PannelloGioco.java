package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GiocoImpiccato;
import view.GraficaPannello.TipoSfondo;
import view.GraficaPannello.TipoTesto;

public class PannelloGioco extends Pannello {

	private JLabel immagineVite;
	private JButton bottoneMenu;
	private JLabel parola;
	private Map<Character, JButton> bottoniLettere;
	private JPanel pannelloSuperiore;
	private JPanel pannelloBottoni;

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

	public PannelloGioco(GiocoImpiccato modello, String parola) {
		this(modello, GRAFICA_DEFAULT, parola);
	}

	public PannelloGioco(GiocoImpiccato modello, GraficaPannello grafica, String parola) {
		super(modello, layout, grafica);
		immagineVite = grafica.creaImmagine(pathImmagineVite);
		bottoneMenu = grafica.creaBottone(indicazioneMenu, GraficaPannello.GIALLO);
		bottoneMenu.setFont(new Font("Segoe Script", Font.PLAIN, 25));

		this.parola = grafica.creaTitolo((carattereNascosto + " ").repeat(parola.length()).strip());
		bottoniLettere = (Map<Character, JButton>) alfabeto.chars().mapToObj(i -> (char) i)
				.collect(Collectors.toMap(x -> (Character) x, x -> grafica.creaBottone(x.toString().toUpperCase())));

		pannelloSuperiore = creaPannelloSuperiore();
		pannelloBottoni = creaPannelloBottoni();

		setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));
		add(pannelloSuperiore);
		add(pannelloBottoni);
		mostraEsito(PannelloEsito.TipoEsito.SCONFITTA);
	}

	private JPanel creaPannelloSuperiore() {
		JPanel p = new JPanel(new GridLayout(2, 1, 20, 20));
		p.add(new JPanel(new GridLayout(1, 2, 300, 0)) {
			{
				add(immagineVite);
				add(bottoneMenu);

			}
		});
		p.add(parola);
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

	public void mostraEsito(PannelloEsito.TipoEsito esito) {
		remove(pannelloBottoni);
		add(new PannelloEsito(getModello(), parola.getText(), esito));
	}

}
