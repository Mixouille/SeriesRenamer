package com.series.renamer.tmdb;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.series.renamer.ISearchUtils;
import com.series.renamer.bean.Episode;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;
import com.series.renamer.tmdb.model.episodes.Episodes;
import com.series.renamer.tmdb.model.episodes.GetEpisodes;
import com.series.renamer.tmdb.model.seasons.GetSeasons;
import com.series.renamer.tmdb.model.seasons.Seasons;
import com.series.renamer.tmdb.model.series.GetSeries;
import com.series.renamer.tmdb.model.series.Results;

public class TMDBSearchUtils implements ISearchUtils {

	public static final String BASE_URL = "https://api.themoviedb.org/3/";
	public static final String API_KEY = "470fd2ec8853e25d2f8d86f685d2270e";
	public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w185";

	private String currentSeriesCode = null;

	public List<Serie> getSerieList(String searchTerm) throws Exception {

		List<Serie> seriesList = new ArrayList<Serie>();

		InputStream is = null;
		Reader isr = null;

		try {

			// Contermstruct query
			String queryURL = ("search/tv?query=") + URLEncoder.encode(searchTerm, "UTF-8");

			// api key
			queryURL += "&api_key=" + API_KEY;

			// Language
			queryURL += "&language=fr";

			// Call API
			URL url = new URL(BASE_URL + queryURL);
			// System.out.println(BASE_URL + queryURL);
			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			// Parse JSON response / construct model
			Gson gson = new Gson();
			GetSeries getSeries = gson.fromJson(isr, GetSeries.class);
			List<Results> resultsList = getSeries.getResults();

			for (Results result : resultsList) {
				Serie serie = new Serie();
				serie.setCode(String.valueOf(result.getId()));
				serie.setOriginalTitle(result.getOriginal_name());
				serie.setTitle(result.getName());
				String year = result.getFirst_air_date();
				if (year != null && year.length() > 0) {
					if (year.contains("-")) {
						year = year.substring(0, year.indexOf("-"));
					}
					serie.setYear(year);
				}
				if (result.getPoster_path() != null && result.getPoster_path().length() > 0) {
					serie.setImage(IMG_BASE_URL + result.getPoster_path());
				}
				seriesList.add(serie);
			}

		} finally {
			if (is != null) {
				is.close();
			}
			if (isr != null) {
				isr.close();
			}
		}

		Collections.sort(seriesList);

		return seriesList;
	}

	public List<Season> getSeasonList(String code) throws Exception {

		List<Season> seasonList = new ArrayList<Season>();

		currentSeriesCode = code;

		String query = "tv/" + code + "?api_key=" + API_KEY;
		query += "&language=fr";

		InputStream is = null;
		Reader isr = null;
		try {
			URL url = new URL(BASE_URL + query);
			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			Gson gson = new Gson();
			GetSeasons getSeasons = gson.fromJson(isr, GetSeasons.class);
			List<Seasons> seasons = getSeasons.getSeasons();
			for (Seasons seasonJSON : seasons) {
				Season season = new Season();
				season.setCode(String.valueOf(seasonJSON.getSeason_number()));
				season.setOrder(seasonJSON.getSeason_number().intValue());

				seasonList.add(season);
			}

		} finally {
			if (is != null) {
				is.close();
			}
			if (isr != null) {
				isr.close();
			}
		}

		Collections.sort(seasonList);

		return seasonList;
	}

	public List<Episode> getEpisodeList(String code) throws Exception {

		List<Episode> episodeList = new ArrayList<Episode>();

		String query = "tv/" + currentSeriesCode + "/season/" + code;
		query += "?api_key=" + API_KEY;

		InputStream is = null;
		Reader isr = null;

		try {
			URL url = new URL(BASE_URL + query);

			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			Gson gson = new Gson();
			GetEpisodes getEpisodes = gson.fromJson(isr, GetEpisodes.class);
			List<Episodes> episodes = getEpisodes.getEpisodes();
			for (Episodes episodeJSON : episodes) {
				Episode episode = new Episode();
				episode.setCode(String.valueOf(episodeJSON.getId()));
				episode.setOriginalTitle(episodeJSON.getName());
				episode.setSeasonOrder(episodeJSON.getEpisode_number().intValue());
				episode.setSerieOrder(0);
				episode.setTitle(episodeJSON.getName());

				episodeList.add(episode);
			}

		} finally {
			if (is != null) {
				is.close();
			}
			if (isr != null) {
				isr.close();
			}
		}

		Collections.sort(episodeList);

		return episodeList;
	}
}