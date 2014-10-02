package com.series.renamer;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser implements Constants {

	private static final long serialVersionUID = 1L;
	private SeriesRenamer seriesRenamer;

	public FileChooser(File currentDirectory, SeriesRenamer seriesRenamer) {
		super(currentDirectory);
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	protected JDialog createDialog(Component parent) throws HeadlessException {
		JDialog dlg = super.createDialog(parent);

		// Size and position
		dlg.setSize(800, 500);
		dlg.setLocation((int) ((seriesRenamer.getWidth() - dlg.getWidth()) / 2 + seriesRenamer.getLocation().getX()),
				(int) ((seriesRenamer.getHeight() - dlg.getHeight()) / 2 + seriesRenamer.getLocation().getY()));

		// Extensions filter
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers vidéos", VIDEO_EXTENSIONS);
		this.setFileFilter(filter);

		return dlg;
	}

}
