package com.series.renamer.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.series.renamer.FileChooser;
import com.series.renamer.SeriesRenamer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileListener implements ActionListener, ListSelectionListener {

	private SeriesRenamer seriesRenamer;

	public FileListener(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel model = (DefaultListModel) seriesRenamer.fileList.getModel();

		if (e.getSource() == seriesRenamer.addButton) {
			JFileChooser fc = new FileChooser(seriesRenamer.fileCurrentDir, seriesRenamer);
			fc.setMultiSelectionEnabled(true);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int res = fc.showDialog(seriesRenamer.addButton, "Sélectionner");

			if (res == JFileChooser.APPROVE_OPTION) {
				model.clear();
				java.io.File[] selectedFiles = fc.getSelectedFiles();
				seriesRenamer.addFiles(selectedFiles);
			}

		} else if (e.getSource() == seriesRenamer.folderButton) {
			JFileChooser fc = new FileChooser(seriesRenamer.fileCurrentDir, seriesRenamer);
			fc.setMultiSelectionEnabled(false);
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int res = fc.showDialog(seriesRenamer.addButton, "Sélectionner");

			if (res == JFileChooser.APPROVE_OPTION) {
				model.clear();
				java.io.File selectedFile = fc.getSelectedFile();
				seriesRenamer.addFolder(selectedFile);
			}

		} else if (e.getSource() == seriesRenamer.removeButton) {
			List<Object> toremove = seriesRenamer.fileList.getSelectedValuesList();
			for (Object obj : toremove) {
				model.removeElement(obj);
			}

		} else if (e.getSource() == seriesRenamer.clearButton) {
			model.clear();

		} else if (seriesRenamer.fileList.getSelectedIndices().length > 0 && e.getSource() == seriesRenamer.upButton
				&& !seriesRenamer.fileList.isSelectedIndex(0)) {

			moveUp();

		} else if (seriesRenamer.fileList.getSelectedIndices().length > 0 && e.getSource() == seriesRenamer.downButton
				&& !seriesRenamer.fileList.isSelectedIndex(model.size() - 1)) {

			moveDown();

		} else if (seriesRenamer.fileList.getSelectedIndices().length > 0 && e.getSource() == seriesRenamer.topButton) {

			moveTop();

		} else if (seriesRenamer.fileList.getSelectedIndices().length > 0 && e.getSource() == seriesRenamer.bottomButton) {

			moveBottom();

			// ///////////////
			// RENAME BUTTONS
		} else if (e.getSource() == seriesRenamer.renameButton1) {
			seriesRenamer.renameFiles(true, seriesRenamer.settings.getPattern1());
		} else if (e.getSource() == seriesRenamer.renameButton2) {
			seriesRenamer.renameFiles(true, seriesRenamer.settings.getPattern2());
		}

		seriesRenamer.checkEnabledButtons();
	}

	private void moveUp() {
		DefaultListModel model = (DefaultListModel) seriesRenamer.fileList.getModel();
		List<Object> toUp = seriesRenamer.fileList.getSelectedValuesList();
		int[] indices = seriesRenamer.fileList.getSelectedIndices();

		for (Object obj : toUp) {
			int index = model.indexOf(obj);
			Object obj2 = model.getElementAt(index - 1);
			model.setElementAt(obj, index - 1);
			model.setElementAt(obj2, index);
		}
		for (int i = 0; i < indices.length; i++) {
			indices[i] = indices[i] - 1;
		}
		seriesRenamer.fileList.setSelectedIndices(indices);
	}

	private void moveDown() {
		DefaultListModel model = (DefaultListModel) seriesRenamer.fileList.getModel();
		List<Object> toDown = seriesRenamer.fileList.getSelectedValuesList();
		int[] indices = seriesRenamer.fileList.getSelectedIndices();

		for (int i = toDown.size() - 1; i >= 0; i--) {
			Object obj = toDown.get(i);
			int index = model.indexOf(obj);
			Object obj2 = model.getElementAt(index + 1);
			model.setElementAt(obj, index + 1);
			model.setElementAt(obj2, index);
		}
		for (int i = 0; i < indices.length; i++) {
			indices[i] = indices[i] + 1;
		}
		seriesRenamer.fileList.setSelectedIndices(indices);
	}

	private void moveTop() {
		while (!seriesRenamer.fileList.isSelectedIndex(0)) {
			moveUp();
		}
	}

	private void moveBottom() {
		DefaultListModel model = (DefaultListModel) seriesRenamer.fileList.getModel();
		while (!seriesRenamer.fileList.isSelectedIndex(model.size() - 1)) {
			moveDown();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
		seriesRenamer.checkEnabledButtons();
	}
}
