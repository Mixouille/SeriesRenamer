package com.series.renamer.bean;

import java.util.ArrayList;
import java.util.List;

public class Season implements Comparable<Season> {

	private String code;
	private int order = 0;
	private Float rating;
	private List<Episode> episodeList = new ArrayList<Episode>();

	public int getOrder() {
		return order;
	}

	public String getStrOrder() {
		return getOrder() >= 10 ? String.valueOf(getOrder()) : "0" + getOrder();
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<Episode> getEpisodeList() {
		return episodeList;
	}

	public void setEpisodeList(List<Episode> episodeList) {
		this.episodeList = episodeList;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		String result;
		if (getOrder() == 0) {
			result = "Hors saison";
		} else {
			result = "Saison " + getOrder();
		}
		if (this.getRating() != null) {
			result += " - " + getRating() + " / 5";
		}
		return result;
	}

	@Override
	public int compareTo(Season arg0) {
		Season season = (Season) arg0;
		return this.getOrder() - season.getOrder();
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public void setRating(String rating) {
		if (rating != null) {
			try {
				float rounded = ((float) Math.round(Float.valueOf(rating.trim()) * 10.0f)) / 10.0f;
				this.rating = rounded;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public Float getRating() {
		return rating;
	}
}
