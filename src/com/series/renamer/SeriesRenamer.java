package com.series.renamer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import com.series.renamer.allocine.AllocineSearchUtils;
import com.series.renamer.bean.Episode;
import com.series.renamer.bean.File;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;
import com.series.renamer.bean.Settings;
import com.series.renamer.betaseries.BetaSeriesSearchUtils;
import com.series.renamer.listener.FileListener;
import com.series.renamer.listener.SearchSeriesListener;
import com.series.renamer.listener.SelectSeasonListener;
import com.series.renamer.listener.SelectSeriesListener;
import com.series.renamer.listener.SettingsListener;
import com.series.renamer.settings.SettingsFrame;
import com.series.renamer.tmdb.TMDBSearchUtils;
import com.series.renamer.utils.FileDrop;
import com.series.renamer.utils.FileNameCleaner;
import com.series.renamer.utils.Utils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SeriesRenamer extends JFrame implements Constants {

	private static final long serialVersionUID = 1L;

	public java.io.File fileCurrentDir = null;

	public ISearchUtils searchUtils;

	private JPanel topPanel;
	private JPanel centerPanel;

	public JTextField searchField = new JTextField(30);

	public JComboBox<ImageIcon[]> providersCB;
	public ImageIcon[] providerImages;

	public SeriesList seriesList = new SeriesList(new DefaultListModel());
	public JList seasonList = new JList(new DefaultListModel());
	public JList episodeList = new JList(new DefaultListModel());
	public JList fileList = new JList(new DefaultListModel());
	private JScrollPane episodeListScroller;
	public JScrollPane fileListScroller;

	public JButton settingsButton;
	public JButton aboutButton;
	public JButton exitButton;
	public JButton renameButton1;
	public JButton renameButton2;
	public JButton addButton;
	public JButton folderButton;
	public JButton removeButton;
	public JButton clearButton;
	public JButton upButton;
	public JButton downButton;
	public JButton topButton;
	public JButton bottomButton;

	public JLabel pattern1Lbl;
	public JLabel pattern2Lbl;

	public Settings settings;
	public SettingsFrame settingsFrame;
	public AboutFrame aboutFrame;

	private SpringLayout generalLayout = new SpringLayout();
	private SpringLayout centerLayout = new SpringLayout();

	private FileListener fileListener;

	public static void main(String[] str) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SeriesRenamer gui = new SeriesRenamer();
		gui.init();
	}

	public SeriesRenamer() {
		super();
		this.setTitle("Series Renamer");
		this.setMinimumSize(new Dimension(1000, 600));
		this.setPreferredSize(new Dimension(1200, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {
		// General panel
		setLayout(generalLayout);
		fileListener = new FileListener(this);
		fileListScroller = new JScrollPane(fileList);
		aboutFrame = new AboutFrame();
		// Settings Internal frame
		settings = Settings.load();
		try {
			fileCurrentDir = new java.io.File(settings.getLastFolder());
		} catch (Exception e) {
		}
		settingsFrame = new SettingsFrame(this);

		// Init zones
		initTopZone();
		initListZone();

		// Drag & drop
		new FileDrop(this, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				DefaultListModel model = (DefaultListModel) fileList.getModel();
				model.clear();
				addFiles(files);
				checkEnabledButtons();
			}
		});

		this.pack();
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(ICON_URL).getImage());
		this.setVisible(true);

		ToolTipManager.sharedInstance().setDismissDelay(60000);
	}

	private void initTopZone() {

		// Top panel
		SpringLayout topLayout = new SpringLayout();
		topPanel = new JPanel(topLayout);
		this.add(topPanel);
		generalLayout.putConstraint(SpringLayout.WEST, topPanel, 15, SpringLayout.WEST, getContentPane());
		generalLayout.putConstraint(SpringLayout.NORTH, topPanel, 0, SpringLayout.NORTH, getContentPane());
		generalLayout.putConstraint(SpringLayout.EAST, topPanel, -15, SpringLayout.EAST, getContentPane());
		generalLayout.putConstraint(SpringLayout.SOUTH, topPanel, 40, SpringLayout.NORTH, getContentPane());

		// Search label
		JLabel searchLbl = new JLabel("Nom de la série :");
		topPanel.add(searchLbl);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, searchLbl, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		// Search field
		searchField.addActionListener(new SearchSeriesListener(this));
		searchField.addFocusListener(new SearchSeriesListener(this));
		searchField.setToolTipText("Entrer tout ou partie du nom d'une série TV");
		topPanel.add(searchField);
		topLayout.putConstraint(SpringLayout.WEST, searchField, 5, SpringLayout.EAST, searchLbl);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, searchField, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		// Search OK button
		JButton searchOKButton = new JButton("Chercher !", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/search.png")));
		searchOKButton.setToolTipText("Lancer la recherche");
		topPanel.add(searchOKButton);
		searchOKButton.addActionListener(new SearchSeriesListener(this));
		topLayout.putConstraint(SpringLayout.WEST, searchOKButton, 5, SpringLayout.EAST, searchField);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, searchOKButton, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		// Providers label
		JLabel providerLbl = new JLabel("Source :");
		topPanel.add(providerLbl);
		topLayout.putConstraint(SpringLayout.WEST, providerLbl, 20, SpringLayout.EAST, searchOKButton);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, providerLbl, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		// Init provider combobox
		providerImages = new ImageIcon[3];
		Integer[] intArray = new Integer[] { 0, 1, 2 };
		providerImages[0] = new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/allocine.JPG"));
		providerImages[0].setDescription("Allocine (FR+EN)");
		providerImages[1] = new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/betaseries.png"));
		providerImages[1].setDescription("Betaseries (EN)");
		providerImages[2] = new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/tmdb.png"));
		providerImages[2].setDescription("The Movie Database (EN)");
		ComboBoxRenderer renderer = new ComboBoxRenderer(this);
		providersCB = new JComboBox(intArray);
		providersCB.setRenderer(renderer);

		// Providers Combobox
		topPanel.add(providersCB);
		Integer providerIndex = settings.getProviderIndex();
		if (providerIndex < providersCB.getModel().getSize()) {
			providersCB.setSelectedIndex(providerIndex);
		} else {
			providersCB.setSelectedIndex(0);
			updateProvider();
		}
		providersCB.setToolTipText("Choisir le site Internet fournissant les données");
		topLayout.putConstraint(SpringLayout.WEST, providersCB, 5, SpringLayout.EAST, providerLbl);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, providersCB, 0, SpringLayout.VERTICAL_CENTER, topPanel);
		updateProvider();

		// Settings button
		settingsButton = new JButton("Réglages", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/settings.png")));
		settingsButton.addActionListener(new SettingsListener(this));
		settingsButton.setToolTipText("Réglages de l'application");
		aboutButton = new JButton("A propos", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/about.png")));
		aboutButton.setToolTipText("A propos de Series Renamer");
		aboutButton.addActionListener(new SettingsListener(this));
		exitButton = new JButton("Quitter", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/exit.png")));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setToolTipText("Quitter l'application");

		topPanel.add(settingsButton);
		topPanel.add(aboutButton);
		topPanel.add(exitButton);

		topLayout.putConstraint(SpringLayout.EAST, exitButton, 0, SpringLayout.EAST, topPanel);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, exitButton, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		topLayout.putConstraint(SpringLayout.EAST, aboutButton, -5, SpringLayout.WEST, exitButton);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, aboutButton, 0, SpringLayout.VERTICAL_CENTER, topPanel);

		topLayout.putConstraint(SpringLayout.EAST, settingsButton, -5, SpringLayout.WEST, aboutButton);
		topLayout.putConstraint(SpringLayout.VERTICAL_CENTER, settingsButton, 0, SpringLayout.VERTICAL_CENTER, topPanel);
	}

	private void initListZone() {
		// Center panel
		centerPanel = new JPanel(centerLayout);
		this.add(centerPanel);
		generalLayout.putConstraint(SpringLayout.NORTH, centerPanel, 0, SpringLayout.SOUTH, topPanel);
		generalLayout.putConstraint(SpringLayout.SOUTH, centerPanel, -15, SpringLayout.SOUTH, getContentPane());
		generalLayout.putConstraint(SpringLayout.WEST, centerPanel, 15, SpringLayout.WEST, getContentPane());
		generalLayout.putConstraint(SpringLayout.EAST, centerPanel, -15, SpringLayout.EAST, getContentPane());

		// Inits
		initSeriesZone();
		initSeasonsZone();
		initEpisodesZone();
		initButtonsZone();
		initFilesZone();
	}

	private void initSeriesZone() {
		// Label
		JLabel seriesLabel = new JLabel("Séries :");
		centerPanel.add(seriesLabel);

		// List
		seriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		seriesList.setLayoutOrientation(JList.VERTICAL);
		seriesList.setVisibleRowCount(-1);
		seriesList.addListSelectionListener(new SelectSeriesListener(this));
		JScrollPane seriesListScroller = new JScrollPane(seriesList);
		centerPanel.add(seriesListScroller);

		// seriesList.setToolTipText("Résultats de la recherche");

		centerLayout.putConstraint(SpringLayout.WEST, seriesListScroller, 0, SpringLayout.WEST, centerPanel);
		centerLayout.putConstraint(SpringLayout.EAST, seriesListScroller, -60, SpringLayout.HORIZONTAL_CENTER, centerPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, seriesListScroller, 5, SpringLayout.SOUTH, seriesLabel);
		centerLayout.putConstraint(SpringLayout.SOUTH, seriesListScroller, -100, SpringLayout.VERTICAL_CENTER, centerPanel);
	}

	private void initSeasonsZone() {
		// Label
		JLabel seasonsLabel = new JLabel("Saisons :");
		centerPanel.add(seasonsLabel);
		centerLayout.putConstraint(SpringLayout.WEST, seasonsLabel, 60, SpringLayout.HORIZONTAL_CENTER, centerPanel);

		// List
		seasonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		seasonList.setLayoutOrientation(JList.VERTICAL);
		seasonList.setVisibleRowCount(-1);
		seasonList.addListSelectionListener(new SelectSeasonListener(this));
		JScrollPane seasonListScroller = new JScrollPane(seasonList);
		centerPanel.add(seasonListScroller);

		seasonList.setToolTipText("Saisons disponibles pour la série sélectionnée");

		centerLayout.putConstraint(SpringLayout.WEST, seasonListScroller, 60, SpringLayout.HORIZONTAL_CENTER, centerPanel);
		centerLayout.putConstraint(SpringLayout.EAST, seasonListScroller, 0, SpringLayout.EAST, centerPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, seasonListScroller, 5, SpringLayout.SOUTH, seasonsLabel);
		centerLayout.putConstraint(SpringLayout.SOUTH, seasonListScroller, -100, SpringLayout.VERTICAL_CENTER, centerPanel);
	}

	private void initEpisodesZone() {
		// Label
		JLabel episodesLabel = new JLabel("Episodes :");
		centerPanel.add(episodesLabel);
		centerLayout.putConstraint(SpringLayout.WEST, episodesLabel, 0, SpringLayout.WEST, centerPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, episodesLabel, -85, SpringLayout.VERTICAL_CENTER, centerPanel);

		// List
		episodeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		episodeList.setLayoutOrientation(JList.VERTICAL);
		episodeList.setVisibleRowCount(-1);
		episodeList.addListSelectionListener(fileListener);
		episodeListScroller = new JScrollPane(episodeList);
		centerPanel.add(episodeListScroller);

		episodeList.setToolTipText("Episodes disponibles pour la saison sélectionnée");

		centerLayout.putConstraint(SpringLayout.WEST, episodeListScroller, 0, SpringLayout.WEST, centerPanel);
		centerLayout.putConstraint(SpringLayout.EAST, episodeListScroller, -60, SpringLayout.HORIZONTAL_CENTER, centerPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, episodeListScroller, 5, SpringLayout.SOUTH, episodesLabel);
		centerLayout.putConstraint(SpringLayout.SOUTH, episodeListScroller, 0, SpringLayout.SOUTH, centerPanel);

	}

	private void initButtonsZone() {

		// Init buttons
		renameButton1 = new JButton("Renommer", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/rename.png")));
		renameButton2 = new JButton("Renommer", new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/rename.png")));
		renameButton1.setHorizontalTextPosition(SwingConstants.LEFT);
		renameButton2.setHorizontalTextPosition(SwingConstants.LEFT);

		pattern1Lbl = new JLabel("<html><u>" + settings.getPatternName1() + "</u></html>");
		pattern1Lbl.setFont(new Font(pattern1Lbl.getFont().getName(), Font.PLAIN, 13));
		pattern1Lbl.setHorizontalAlignment(JLabel.CENTER);

		pattern2Lbl = new JLabel("<html><u>" + settings.getPatternName2() + "</u></html>");
		pattern2Lbl.setFont(new Font(pattern2Lbl.getFont().getName(), Font.PLAIN, 13));
		pattern2Lbl.setHorizontalAlignment(JLabel.CENTER);

		// Tool tips
		renameButton1.setToolTipText("Renommer les fichiers sur le disque");
		renameButton2.setToolTipText("Renommer les fichiers sur le disque");

		// Add to panel
		centerPanel.add(pattern1Lbl);
		centerPanel.add(renameButton1);
		centerPanel.add(pattern2Lbl);
		centerPanel.add(renameButton2);

		// Positioning
		centerLayout.putConstraint(SpringLayout.WEST, pattern1Lbl, 0, SpringLayout.EAST, episodeListScroller);
		centerLayout.putConstraint(SpringLayout.EAST, pattern1Lbl, 0, SpringLayout.WEST, fileListScroller);
		centerLayout.putConstraint(SpringLayout.NORTH, pattern1Lbl, 0, SpringLayout.NORTH, episodeListScroller);

		centerLayout.putConstraint(SpringLayout.WEST, renameButton1, 8, SpringLayout.EAST, episodeListScroller);
		centerLayout.putConstraint(SpringLayout.NORTH, renameButton1, 5, SpringLayout.SOUTH, pattern1Lbl);
		//

		centerLayout.putConstraint(SpringLayout.WEST, pattern2Lbl, 0, SpringLayout.WEST, pattern1Lbl);
		centerLayout.putConstraint(SpringLayout.EAST, pattern2Lbl, 0, SpringLayout.WEST, fileListScroller);
		centerLayout.putConstraint(SpringLayout.NORTH, pattern2Lbl, 20, SpringLayout.SOUTH, renameButton1);

		centerLayout.putConstraint(SpringLayout.WEST, renameButton2, 0, SpringLayout.WEST, renameButton1);
		centerLayout.putConstraint(SpringLayout.NORTH, renameButton2, 5, SpringLayout.SOUTH, pattern2Lbl);

		// Disable
		renameButton1.setEnabled(false);
		renameButton2.setEnabled(false);

		// Listeners
		renameButton1.addActionListener(fileListener);
		renameButton2.addActionListener(fileListener);

	}

	private void initFilesZone() {

		JLabel fichiersLabel = new JLabel("Fichiers :");
		JPanel fileButtonsPanel = new JPanel();

		// Label
		centerPanel.add(fichiersLabel);
		centerLayout.putConstraint(SpringLayout.WEST, fichiersLabel, 60, SpringLayout.HORIZONTAL_CENTER, centerPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, fichiersLabel, -85, SpringLayout.VERTICAL_CENTER, centerPanel);

		// File list
		fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fileList.setLayoutOrientation(JList.VERTICAL);
		fileList.setVisibleRowCount(-1);
		fileList.addListSelectionListener(fileListener);
		fileList.setToolTipText("Liste des fichiers à renommer");
		centerPanel.add(fileListScroller);

		centerLayout.putConstraint(SpringLayout.WEST, fileListScroller, 60, SpringLayout.HORIZONTAL_CENTER, centerPanel);
		centerLayout.putConstraint(SpringLayout.EAST, fileListScroller, -2, SpringLayout.WEST, fileButtonsPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, fileListScroller, 5, SpringLayout.SOUTH, fichiersLabel);
		centerLayout.putConstraint(SpringLayout.SOUTH, fileListScroller, 0, SpringLayout.SOUTH, centerPanel);

		// Buttons
		fileButtonsPanel.setLayout(new BoxLayout(fileButtonsPanel, BoxLayout.Y_AXIS));

		addButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/files.png")));
		folderButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/folder.png")));
		removeButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/remove.png")));
		clearButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/clear.png")));
		topButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/arrow_top.png")));
		upButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/arrow_up.png")));
		downButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/arrow_down.png")));
		bottomButton = new JButton(new ImageIcon(SeriesRenamer.class.getResource("/com/series/renamer/resources/arrow_bottom.png")));

		addButton.addActionListener(fileListener);
		folderButton.addActionListener(fileListener);
		removeButton.addActionListener(fileListener);
		clearButton.addActionListener(fileListener);
		topButton.addActionListener(fileListener);
		upButton.addActionListener(fileListener);
		downButton.addActionListener(fileListener);
		bottomButton.addActionListener(fileListener);

		fileButtonsPanel.add(addButton);
		fileButtonsPanel.add(folderButton);
		fileButtonsPanel.add(removeButton);
		// fileButtonsPanel.add(clearButton);
		fileButtonsPanel.add(topButton);
		fileButtonsPanel.add(upButton);
		fileButtonsPanel.add(downButton);
		fileButtonsPanel.add(bottomButton);

		centerPanel.add(fileButtonsPanel);
		centerLayout.putConstraint(SpringLayout.NORTH, fileButtonsPanel, 5, SpringLayout.SOUTH, fichiersLabel);
		centerLayout.putConstraint(SpringLayout.EAST, fileButtonsPanel, 0, SpringLayout.EAST, centerPanel);

		// Tool tips
		addButton.setToolTipText("Choisir les fichiers à renommer");
		folderButton.setToolTipText("Choisir un répertoire");
		removeButton.setToolTipText("Supprimer les fichiers sélectionnés de la liste");
		clearButton.setToolTipText("Vider la liste");
		topButton.setToolTipText("Monter la sélection en haut de la liste");
		upButton.setToolTipText("Monter la sélection d'un cran");
		downButton.setToolTipText("Descendre la sélection d'unn cran");
		bottomButton.setToolTipText("Descendre la sélection en bas de la liste");
	}

	public void updateProvider() {
		int selectedIndex = this.providersCB.getSelectedIndex();
		switch (selectedIndex) {
		case 0:
			this.searchUtils = new AllocineSearchUtils();
			break;

		case 1:
			this.searchUtils = new BetaSeriesSearchUtils();
			break;

		case 2:
			this.searchUtils = new TMDBSearchUtils();
			break;

		default:
			this.searchUtils = new AllocineSearchUtils();
			break;
		}
		settings.setProviderIndex(selectedIndex);
		settings.save();
	}

	public boolean checkEnabledButtons() {

		// Activate rename buttons or not
		int episodeNb = episodeList.getSelectedIndices().length;
		if (episodeNb == 0) {
			episodeNb = ((DefaultListModel) episodeList.getModel()).size();
		}
		int fileNb = fileList.getSelectedIndices().length;
		if (fileNb == 0) {
			fileNb = ((DefaultListModel) fileList.getModel()).size();
		}

		boolean enabled = episodeNb == fileNb && fileList.getModel().getSize() + episodeList.getModel().getSize() > 0;

		renameButton1.setEnabled(enabled);
		renameButton2.setEnabled(enabled);

		return enabled;
	}

	public void renameFiles(boolean renameRealFile, String pattern) {
		if (!checkEnabledButtons()) {
			return;
		}

		Serie serie = (Serie) seriesList.getSelectedValue();
		Season season = (Season) seasonList.getSelectedValue();

		DefaultListModel episodeModel = (DefaultListModel) episodeList.getModel();
		DefaultListModel fileModel = (DefaultListModel) fileList.getModel();

		if (episodeList.getSelectedIndices().length == 0) {
			episodeList.setSelectionInterval(0, episodeModel.size() - 1);
		}
		if (fileList.getSelectedIndices().length == 0) {
			fileList.setSelectionInterval(0, fileModel.size() - 1);
		}

		for (int i = 0; i < episodeList.getSelectedIndices().length; i++) {
			renameFile(pattern, serie, season, (Episode) episodeList.getSelectedValuesList().get(i), (File) fileList.getSelectedValuesList().get(i),
					episodeModel.size(), renameRealFile);
		}

		fileList.repaint();
		fileListScroller.repaint();
		fileListScroller.validate();
		File fake = new File(null);
		fileModel.addElement(fake);
		fileModel.removeElement(fake);

	}

	public void renameFile(String pattern, Serie serie, Season season, Episode episode, File file, int nbEpisodes, boolean renameRealFile) {
		String extension = "";
		try {
			extension = file.getFile().getName().substring(file.getFile().getName().lastIndexOf('.'));
		} catch (Exception e) {
		}

		// String replacement
		String name = pattern;
		name = name.replaceAll("%svf%", serie.getTitle());
		name = name.replaceAll("%svo%", serie.getOriginalTitle());
		name = name.replaceAll("%sai%", season.getStrOrder());

		String episodeNo = String.valueOf(episode.getSeasonOrder());
		if (nbEpisodes >= 100 && episode.getSeasonOrder() < 100) {
			episodeNo = "0" + episodeNo;
		}
		if (episode.getSeasonOrder() < 10) {
			episodeNo = "0" + episodeNo;
		}
		name = name.replaceAll("%esa%", episodeNo);

		episodeNo = String.valueOf(episode.getSerieOrder());
		if (episode.getSerieOrder() < 100) {
			episodeNo = "0" + episodeNo;
		}
		if (episode.getSerieOrder() < 10) {
			episodeNo = "0" + episodeNo;
		}
		name = name.replaceAll("%ese%", episodeNo);

		name = name.replaceAll("%evf%", episode.getTitle());
		name = name.replaceAll("%evo%", episode.getOriginalTitle());
		name = name.replaceAll("%ann%", episode.getYear());
		name = name.replaceAll("%dat%", episode.getDate());

		name = FileNameCleaner.cleanFileName(name);

		if (settings.isReplaceSpaces()) {
			name = name.replaceAll(" ", ".");
		}

		name += extension;
		file.setDisplayName(name);

		if (renameRealFile) {
			java.io.File parentFolder = null;
			if (settings.isExtractSubFolders()) {
				parentFolder = fileCurrentDir;
			} else {
				parentFolder = file.getFile().getParentFile();
			}
			java.io.File newFile = new java.io.File(parentFolder, name);
			file.getFile().renameTo(newFile);
			file.setFile(newFile);
		}
	}

	private void addFiles(java.io.File[] selectedFiles, boolean inLoop) {
		DefaultListModel model = (DefaultListModel) fileList.getModel();

		List<com.series.renamer.bean.File> fileList = new ArrayList<com.series.renamer.bean.File>();

		if (selectedFiles != null && selectedFiles.length > 0) {
			if (!inLoop) {
				setFileCurrentDir(selectedFiles[0].getParentFile());
			}

			int i = model.size();
			for (java.io.File file : selectedFiles) {
				if (file.isFile() && Utils.isVideoFile(file)) {
					com.series.renamer.bean.File fileObj = new com.series.renamer.bean.File(file);
					fileObj.setOrder(++i);
					fileList.add(fileObj);

				} else if (file.isDirectory()) {
					addFiles(file.listFiles(), true);
				}
			}
			Collections.sort(fileList);
			for (com.series.renamer.bean.File file : fileList) {
				model.addElement(file);
			}

		}
	}

	public void addFolder(java.io.File selectedFile) {
		addFiles(selectedFile.listFiles());
	}

	public void addFiles(java.io.File[] selectedFiles) {
		addFiles(selectedFiles, false);
	}

	public void setFileCurrentDir(java.io.File fileCurrentDir) {
		this.fileCurrentDir = fileCurrentDir;
		settings.setLastFolder(fileCurrentDir.getPath());
		settings.save();
	}

}
