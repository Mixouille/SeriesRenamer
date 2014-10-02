package com.series.renamer.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.series.renamer.SeriesRenamer;

public class SettingsListener implements ActionListener {

	private SeriesRenamer seriesRenamer;

	public SettingsListener(SeriesRenamer seriesRenamer) {
		super();
		this.seriesRenamer = seriesRenamer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == seriesRenamer.settingsButton) {
			seriesRenamer.settingsFrame.setLocationRelativeTo(seriesRenamer);
			seriesRenamer.settingsFrame.setVisible(true);
		} else if (e.getSource() == seriesRenamer.aboutButton) {
			seriesRenamer.aboutFrame.setLocationRelativeTo(seriesRenamer);
			seriesRenamer.aboutFrame.setVisible(true);
		}
	}
}
