package com.series.renamer.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.series.renamer.Constants;

public class Settings implements Constants {

	private String patternName1 = "VF";
	private String patternName2 = "VO";

	private String pattern1 = "%svf% - %sai%x%esa% - %evf%";
	private String pattern2 = "%svo% - %sai%x%esa% - %evo%";

	private Boolean replaceSpaces = false;
	private Boolean extractSubFolders = false;

	private String lastFolder = null;

	private Integer providerIndex = 0;

	/**
	 * Saves settings, serializing the object
	 */
	public void save() {

		Properties p = new Properties();
		try {

			File file = new java.io.File(SETTINGS_FILE);
			if (!file.exists()) {
				file.createNewFile();
			}

			p.load(new FileInputStream(file));

			p.put("patternName1", patternName1);
			p.put("patternName2", patternName2);

			p.put("pattern1", pattern1);
			p.put("pattern2", pattern2);

			p.put("replaceSpaces", replaceSpaces ? "1" : "0");
			p.put("extractSubFolders", extractSubFolders ? "1" : "0");
			if (lastFolder != null) {
				p.put("lastFolder", lastFolder);
			}

			p.put("providerIndex", providerIndex.toString());
			p.store(new FileOutputStream(file), "### Configuration de Series Renamer ###");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads settings deserializing the object
	 * 
	 * @return
	 */
	public static Settings load() {

		Properties p = new Properties();
		Settings settings = new Settings();
		try {
			p.load(new FileInputStream(SETTINGS_FILE));

			settings.patternName1 = p.getProperty("patternName1");
			settings.patternName2 = p.getProperty("patternName2");

			settings.pattern1 = p.getProperty("pattern1");
			settings.pattern2 = p.getProperty("pattern2");

			settings.replaceSpaces = "1".equals(p.getProperty("replaceSpaces").trim());
			settings.extractSubFolders = "1".equals(p.getProperty("extractSubFolders").trim());

			settings.lastFolder = p.getProperty("lastFolder");

			settings.providerIndex = Integer.parseInt(p.getProperty("providerIndex"));

		} catch (Exception e) {
			// Nothing
		}
		return settings;
	}

	public String getPatternName1() {
		return patternName1;
	}

	public void setPatternName1(String patternName1) {
		this.patternName1 = patternName1;
	}

	public String getPatternName2() {
		return patternName2;
	}

	public void setPatternName2(String patternName2) {
		this.patternName2 = patternName2;
	}

	public String getPattern1() {
		return pattern1;
	}

	public void setPattern1(String pattern1) {
		this.pattern1 = pattern1;
	}

	public String getPattern2() {
		return pattern2;
	}

	public void setPattern2(String pattern2) {
		this.pattern2 = pattern2;
	}

	public void setReplaceSpaces(Boolean replaceSpaces) {
		this.replaceSpaces = replaceSpaces;
	}

	public Boolean isReplaceSpaces() {
		return replaceSpaces;
	}

	public Integer getProviderIndex() {
		return providerIndex;
	}

	public void setProviderIndex(Integer providerIndex) {
		this.providerIndex = providerIndex;
	}

	public Boolean isExtractSubFolders() {
		return extractSubFolders;
	}

	public void setExtractSubFolders(Boolean extractSubFolders) {
		this.extractSubFolders = extractSubFolders;
	}

	public String getLastFolder() {
		return lastFolder;
	}

	public void setLastFolder(String lastFolder) {
		this.lastFolder = lastFolder;
	}

}
