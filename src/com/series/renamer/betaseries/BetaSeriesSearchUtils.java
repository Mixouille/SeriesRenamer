package com.series.renamer.betaseries;

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
import com.series.renamer.betaseries.model.episodes.Episodes;
import com.series.renamer.betaseries.model.episodes.GetEpisodes;
import com.series.renamer.betaseries.model.series.GetSeries;
import com.series.renamer.betaseries.model.series.Seasons_details;
import com.series.renamer.betaseries.model.series.Shows;

public class BetaSeriesSearchUtils implements ISearchUtils {

	public static final String BASE_URL = "https://api.betaseries.com/";
	public static final String API_KEY = "b0a33a7d00ef";

	private List<Shows> shows = null;

	public List<Serie> getSerieList(String searchTerm) throws Exception {

		List<Serie> seriesList = new ArrayList<Serie>();

		InputStream is = null;
		Reader isr = null;

		try {

			// Contermstruct query
			String queryURL = ("shows/search?title=") + URLEncoder.encode(searchTerm, "UTF-8");

			// api key
			queryURL += "&summary=0&key=" + API_KEY;

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
			List<Shows> resultsList = getSeries.getShows();
			shows = resultsList;

			for (Shows result : resultsList) {
				Serie serie = new Serie();
				serie.setCode(String.valueOf(result.getId()));
				serie.setTitle(result.getTitle());
				String year = result.getCreation();
				if (year != null && year.length() > 0) {
					serie.setYear(year);
				}
				// if (result.getResource_url() != null &&
				// result.getResource_url().length() > 0) {
				// serie.setImage(result.getResource_url());
				// }
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

		for (Shows show : shows) {
			if (show.getId().toString().equals(code)) {
				for (Seasons_details seasonJSON : show.getSeasons_details()) {

					Season season = new Season();
					season.setCode(show.getId() + "_" + String.valueOf(seasonJSON.getNumber()));
					season.setOrder(seasonJSON.getNumber().intValue());

					seasonList.add(season);
				}
				Collections.sort(seasonList);
				break;
			}
		}

		return seasonList;
	}

	public List<Episode> getEpisodeList(String code) throws Exception {

		List<Episode> episodeList = new ArrayList<Episode>();

		String[] split = code.split("_");

		String query = "shows/episodes?id=" + split[0];
		query += "&season=" + split[1];
		query += "&key=" + API_KEY;

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
				episode.setSeasonOrder(episodeJSON.getEpisode().intValue());
				episode.setSerieOrder(episodeJSON.getGlobal().intValue());
				episode.setTitle(episodeJSON.getTitle());

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