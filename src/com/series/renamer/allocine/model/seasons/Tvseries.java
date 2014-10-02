package com.series.renamer.allocine.model.seasons;

import java.util.ArrayList;
import java.util.List;

public class Tvseries {
	private Number code;
	private String originalTitle;
	private List<Season> season = new ArrayList<Season>();
	private String title;

	public Number getCode() {
		return this.code;
	}

	public void setCode(Number code) {
		this.code = code;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public List<Season> getSeason() {
		return this.season;
	}

	public void setSeason(List<Season> season) {
		this.season = season;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
