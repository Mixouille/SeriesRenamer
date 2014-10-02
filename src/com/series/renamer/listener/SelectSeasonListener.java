package com.series.renamer.listener;

import java.awt.Cursor;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.series.renamer.SeriesRenamer;
import com.series.renamer.bean.Episode;
import com.series.renamer.bean.Season;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SelectSeasonListener implements ListSelectionListener {

	private SeriesRenamer seriesRenamer;

	public SelectSeasonListener(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			seriesRenamer.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			DefaultListModel model = (DefaultListModel) seriesRenamer.episodeList.getModel();
			model.clear();
			Season season = (Season) seriesRenamer.seasonList.getSelectedValue();
			if (season != null) {
				try {
					List<Episode> episodeList = seriesRenamer.searchUtils.getEpisodeList(season.getCode());
					for (Episode episode : episodeList) {
						model.addElement(episode);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(seriesRenamer,
							"Erreur de connexion. Vous n'êtes pas connecté à Internet ou le site Internet est inaccessible.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} finally {
					seriesRenamer.setCursor(Cursor.getDefaultCursor());
				}
			}

			// Activate rename buttons or not
			boolean enabled = model.size() > 0 && model.size() == ((DefaultListModel) seriesRenamer.fileList.getModel()).size();
			seriesRenamer.renameButton1.setEnabled(enabled);
			seriesRenamer.renameButton2.setEnabled(enabled);

			seriesRenamer.setCursor(Cursor.getDefaultCursor());
		}

	}
}
