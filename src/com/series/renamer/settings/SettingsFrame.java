package com.series.renamer.settings;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.series.renamer.Constants;
import com.series.renamer.SeriesRenamer;
import com.series.renamer.bean.Episode;
import com.series.renamer.bean.File;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;
import com.series.renamer.bean.Settings;
import com.series.renamer.utils.SpringUtilities;

public class SettingsFrame extends JDialog implements Constants, ActionListener {

	private static final long serialVersionUID = 1L;
	private SeriesRenamer seriesRenamer;
	private Settings settings = null;

	private JButton saveButton = new JButton("Enregistrer", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/ok.png")));
	private JButton cancelButton = new JButton("Annuler", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/cancel.png")));

	private JTextField patternNameField1 = new JTextField(15);
	private JTextField patternField1 = new JTextField(30);
	private JTextField patternNameField2 = new JTextField(15);
	private JTextField patternField2 = new JTextField(30);

	private JButton preview1Btn;
	private JButton preview2Btn;

	private JLabel preview = new JLabel();

	private JPanel formPanel;

	private JCheckBox replaceSpacesCB = new JCheckBox();
	private JCheckBox extractSubFoldersCB = new JCheckBox();

	private Serie serie = new Serie();
	private Season season = new Season();
	private Episode episode = new Episode();
	private File dummyFile = new File(null);

	private static final String HELP_TXT = "<html>" + "Mots-clés pour la définition des patterns :" + "<ul>"
			+ "<li>%svf% : Titre de la série en VF (uniquement Allocinbe)" + "<li>%svo% : Titre de la série en VO" + "<li>%sai% : N° de la saison"
			+ "<li>%esa% : N° dépisode dans la saison" + "<li>%ese% : N° global de l'épisode dans la série (non disponible sur TMDB)"
			+ "<li>%evf% : Titre de l'épisode en VF (uniquement Allocinbe)" + "<li>%evo% : Titre de l'épisode en VO" + "</ul>"
			+ "<u>Note</u> : Selon le site fournisseur choisi certaines données peuvent ne pas être disponibles.<br>" + "</html>";

	public SettingsFrame(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
		this.settings = seriesRenamer.settings;

		// Create test objects
		serie.setTitle("Les Simpson");
		serie.setOriginalTitle("The Simpsons");
		serie.setCode("1234");
		serie.setRating(4.2f);

		season.setCode("2345");
		season.setOrder(5);
		season.setRating(4.1f);

		episode.setCode("3456");
		episode.setDate("12-05-1994");
		episode.setOriginalTitle("Homer Goes to College");
		episode.setTitle("Homer va à la fac");
		episode.setSeasonOrder(3);
		episode.setSerieOrder(84);
		episode.setSynopsis("Synopsis ...");

		init();
	}

	private void init() {
		this.setTitle("Réglages");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setResizable(false);
		this.setIconImage(new ImageIcon(ICON_URL).getImage());
		this.setModal(true);

		this.formPanel = new JPanel(new SpringLayout());
		this.add(formPanel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		this.add(buttonsPanel);

		// FIELDS

		// Pattern 1
		JLabel lblPatternName1 = new JLabel("Nom pattern 1 : ", JLabel.TRAILING);
		formPanel.add(lblPatternName1);
		formPanel.add(patternNameField1);
		lblPatternName1.setLabelFor(patternNameField1);

		JPanel pattern1Panel = new JPanel();
		BoxLayout boxLayout1 = new BoxLayout(pattern1Panel, BoxLayout.X_AXIS);
		pattern1Panel.setLayout(boxLayout1);
		JLabel lblPattern1 = new JLabel("Pattern 1 : ", JLabel.TRAILING);
		formPanel.add(lblPattern1);
		formPanel.add(pattern1Panel);
		pattern1Panel.add(patternField1);
		patternField1.setToolTipText(HELP_TXT);
		preview1Btn = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/preview.png")));
		preview1Btn.setToolTipText("Tester le pattern avec un exemple");
		preview1Btn.addActionListener(this);
		pattern1Panel.add(preview1Btn);
		lblPattern1.setLabelFor(patternField1);

		// Pattern 2
		JLabel lblPatternName2 = new JLabel("Nom pattern 2 : ", JLabel.TRAILING);
		formPanel.add(lblPatternName2);
		formPanel.add(patternNameField2);
		lblPatternName2.setLabelFor(patternNameField2);

		JPanel pattern2Panel = new JPanel();
		BoxLayout boxLayout2 = new BoxLayout(pattern2Panel, BoxLayout.X_AXIS);
		pattern2Panel.setLayout(boxLayout2);
		JLabel lblPattern2 = new JLabel("Pattern 2 : ", JLabel.TRAILING);
		formPanel.add(lblPattern2);
		formPanel.add(pattern2Panel);
		pattern2Panel.add(patternField2);
		patternField2.setToolTipText(HELP_TXT);

		preview2Btn = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/preview.png")));
		preview2Btn.setToolTipText("Tester le pattern avec un exemple");
		preview2Btn.addActionListener(this);
		pattern2Panel.add(preview2Btn);
		lblPattern2.setLabelFor(patternField2);

		// Replace dots
		JLabel lblReplace = new JLabel("Remplacer les espaces par des points : ", JLabel.TRAILING);
		formPanel.add(lblReplace);
		formPanel.add(replaceSpacesCB);
		lblReplace.setLabelFor(replaceSpacesCB);

		// Extract subfolders
		JLabel lblExtract = new JLabel("Extraire des sous-répertoires : ", JLabel.TRAILING);
		extractSubFoldersCB.setToolTipText("Remonter les fichiers vidéos contenus dans les sous-répertoires au niveau du répertoire racine");
		formPanel.add(lblExtract);
		formPanel.add(extractSubFoldersCB);
		lblExtract.setLabelFor(extractSubFoldersCB);

		// Preview
		JLabel previewLbl = new JLabel("Test pattern : ", JLabel.TRAILING);
		formPanel.add(previewLbl);
		formPanel.add(preview);

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(formPanel, 7, 2, // rows, cols
				10, 10, // initX, initY
				10, 10); // xPad, yPad

		// BUTTONS
		buttonsPanel.add(saveButton);
		saveButton.setFocusPainted(true);
		saveButton.addActionListener(this);

		buttonsPanel.add(cancelButton);
		cancelButton.addActionListener(this);

		JLabel emptyLbl = new JLabel(" ");
		emptyLbl.setFont(new Font("Arial", Font.PLAIN, 5));
		this.add(emptyLbl); // For space

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Fill fields (move loading to app loading)
		fillFields(settings);

		this.pack();
	}

	@Override
	public synchronized void removeWindowListener(WindowListener paramWindowListener) {
		fillFields(Settings.load());
		super.removeWindowListener(paramWindowListener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			settings = fillSettings(settings);
			settings.save();
			seriesRenamer.pattern1Lbl.setText("<html><u>" + settings.getPatternName1() + "</u></html>");
			seriesRenamer.pattern2Lbl.setText("<html><u>" + settings.getPatternName2() + "</u></html>");
			this.setVisible(false);
			fillFields(Settings.load());
		} else if (e.getSource() == cancelButton) {
			this.setVisible(false);
			fillFields(Settings.load());
		} else if (e.getSource() == preview1Btn) {
			preview(patternField1.getText());
		} else if (e.getSource() == preview2Btn) {
			preview(patternField2.getText());
		}
	}

	@Override
	public void setVisible(boolean paramBoolean) {
		preview.setText("");
		super.setVisible(paramBoolean);
	}

	private void preview(String pattern) {
		seriesRenamer.renameFile(pattern, serie, season, episode, dummyFile, 22, false);
		String text = dummyFile.getDisplayName();
		if (replaceSpacesCB.isSelected()) {
			text = text.replaceAll(" ", ".");
		}
		preview.setText(text + ".avi");
	}

	private void fillFields(Settings settings) {
		patternNameField1.setText(settings.getPatternName1());
		patternField1.setText(settings.getPattern1());
		patternNameField2.setText(settings.getPatternName2());
		patternField2.setText(settings.getPattern2());
		replaceSpacesCB.setSelected(settings.isReplaceSpaces());
		extractSubFoldersCB.setSelected(settings.isExtractSubFolders());
	}

	private Settings fillSettings(Settings settings) {
		if (settings == null)
			settings = new Settings();

		settings.setPatternName1(patternNameField1.getText());
		settings.setPattern1(patternField1.getText());
		settings.setPatternName2(patternNameField2.getText());
		settings.setPattern2(patternField2.getText());
		settings.setReplaceSpaces(replaceSpacesCB.isSelected());
		settings.setExtractSubFolders(extractSubFoldersCB.isSelected());

		return settings;
	}
}
