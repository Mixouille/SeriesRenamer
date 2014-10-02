
package com.series.renamer.betaseries.model.series;

import java.util.List;

public class GetSeries{
   	private List<Errors> errors;
   	private List<Shows> shows;

 	public List<Errors> getErrors(){
		return this.errors;
	}
	public void setErrors(List<Errors> errors){
		this.errors = errors;
	}
 	public List<Shows> getShows(){
		return this.shows;
	}
	public void setShows(List<Shows> shows){
		this.shows = shows;
	}
}
