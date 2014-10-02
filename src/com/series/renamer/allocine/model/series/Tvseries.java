package com.series.renamer.allocine.model.series;

import java.util.List;

public class Tvseries {
	private CastingShort castingShort;
	private Number code;
	private List<Link> link;
	private String originalTitle;
	private String title;
	private Poster poster;
	private Statistics statistics;
	private Number yearStart;

	public CastingShort getCastingShort() {
		return this.castingShort;
	}

	public void setCastingShort(CastingShort castingShort) {
		this.castingShort = castingShort;
	}

	public Number getCode() {
		return this.code;
	}

	public void setCode(Number code) {
		this.code = code;
	}

	public List<Link> getLink() {
		return this.link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Poster getPoster() {
		return this.poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Statistics getStatistics() {
		return this.statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Number getYearStart() {
		return this.yearStart;
	}

	public void setYearStart(Number yearStart) {
		this.yearStart = yearStart;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
