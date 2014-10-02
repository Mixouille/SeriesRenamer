
package com.series.renamer.tmdb.model.episodes;


public class Episodes{
   	private String air_date;
   	private Number episode_number;
   	private Number id;
   	private String name;
   	private String overview;
   	private String still_path;
   	private Number vote_average;
   	private Number vote_count;

 	public String getAir_date(){
		return this.air_date;
	}
	public void setAir_date(String air_date){
		this.air_date = air_date;
	}
 	public Number getEpisode_number(){
		return this.episode_number;
	}
	public void setEpisode_number(Number episode_number){
		this.episode_number = episode_number;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getOverview(){
		return this.overview;
	}
	public void setOverview(String overview){
		this.overview = overview;
	}
 	public String getStill_path(){
		return this.still_path;
	}
	public void setStill_path(String still_path){
		this.still_path = still_path;
	}
 	public Number getVote_average(){
		return this.vote_average;
	}
	public void setVote_average(Number vote_average){
		this.vote_average = vote_average;
	}
 	public Number getVote_count(){
		return this.vote_count;
	}
	public void setVote_count(Number vote_count){
		this.vote_count = vote_count;
	}
}
