package com.series.renamer.bean;
public class File implements Comparable<File> {

	private java.io.File file;
	private String displayName;
	private int order = 0;

	public File(java.io.File myfile) {
		super();
		this.file = myfile;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

	public java.io.File getFile() {
		return file;
	}

	public void setFile(java.io.File file) {
		this.file = file;
	}

	public String getDisplayName() {
		return (displayName == null || displayName.length() == 0) ? getFile().getName() : displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int compareTo(File o) {
		File myfile = (File) o;
		return this.getOrder() - myfile.getOrder();
	}
}
