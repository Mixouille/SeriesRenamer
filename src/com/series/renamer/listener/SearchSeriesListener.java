package com.series.renamer.listener;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.series.renamer.SeriesRenamer;
import com.series.renamer.bean.Serie;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SearchSeriesListener implements ActionListener, FocusListener {

	private SeriesRenamer seriesRenamer;

	public SearchSeriesListener(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			seriesRenamer.updateProvider();

			if (seriesRenamer.searchField.getText().length() > 0) {
				seriesRenamer.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				List<Serie> serieList = seriesRenamer.searchUtils.getSerieList(seriesRenamer.searchField.getText());
				DefaultListModel model = (DefaultListModel) seriesRenamer.seriesList.getModel();
				model.clear();
				((DefaultListModel) seriesRenamer.seasonList.getModel()).clear();
				for (Serie serie : serieList) {
					model.addElement(serie);
				}

				if (serieList.isEmpty()) {
					JOptionPane.showMessageDialog(seriesRenamer, "Aucun résultat", "Recherche", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(seriesRenamer,
					"Erreur de connexion. Vous n'êtes pas connecté à Internet ou le site Internet est inaccessible.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			seriesRenamer.setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void focusGained(FocusEvent paramFocusEvent) {
		if (paramFocusEvent.getSource() == seriesRenamer.searchField) {
			seriesRenamer.searchField.selectAll();
		}
	}

	@Override
	public void focusLost(FocusEvent paramFocusEvent) {
		// TNothing
	}
}
