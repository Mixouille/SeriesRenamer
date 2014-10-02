
package com.series.renamer.betaseries.model.episodes;

import java.util.List;

public class User{
   	private boolean downloaded;
   	private boolean seen;

 	public boolean getDownloaded(){
		return this.downloaded;
	}
	public void setDownloaded(boolean downloaded){
		this.downloaded = downloaded;
	}
 	public boolean getSeen(){
		return this.seen;
	}
	public void setSeen(boolean seen){
		this.seen = seen;
	}
}
