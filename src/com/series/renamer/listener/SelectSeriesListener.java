package com.series.renamer.listener;

import java.awt.Cursor;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.series.renamer.SeriesRenamer;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SelectSeriesListener implements ListSelectionListener {

	private SeriesRenamer seriesRenamer;

	public SelectSeriesListener(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting()) {
			seriesRenamer.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			DefaultListModel model = (DefaultListModel) seriesRenamer.seasonList.getModel();
			model.clear();
			Serie serie = (Serie) seriesRenamer.seriesList.getSelectedValue();
			if (serie != null) {
				try {
					List<Season> seasonList = seriesRenamer.searchUtils.getSeasonList(serie.getCode());
					for (Season season : seasonList) {
						model.addElement(season);
					}
				} catch (Exception e1) {
					// TODO Gérer exception
					e1.printStackTrace();
					JOptionPane.showMessageDialog(seriesRenamer,
							"Erreur de connexion. Vous n'êtes pas connecté à Internet ou le site Internet est inaccessible.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} finally {
					seriesRenamer.setCursor(Cursor.getDefaultCursor());
				}
			}

			seriesRenamer.setCursor(Cursor.getDefaultCursor());
		}
	}
}
