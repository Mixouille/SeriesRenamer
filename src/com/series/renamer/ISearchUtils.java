package com.series.renamer;

import java.util.List;

import com.series.renamer.bean.Episode;
import com.series.renamer.bean.Season;
import com.series.renamer.bean.Serie;

public interface ISearchUtils {

	public List<Serie> getSerieList(String searchTerm) throws Exception;

	public List<Season> getSeasonList(String code) throws Exception;

	public List<Episode> getEpisodeList(String code) throws Exception;

}