
package com.series.renamer.allocine.model.episodes;


public class Episode{
   	private Number code;
   	private Number episodeNumberSeason;
   	private Number episodeNumberSeries;
   	private String originalTitle;
   	private ParentSeason parentSeason;
   	private Statistics statistics;
   	private String title;

 	public Number getCode(){
		return this.code;
	}
	public void setCode(Number code){
		this.code = code;
	}
 	public Number getEpisodeNumberSeason(){
		return this.episodeNumberSeason;
	}
	public void setEpisodeNumberSeason(Number episodeNumberSeason){
		this.episodeNumberSeason = episodeNumberSeason;
	}
 	public Number getEpisodeNumberSeries(){
		return this.episodeNumberSeries;
	}
	public void setEpisodeNumberSeries(Number episodeNumberSeries){
		this.episodeNumberSeries = episodeNumberSeries;
	}
 	public String getOriginalTitle(){
		return this.originalTitle;
	}
	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}
 	public ParentSeason getParentSeason(){
		return this.parentSeason;
	}
	public void setParentSeason(ParentSeason parentSeason){
		this.parentSeason = parentSeason;
	}
 	public Statistics getStatistics(){
		return this.statistics;
	}
	public void setStatistics(Statistics statistics){
		this.statistics = statistics;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
