package com.series.renamer.allocine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.series.renamer.ISearchUtils;
import com.series.renamer.allocine.model.episodes.GetEpisodes;
import com.series.renamer.allocine.model.seasons.GetSeasons;
import com.series.renamer.allocine.model.series.Feed;
import com.series.renamer.allocine.model.series.GetSeries;
import com.series.renamer.allocine.model.series.Tvseries;
import com.series.renamer.bean.Episode;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;

public class AllocineSearchUtils implements ISearchUtils {

	public static final String BASE_URL = "http://api.allocine.fr/rest/v3/";
	public static final String PARTNER_ID = "100043982026";
	public static final String HASHKEY = "29d185d98c984a359e6e6f26a0474269";

	public List<Serie> getSerieList(String searchTerm) throws Exception {

		List<Serie> seriesList = new ArrayList<Serie>();

		InputStream is = null;
		Reader isr = null;

		try {

			// Contermstruct query
			String searchQuery = ("filter=tvseries&q=") + URLEncoder.encode(searchTerm, "UTF-8");

			// Partner
			searchQuery += "&partner=" + PARTNER_ID;

			// Add sed (date)
			searchQuery += "&count=50&page=1&format=json&sed=" + getSed();

			String queryURL = "search?" + searchQuery + "&sig=" + getSig(searchQuery);

			// Call Allocine API
			URL url = new URL(BASE_URL + queryURL);
			// System.out.println(BASE_URL + queryURL);
			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			// Parse JSON response / construct model
			Gson gson = new Gson();
			GetSeries getSeries = gson.fromJson(isr, GetSeries.class);
			Feed feed = getSeries.getFeed();
			List<Tvseries> tvseriesList = feed.getTvseries();

			for (Tvseries tvseries : tvseriesList) {
				Serie serie = new Serie();
				serie.setCode(String.valueOf(tvseries.getCode()));
				serie.setOriginalTitle(tvseries.getOriginalTitle());
				serie.setTitle(tvseries.getTitle());
				serie.setYear(String.valueOf(tvseries.getYearStart()));
				if (tvseries.getPoster() != null) {
					serie.setImage(tvseries.getPoster().getHref());
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
		String query = "partner=" + PARTNER_ID;
		query += "&code=" + code;
		query += "&profile=large&format=json&filter=movie&striptags=synopsis,synopsisshort";
		query += "&sed=" + getSed();
		query += "&sig=" + getSig(query);

		InputStream is = null;
		Reader isr = null;
		try {
			URL url = new URL(BASE_URL + "tvseries?" + query);
			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			Gson gson = new Gson();
			GetSeasons getSeasons = gson.fromJson(isr, GetSeasons.class);
			com.series.renamer.allocine.model.seasons.Tvseries tvseries = getSeasons.getTvseries();
			List<com.series.renamer.allocine.model.seasons.Season> seasons = tvseries.getSeason();
			for (com.series.renamer.allocine.model.seasons.Season seasonJSON : seasons) {
				Season season = new Season();
				season.setCode(String.valueOf(seasonJSON.getCode()));
				season.setOrder(seasonJSON.getSeasonNumber().intValue());
				season.setRating(season.getRating());

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
		String query = "partner=" + PARTNER_ID;
		query += "&season=" + code;
		query += "&page=1&count=1000&format=json";
		query += "&sed=" + getSed();
		query += "&sig=" + getSig(query);

		query = "episodelist?" + query;

		InputStream is = null;
		Reader isr = null;

		try {
			URL url = new URL(BASE_URL + query);

			is = url.openStream();
			isr = new InputStreamReader(is, "UTF-8");

			Gson gson = new Gson();
			GetEpisodes getEpisodes = gson.fromJson(isr, GetEpisodes.class);
			List<com.series.renamer.allocine.model.episodes.Episode> episodes = getEpisodes.getFeed().getEpisode();
			for (com.series.renamer.allocine.model.episodes.Episode episodeJSON : episodes) {
				Episode episode = new Episode();
				episode.setCode(String.valueOf(episodeJSON.getCode()));
				episode.setOriginalTitle(episodeJSON.getOriginalTitle());
				episode.setSeasonOrder(episodeJSON.getEpisodeNumberSeason().intValue());
				episode.setSerieOrder(episodeJSON.getEpisodeNumberSeries() == null ? 0 : episodeJSON.getEpisodeNumberSeries().intValue());
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

	private String getSig(String searchQuery) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// 8859_1 ou UTF-8
		md.update((HASHKEY + searchQuery).getBytes("UTF-8"));

		byte raw[] = md.digest(); // Message summary reception

		String sig = new String(org.apache.commons.codec.binary.Base64.encodeBase64(raw), "UTF-8");
		return sig;
	}

	private String getSed() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
}