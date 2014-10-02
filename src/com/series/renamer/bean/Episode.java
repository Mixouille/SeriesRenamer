package com.series.renamer.bean;

public class Episode implements Comparable<Episode> {

	private int seasonOrder;
	private int serieOrder;
	private String title;
	private String originalTitle;
	private String code;
	private String synopsis;
	private String date;

	public String getDate() {
		return date == null ? "" : date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return (date != null && date.indexOf("-") > -1) ? date.substring(0, date.indexOf("-")) : "";
	}

	public int getSerieOrder() {
		return serieOrder;
	}

	public void setSerieOrder(int serieOrder) {
		this.serieOrder = serieOrder;
	}

	public int getSeasonOrder() {
		return seasonOrder;
	}

	public void setSeasonOrder(int seasonOrder) {
		this.seasonOrder = seasonOrder;
	}

	public String getTitle() {
		String ret = title;

		if (ret == null || ret.length() == 0) {
			ret = originalTitle;
		}
		if (ret == null || ret.length() == 0) {
			ret = "NO-TITLE";
		}

		return ret;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		String ret = originalTitle;

		if (ret == null || ret.length() == 0) {
			ret = title;
		}
		if (ret == null || ret.length() == 0) {
			ret = "NO-TITLE";
		}

		return ret;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String toString() {
		String result = this.getSeasonOrder() + " - " + this.getTitle();
		if (!getTitle().equalsIgnoreCase(getOriginalTitle())) {
			result += " (" + this.getOriginalTitle() + ")";
		}
		return result;
	}

	@Override
	public int compareTo(Episode arg0) {
		return this.getSeasonOrder() - ((Episode) arg0).getSeasonOrder();
	}

}
