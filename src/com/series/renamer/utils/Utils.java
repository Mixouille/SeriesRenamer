package com.series.renamer.utils;

import java.util.Arrays;

import com.series.renamer.Constants;
import com.series.renamer.bean.File;

public class Utils implements Constants {

	public static String getFileExtension(File file) {
		return getFileExtension(file.getFile());
	}

	public static String getFileExtension(java.io.File file) {
		String extension = "";
		try {
			extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		} catch (Exception e) {
		}
		return extension;
	}

	public static boolean isVideoFile(java.io.File file) {
		return Arrays.asList(VIDEO_EXTENSIONS).contains(getFileExtension(file));
	}
}
