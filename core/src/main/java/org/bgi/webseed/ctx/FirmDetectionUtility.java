package org.bgi.webseed.ctx;

public class FirmDetectionUtility {
	
	public static String extractFirmNameFromPath(final String path){
		int start = 0;
		if(path.startsWith("/")){
			start = 1;
		}
		int end = path.indexOf('/', 1);
		if(end == -1){
			end = path.length();
		}
		return path.substring(start, end);
	}
	
	public static String removeFirmFromPath(String path, String firmWebContext){
		int start = 0;
		boolean mustStartWithSlash = false;
		if(path.startsWith("/")){
			start = 1;
			mustStartWithSlash = true;
		}
		int len = firmWebContext.length();
		String result = path.substring(start + len);
		if(!result.startsWith("/") && mustStartWithSlash){
			result = "/" + result;
		}
		return result;
	}

}
