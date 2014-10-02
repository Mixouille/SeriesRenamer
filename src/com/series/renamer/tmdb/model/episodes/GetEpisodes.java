
package com.series.renamer.tmdb.model.episodes;

import java.util.List;

public class GetEpisodes{
   	private String air_date;
   	private List<Episodes> episodes;
   	private Number id;
   	private String name;
   	private String overview;
   	private String poster_path;
   	private Number season_number;

 	public String getAir_date(){
		return this.air_date;
	}
	public void setAir_date(String air_date){
		this.air_date = air_date;
	}
 	public List<Episodes> getEpisodes(){
		return this.episodes;
	}
	public void setEpisodes(List<Episodes> episodes){
		this.episodes = episodes;
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
 	public String getPoster_path(){
		return this.poster_path;
	}
	public void setPoster_path(String poster_path){
		this.poster_path = poster_path;
	}
 	public Number getSeason_number(){
		return this.season_number;
	}
	public void setSeason_number(Number season_number){
		this.season_number = season_number;
	}
}
