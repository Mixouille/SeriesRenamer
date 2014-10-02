package com.series.renamer.allocine.model.episodes;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	private List<Episode> episode = new ArrayList<Episode>();

	public List<Episode> getEpisode() {
		return this.episode;
	}

	public void setEpisode(List<Episode> episode) {
		this.episode = episode;
	}
}
