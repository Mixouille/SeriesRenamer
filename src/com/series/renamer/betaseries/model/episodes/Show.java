
package com.series.renamer.betaseries.model.episodes;

import java.util.List;

public class Show{
   	private Number id;
   	private Number thetvdb_id;
   	private String title;

 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public Number getThetvdb_id(){
		return this.thetvdb_id;
	}
	public void setThetvdb_id(Number thetvdb_id){
		this.thetvdb_id = thetvdb_id;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
