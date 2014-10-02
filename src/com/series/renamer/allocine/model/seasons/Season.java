
package com.series.renamer.allocine.model.seasons;


public class Season{
   	private Number code;
   	private Number seasonNumber;
   	private Statistics statistics;

 	public Number getCode(){
		return this.code;
	}
	public void setCode(Number code){
		this.code = code;
	}
 	public Number getSeasonNumber(){
		return this.seasonNumber;
	}
	public void setSeasonNumber(Number seasonNumber){
		this.seasonNumber = seasonNumber;
	}
 	public Statistics getStatistics(){
		return this.statistics;
	}
	public void setStatistics(Statistics statistics){
		this.statistics = statistics;
	}
}
