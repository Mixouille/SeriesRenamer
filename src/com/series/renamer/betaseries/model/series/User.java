
package com.series.renamer.betaseries.model.series;

import java.util.List;

public class User{
   	private boolean archived;
   	private boolean favorited;
   	private String last;
   	private Number remaining;
   	private Number status;
   	private String tags;

 	public boolean getArchived(){
		return this.archived;
	}
	public void setArchived(boolean archived){
		this.archived = archived;
	}
 	public boolean getFavorited(){
		return this.favorited;
	}
	public void setFavorited(boolean favorited){
		this.favorited = favorited;
	}
 	public String getLast(){
		return this.last;
	}
	public void setLast(String last){
		this.last = last;
	}
 	public Number getRemaining(){
		return this.remaining;
	}
	public void setRemaining(Number remaining){
		this.remaining = remaining;
	}
 	public Number getStatus(){
		return this.status;
	}
	public void setStatus(Number status){
		this.status = status;
	}
 	public String getTags(){
		return this.tags;
	}
	public void setTags(String tags){
		this.tags = tags;
	}
}
