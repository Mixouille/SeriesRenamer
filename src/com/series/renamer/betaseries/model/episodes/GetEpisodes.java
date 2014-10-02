package com.series.renamer.betaseries.model.episodes;

import java.util.List;

public class GetEpisodes {
	private List<Episodes> episodes;
	private List<String> errors;

	public List<Episodes> getEpisodes() {
		return this.episodes;
	}

	public void setEpisodes(List<Episodes> episodes) {
		this.episodes = episodes;
	}

	public List<String> getString() {
		return this.errors;
	}

	public void setString(List<String> errors) {
		this.errors = errors;
	}
}
