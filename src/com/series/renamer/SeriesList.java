package com.series.renamer;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ToolTipManager;

import com.series.renamer.bean.Serie;

public class SeriesList extends JList<Serie> {

	private static final long serialVersionUID = 1L;

	public SeriesList(ListModel<Serie> paramListModel) {
		super(paramListModel);
		ToolTipManager.sharedInstance().registerComponent(this);
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		Point p = event.getPoint();
		int location = locationToIndex(p);
		if (getModel().getElementAt(location) == null)
			return null;
		Serie serie = getModel().getElementAt(location);
		if (serie.getImage() == null || serie.getImage().isEmpty()) {
			return null;
		}
		return "<html><img src='" + serie.getImage() + "'/></html>";
	}
}
