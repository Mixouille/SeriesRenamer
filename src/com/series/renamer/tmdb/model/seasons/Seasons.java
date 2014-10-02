
package com.series.renamer.tmdb.model.seasons;


public class Seasons{
   	private String air_date;
   	private Number id;
   	private String poster_path;
   	private Number season_number;

 	public String getAir_date(){
		return this.air_date;
	}
	public void setAir_date(String air_date){
		this.air_date = air_date;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
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
