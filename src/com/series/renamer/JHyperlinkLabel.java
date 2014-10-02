package com.series.renamer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;

public class JHyperlinkLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	private Color underlineColor = null;
	private String href = "#";

	public JHyperlinkLabel(String label, String href) {
		super("<html>" + label + "</html>");

		this.href = href;
		if (this.href == null) {
			this.href = label;
		}

		setForeground(Color.BLUE.darker());
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new HyperlinkLabelMouseAdapter());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(underlineColor == null ? getForeground() : underlineColor);

		Insets insets = getInsets();

		int left = insets.left;
		if (getIcon() != null)
			left += getIcon().getIconWidth() + getIconTextGap();

		g.drawLine(left, getHeight() - 1 - insets.bottom, (int) getPreferredSize().getWidth() - insets.right, getHeight() - 1 - insets.bottom);
	}

	public class HyperlinkLabelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 0) {
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						URI uri = new URI(href);
						desktop.browse(uri);
					} catch (IOException ex) {
						// do nothing
					} catch (URISyntaxException ex) {
						// do nothing
					}
				} else {
					// do nothing
				}

			}
		}
	}

	public Color getUnderlineColor() {
		return underlineColor;
	}

	public void setUnderlineColor(Color underlineColor) {
		this.underlineColor = underlineColor;
	}
}