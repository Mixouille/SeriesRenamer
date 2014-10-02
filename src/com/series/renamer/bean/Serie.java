package com.series.renamer.bean;

import java.util.ArrayList;
import java.util.List;

public class Serie implements Comparable<Serie> {

	private String code;
	private String title;
	private String originalTitle;
	private Float rating;
	private String year;
	private String image;
	private List<Season> seasonList = new ArrayList<Season>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return (title == null || title.length() == 0) ? originalTitle : title;
	}

	public void setTitle(String title) {
		this.title = title != null ? title.trim() : null;
	}

	public String getOriginalTitle() {
		return (originalTitle == null || originalTitle.length() == 0) ? title : originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle != null ? originalTitle.trim() : null;
	}

	public List<Season> getSeasonList() {
		return seasonList;
	}

	@Override
	public String toString() {
		String result = this.getTitle();
		if (!getTitle().equalsIgnoreCase(getOriginalTitle())) {
			result += " (" + this.getOriginalTitle() + ")";
		}
		if (this.getYear() != null && !"0".equals(getYear())) {
			result += " - " + getYear();
		}
		if (this.getRating() != null) {
			result += " - " + getRating() + " / 5";
		}
		return result;
	}

	@Override
	public int compareTo(Serie arg0) {
		Serie serie = (Serie) arg0;
		return this.getTitle().compareTo(serie.getTitle());
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
