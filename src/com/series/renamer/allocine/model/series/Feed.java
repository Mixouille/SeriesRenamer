package com.series.renamer.allocine.model.series;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	private Number count;
	private Number page;
	private List<Results> results = new ArrayList<Results>();
	private Number totalResults;
	private List<Tvseries> tvseries = new ArrayList<Tvseries>();

	public Number getCount() {
		return this.count;
	}

	public void setCount(Number count) {
		this.count = count;
	}

	public Number getPage() {
		return this.page;
	}

	public void setPage(Number page) {
		this.page = page;
	}

	public List<Results> getResults() {
		return this.results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public Number getTotalResults() {
		return this.totalResults;
	}

	public void setTotalResults(Number totalResults) {
		this.totalResults = totalResults;
	}

	public List<Tvseries> getTvseries() {
		return this.tvseries;
	}

	public void setTvseries(List<Tvseries> tvseries) {
		this.tvseries = tvseries;
	}
}
