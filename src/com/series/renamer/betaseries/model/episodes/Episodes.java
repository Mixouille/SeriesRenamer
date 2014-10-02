package com.series.renamer.betaseries.model.episodes;

import java.util.List;

public class Episodes {
	private String code;
	private String comments;
	private String date;
	private String description;
	private Number episode;
	private Number global;
	private Number id;
	private Note note;
	private Number season;
	private Show show;
	private List<String> subtitles;
	private Number thetvdb_id;
	private String title;
	private User user;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getEpisode() {
		return this.episode;
	}

	public void setEpisode(Number episode) {
		this.episode = episode;
	}

	public Number getGlobal() {
		return this.global;
	}

	public void setGlobal(Number global) {
		this.global = global;
	}

	public Number getId() {
		return this.id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public Note getNote() {
		return this.note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Number getSeason() {
		return this.season;
	}

	public void setSeason(Number season) {
		this.season = season;
	}

	public Show getShow() {
		return this.show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public List<String> getString() {
		return this.subtitles;
	}

	public void setString(List<String> subtitles) {
		this.subtitles = subtitles;
	}

	public Number getThetvdb_id() {
		return this.thetvdb_id;
	}

	public void setThetvdb_id(Number thetvdb_id) {
		this.thetvdb_id = thetvdb_id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
