package com.series.renamer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutFrame extends JDialog implements Constants {

	private static final long serialVersionUID = 1L;

	public AboutFrame() {
		super();
		init();
	}

	private void init() {
		this.setTitle("A propos de Series Renamer");
		this.setSize(620, 195);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setResizable(false);
		this.setIconImage(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/about.png")).getImage());
		this.setModal(true);
		JPanel panelImg = new JPanel();
		panelImg.setLayout(new BoxLayout(panelImg, BoxLayout.Y_AXIS));
		this.add(panelImg);
		JLabel iconLabel = new JLabel(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/seriesrenamer.png")));
		panelImg.add(new JLabel(" "));
		panelImg.add(iconLabel);
		panelImg.setAlignmentY(TOP_ALIGNMENT);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		panel.setAlignmentY(TOP_ALIGNMENT);

		panel.add(new JLabel("<html><h2>Series Renamer 1.0.0</h2>"
				+ "Series Renamer est un outil développé en Java par Mixouille se basant sur les APIs des sites :<br/></html>"));

		panel.add(new JHyperlinkLabel("Allocine.fr", "http://www.allocine.fr"));
		panel.add(new JHyperlinkLabel("Betaseries.com", "http://www.betaseries.com"));
		panel.add(new JHyperlinkLabel("Themoviedb.org", "http://www.themoviedb.org"));

		panel.add(new JLabel("<html><br/>Pour toute question, suggestion ou rapport de bug merci de me contacter à l'adresse suivante :</html>"));
		panel.add(new JHyperlinkLabel("mixouille@gmail.com", "mailto:mixouille@gmail.com"));

	}
}
